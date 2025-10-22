package ru.yandex.practicum.filmorate.storage.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import java.util.*;

@Component
@Slf4j
public class InMemoryUserStorage implements UserStorage {
    Map<Long, User> users = new HashMap<>();

    public User createUser(User user) {
        if (user.getName() == null) user.setName(user.getLogin());
        user.setId(nextId());
        users.put(user.getId(), user);
        log.info("Пользователь'{}' с id - '{}' был создан", user.getName(), user.getId());
        return user;
    }


    public User updateUser(User user) {
        if (user == null) {
            throw new ValidationException("Пользователь не может быть null");
        }
        if (!users.containsKey(user.getId())) {
            throw new NotFoundException("Пользователя с таким ID не существует");
        }

        if (user.getName() == null) user.setName(user.getLogin());

        users.put(user.getId(), user);
        log.info("Информация о пользователе'{}' с id - '{}' была обновлена", user.getName(), user.getId());
        return user;
    }

    //получение списка всех пользователей
    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    //добавление друга
    public void addFriend(Long userId, Long friendId) {
        if (userId != null && friendId != null) {
            if (users.containsKey(userId) && users.containsKey(friendId)) {
                User user1 = getUserById(userId);
                User user2 = getUserById(friendId);

                Set<Long> set = user1.getFriends();
                Set<Long> set2 = user2.getFriends();

                if (set == null) { // Проверяем на null перед использованием
                    set = new HashSet<>();
                    user1.setFriends(set);
                }
                set.add(friendId); // Добавляем друга

                log.info("Добавили друга для пользователя с ID = " + user1.getId());

                if (set2 == null) { // Проверяем на null перед использованием
                    set2 = new HashSet<>();
                    user2.setFriends(set2);
                }
                set2.add(userId); // Добавляем друг друга симметрично

                log.info("Добавили друга для пользователя с ID = " + user2.getId());

                updateUser(user1);
                updateUser(user2);
            } else {
                throw new NotFoundException("Пользователи с указанными ID не найдены.");
            }
        } else {
            throw new ValidationException("Значения user и friend не могут быть равны null.");
        }
    }


    public void removeFriend(Long userId, Long friendId) {
        if (userId != null && friendId != null) {
            if (users.containsKey(userId) && users.containsKey(friendId)) {
                User user = users.get(userId);
                User friend = users.get(friendId);

                if (user.getFriends() != null) {
                    user.getFriends().remove(friendId);
                }

                if (friend.getFriends() != null) {
                    friend.getFriends().remove(userId);
                }
            } else {
                throw new NotFoundException("Один или оба пользователя с указанными ID не найдены.");
            }
        } else {
            throw new ValidationException("Значения user и friend не могут быть равны null.");
        }
    }


    //получение списка друзей у пользователя
//    public Set<Long> getFriendList(Long userId) {
//        if ((userId != null) && (users.containsKey(userId))) {
//            return getUserById(userId).getFriends();
//        } else throw new NotFoundException("Значение user не может быть равно null");
//    }
    public Set<Long> getFriendList(Long userId) {
        if (userId != null && users.containsKey(userId)) {
            User user = getUserById(userId);
            Set<Long> friends = user.getFriends();
            if (friends != null) {
                return friends;
            } else {
                // Если список друзей null, возвращаем пустой набор
                return Collections.emptySet();
            }
        } else {
            throw new NotFoundException("Пользователь с указанным ID не найден.");
        }
    }


    //    public List<Long> getCrossFriendList(Long userId, Long otherId) {
//        List<Long> userList = new ArrayList<>();
//        if ((userId != null) && (otherId != null)) {
//            if (users.containsKey(userId) && users.containsKey(otherId)) {
//                Set<Long> setCrossId = users.get(userId).getFriends();
//                setCrossId.addAll(users.get(otherId).getFriends());
//                for (User user : users.values()) {
//                    if (setCrossId.contains(users.get(userId).getId())) {
//                        userList.add(user.getId());
//                    }
//                }
//
//            } else throw new NotFoundException("Значения userId и otherId не могут быть равны null");
//        }
//        return userList;
//    }
//получение списка всех друзей
    public List<Long> getCrossFriendList(Long userId, Long otherId) {
        if (userId != null && otherId != null) {
            if (users.containsKey(userId) && users.containsKey(otherId)) {
                Set<Long> userFriends = users.get(userId).getFriends();
                Set<Long> otherFriends = users.get(otherId).getFriends();

                // Объединение списков друзей в один набор
                Set<Long> crossFriends = new HashSet<>(userFriends);
                crossFriends.addAll(otherFriends);

                return new ArrayList<>(crossFriends); // Преобразуем набор в список
            } else {
                throw new NotFoundException("Пользователи с указанными ID не найдены.");
            }
        } else {
            throw new ValidationException("Значения userId и otherId не могут быть равны null.");
        }
    }


    @Override
    public User getUserById(Long id) {
        if (!users.containsKey(id)) {
            throw new NotFoundException("Пользователя с таким ID - " + id + "  не существует! Не добавила значится, косяяяяк!");
        }
        return users.get(id);
    }


    private Long nextId() {
        long currentMaxId = users.keySet()
                .stream()
                .mapToLong(id -> id)
                .max()
                .orElse(0);
        return ++currentMaxId;
    }
}
