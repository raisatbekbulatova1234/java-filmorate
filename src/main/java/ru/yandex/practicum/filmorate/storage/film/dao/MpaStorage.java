package ru.yandex.practicum.filmorate.storage.film.dao;


import ru.yandex.practicum.filmorate.model.Mpa;

import java.util.List;

public interface MpaStorage {

    /**
     * Вернуть MPA-рейтинг по его ID.
     */
    Mpa getMpaById(Integer mpaId);

    /**
     * Получить информацию о наличии MPA с ID = 'mpaId'.
     */
    boolean existMpaByIdInDB(Integer mpaId);

    /**
     * Получить список всех MPA из БД.
     */
    List<Mpa> getAllMpa();
}