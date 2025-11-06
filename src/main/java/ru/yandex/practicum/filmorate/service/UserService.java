package ru.yandex.practicum.filmorate.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.NotFoundRecordInBD;
import ru.yandex.practicum.filmorate.exception.ValidateException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.friends.dao.FriendsStorage;
import ru.yandex.practicum.filmorate.storage.user.dao.UserStorage;

import java.util.List;

@Slf4j
@Service
@Qualifier("UserDBService")
public class UserService {

    //Используется для однозначности использования классов наследников интерфейса.
    private final UserStorage userStorage;

    private final FriendsStorage friendsDBStorage;

    private final ValidationService validationService;


    @Autowired
    public UserService(@Qualifier("UserDBStorage") UserStorage userStorage, ValidationService validationService,
                       @Qualifier("FriendsDBStorage") FriendsStorage friendsStorage) {
        this.userStorage = userStorage;
        this.validationService = validationService;
        this.friendsDBStorage = friendsStorage;
    }


    /**
     * Получить пользователя по ID.
     */
    public User getUserById(Integer id) {
        User result = userStorage.getUserById(id);
        if (result == null) {
            String error = "В БД отсутствует запись о пользователе при получении пользователя по ID = " + id + ".";
            log.error(error);
            throw new NotFoundRecordInBD(error);
        }
        return result;
    }

    /**
     * Получение списка всех пользователей.
     */
    public List<User> getAllUsers() {
        return userStorage.getAllUsersFromStorage();
    }


    /**
     * Добавить юзера в БД.
     */
    public User addToStorage(User user) throws ValidateException, NotFoundRecordInBD {

        // TODO: 2022.09.21 02:20:26 Удалить старый UserServiceOld - @Dmitriy_Gaju
        //Проверяем необходимые поля, и, если имя пустое, то оно равно логину.
        validationService.checkUser(user);
        return userStorage.addToStorage(user);
    }

    /**
     * Обновить юзера в БД.
     */
    public User updateInStorage(User user) {
        //Проверяем необходимые поля, и, если имя пустое, то оно равно логину.
        validationService.checkUser(user);
        //Проверяем наличие записи о пользователе с ID = user.getId().
        validationService.checkExistUserInDB(user.getId());
        return userStorage.updateInStorage(user);
    }

    /**
     * Удалить пользователя из БД.
     */
    public void removeFromStorage(Integer id) {
        validationService.checkExistUserInDB(id);
        //Если пользователь есть в БД, то идём далее.
        userStorage.removeFromStorage(id);
        String message = "Выполнено удаление пользователя  из БД с ID = '" + id + ".";
        log.info(message);
    }


    /**
     * Добавить пользователей с ID1 и ID2 в друзья.
     */
    public void addEachOtherAsFriends(Integer id1, Integer id2) {
        log.debug("Запрос на удаление из друзей.");
        validationService.checkExistUserInDB(id1);
        validationService.checkExistUserInDB(id2);
        // TODO: 2022.09.23 22:25:09 idFriendship считаем равным 2 - не подтверждено. - @Dmitriy_Gaju
        friendsDBStorage.addFriend(id1, id2, 2);    //idFriendship считаем равным 2 - не подтверждено.
    }

    /**
     * Удалить пользователей из друзей.
     */
    public void deleteFromFriends(Integer id1, Integer id2) {
        log.debug("Запрос на удаление из друзей пользователем (Id = {}) пользователя (ID = {}).", id1, id2);
        validationService.checkExistUserInDB(id1);
        validationService.checkExistUserInDB(id2);
        friendsDBStorage.deleteFromFriends(id1, id2);
    }


    /**
     * Вывести список общих друзей.
     */
    public List<User> getCommonFriends(Integer id1, Integer id2) {
        log.debug("Запрос получения общих друзей.");
        validationService.checkExistUserInDB(id1);
        validationService.checkExistUserInDB(id2);

        List<User> result = friendsDBStorage.getCommonFriends(id1, id2);
        log.debug("Запрос получения общих друзей выполнен.");
        return result;
    }

    /**
     * Вывести список друзей пользователя с ID.
     */
    public List<User> getUserFriends(Integer id) {
        log.debug("Запрошена выдача списка друзей пользователя с ID = {}.", id);
        validationService.checkExistUserInDB(id);
        List<User> result = friendsDBStorage.getFriends(id);
        log.debug("Выполнен запрос на выдачу списка друзей пользователя с ID = {}.", id);
        return result;
    }

    private Integer idFromDBByID(Integer id) {

        return userStorage.getAllUsersFromStorage().stream().filter(u -> u.getId().equals(id))
                .findFirst().map(User::getId)
                .orElse(null);
    }
}