package ru.yandex.practicum.filmorate.storage.database;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.mapper.UserRowMapper;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.UserStorage;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component("UserDbStorage")
@RequiredArgsConstructor
public class UserDbStorage implements UserStorage {

    private final JdbcTemplate jdbcTemplate;
    private final UserRowMapper userRowMapper = new UserRowMapper();

    String sqlUsers = "SELECT * FROM users";
    String sqlAdd = "INSERT INTO users (email, login, name, birthday) VALUES (?, ?, ?, ?)";
    String sqlUpdate = "UPDATE users SET email = ?, login = ?, name = ?, birthday = ? WHERE user_id = ?";
    String sqlGetById = "SELECT * FROM users WHERE user_id=?";
    String sqlFindUserById = "SELECT * FROM users WHERE user_id = ?";
    String sqlAddFriend = "INSERT INTO friendship (user_id, friend_id) VALUES (?, ?)";
    String sqlGetUserFriends = "SELECT friend_id FROM friendship WHERE user_id = ?";
    String sqlGetAllUsers = "SELECT * FROM users";
    String sqlRemoveFriend = "DELETE FROM friendship WHERE user_id = ? AND friend_id = ?";
    String sqlGetAllFriends = "SELECT user_id, friend_id FROM friendship";
    String sqlGetIDCommonFriends = "SELECT f1.friend_id " +
            "FROM friendship f1 " +
            "INNER JOIN friendship f2 ON f1.friend_id = f2.friend_id " +
            "WHERE f1.user_id = ? AND f2.user_id = ?";

    @Override
    public List<User> findAll() {
        List<User> users = jdbcTemplate.query(sqlUsers, userRowMapper);

        if (users.isEmpty()) {
            return users;
        }

        String inSql = users.stream().map(u -> "?").collect(Collectors.joining(","));
        String sqlFriends = "SELECT user_id, friend_id " +
                "             FROM friendship WHERE user_id IN (" + inSql + ")";
        List<Long> userIds = users.stream().map(User::getId).toList();

        Map<Long, Set<Long>> friendsMap = new HashMap<>();
        jdbcTemplate.query(sqlFriends, rs -> {
            Long userId = rs.getLong("user_id");
            Long friendId = rs.getLong("friend_id");
            friendsMap.computeIfAbsent(userId, k -> new HashSet<>()).add(friendId);
        }, userIds.toArray());

        for (User user : users) {
            user.setFriends(friendsMap.getOrDefault(user.getId(), Set.of()));
        }

        return users;
    }

    @Override
    public User add(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlAdd, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setObject(4, user.getBirthday());
            return preparedStatement;
        }, keyHolder);

        user.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        log.info("Добавлен пользователь с id={}", user.getId());
        return user;
    }

    @Override
    public User update(User user) {

        jdbcTemplate.update(sqlUpdate,
                user.getEmail(),
                user.getLogin(),
                user.getName(),
                user.getBirthday(),
                user.getId());

        return user;
    }

    @Override
    public User getById(Long id) {
        List<User> users = jdbcTemplate.query(sqlGetById, userRowMapper, id);
        if (users.isEmpty()) {
            return null;
        }
        User user = users.get(0);
        user.setFriends(getUserFriends(user.getId()));
        return user;
    }

    @Override
    public Optional<User> findUserById(int id) {

        List<User> users = jdbcTemplate.query(sqlFindUserById, userRowMapper, id);
        return users.isEmpty() ? Optional.empty() : Optional.of(users.get(0));
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = jdbcTemplate.query(sqlGetAllUsers, userRowMapper);
        Map<Long, Set<Long>> allFriends = getAllFriends();
        for (User user : users) {
            user.setFriends(allFriends.getOrDefault(user.getId(), new HashSet<>()));
        }

        return users;
    }


    @Override
    public void addFriend(Long userId, Long friendId) {
        try {
            jdbcTemplate.update(sqlAddFriend, userId, friendId);
        } catch (Exception e) {
            log.warn("Не удалось добавить дружбу между пользователями {} и {}: {}", userId, friendId, e.getMessage());
            throw e; // пробрасываем исключение дальше
        }
    }

    @Override
    public void removeFriend(Long userId, Long friendId) {
        jdbcTemplate.update(sqlRemoveFriend, userId, friendId);
    }

    @Override
    public Set<Long> getUserFriends(Long userId) {
        List<Long> friends = jdbcTemplate.query(sqlGetUserFriends, (rs, rowNum) -> rs.getLong("friend_id"), userId);
        return new HashSet<>(friends);
    }

    @Override
    public List<User> getCommonFriends(Long userId1, Long userId2) {

        List<Long> commonFriendIds = jdbcTemplate.query(sqlGetIDCommonFriends,
                (rs, rowNum) -> rs.getLong("friend_id"), userId1, userId2);

        if (commonFriendIds.isEmpty()) {
            return new ArrayList<>();
        }

        String inSql = String.join(",", Collections.nCopies(commonFriendIds.size(), "?"));
        String userSql = "SELECT * FROM users " +
                "WHERE user_id IN (" + inSql + ")";
        List<User> users = jdbcTemplate.query(userSql, userRowMapper, commonFriendIds.toArray());
        return users;
    }

    private Map<Long, Set<Long>> getAllFriends() {
        Map<Long, Set<Long>> allFriends = new HashMap<>();

        jdbcTemplate.query(sqlGetAllFriends, (rs, rowNum) -> {
            Long userId = rs.getLong("user_id");
            Long friendId = rs.getLong("friend_id");
            allFriends.computeIfAbsent(userId, k -> new HashSet<>()).add(friendId);
            return null;
        });

        return allFriends;
    }

}