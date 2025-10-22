package ru.yandex.practicum.filmorate.storage.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.messages.LogMessages;
import ru.yandex.practicum.filmorate.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Component
@Slf4j
public class InMemoryUserStorage implements UserStorage {
    private final Map<Long, User> users = new HashMap<>();


    public List<User> getAll() {
        log.info(String.valueOf(LogMessages.COUNT), users.size());
        return (List<User>) users.values();
    }

    public User add(User user) {
        log.info(String.valueOf(LogMessages.TRY_ADD), user.getName());
        user.setId(nextId());
        users.put(user.getId(), user);
        log.info(String.valueOf(LogMessages.ADD));

        return user;
    }

    public User update(User user) {
        log.info(String.valueOf(LogMessages.TRY_UPDATE), user.getName());
        if (!users.containsKey(user.getId())) {
            throw new NoSuchElementException("Пользователь не найден");
        }
        users.put(user.getId(), user);
        log.info(String.valueOf(LogMessages.UPDATE));
        return user;
    }

    public User getById(long id) {
        User user = users.get(id);
        if (user == null) {
            throw new NoSuchElementException("Пользователь не найден");
        }
        return user;
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
