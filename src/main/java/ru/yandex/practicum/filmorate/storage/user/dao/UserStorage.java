package ru.yandex.practicum.filmorate.storage.user.dao;

import ru.yandex.practicum.filmorate.model.User;

import java.util.List;

public interface UserStorage {

    /**
     * Добавить юзера в БД.
     */
    User addToStorage(User user);

    /**
     * Обновить юзера в БД.
     */
    User updateInStorage(User user);

    /**
     * Удалить пользователя из БД. True - удалено.
     */
    void removeFromStorage(Integer id);

    /**
     * Получить список всех пользователей.
     */
    List<User> getAllUsersFromStorage();

    /**
     * Получить пользователя по ID.
     */
    User getUserById(Integer id);

    /**
     * Получить пользователя по логину.
     */
    User getUserByLogin(String login);

    /**
     * Проверка наличия юзера в БД.
     */
    boolean isExistUserInDB(Integer id);

}