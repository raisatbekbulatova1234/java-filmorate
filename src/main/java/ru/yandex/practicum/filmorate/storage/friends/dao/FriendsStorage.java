package ru.yandex.practicum.filmorate.storage.friends.dao;

import ru.yandex.practicum.filmorate.model.User;

import java.util.List;

/**
 * Интерфейс работы с базой данных друзей.
 */
public interface FriendsStorage {

    /**
     * Вывести список друзей.
     */
    List<User> getFriends(Integer id);

    /**
     * Добавить пользователей с ID1 и ID2 в друзья.
     */
    void addFriend(Integer id, Integer idFriend, Integer idFriendship);

    /**
     * Удалить пользователей из друзей.
     */
    void deleteFromFriends(Integer id, Integer idFriend);

    /**
     * Вывести список общих друзей.
     */
     List<User> getCommonFriends(Integer id1, Integer id2);

}