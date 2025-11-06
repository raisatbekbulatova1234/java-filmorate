package ru.yandex.practicum.filmorate.storage.film.dao;

import ru.yandex.practicum.filmorate.model.Film;

import java.util.List;

public interface FilmStorage {

    /**
     * Создание фильма в хранилище.
     */
    Film addInStorage(Film film);

    /**
     * Обновление информации о существующем фильме.
     */
    Film updateInStorage(Film film);

    /**
     * Удалить фильм из библиотеки.
     */
    Film removeFromStorage(Film film);

    /**
     * Удалить фильм из библиотеки.
     */
    void removeFromStorageById(Integer filmId);

    /**
     * Вывести список всех фильмов.
     */
    List<Film> getAllFilms();

    /**
     * Метод получения фильма из библиотеки по его ID.
     */

    Film getFilmById(Integer id);

    Film getFilmByName(String name);

    /**
     * Найти популярные фильмы.
     */
    List<Film> getPopularFilms(Integer count);

    /**
     * Проверка наличия фильма в БД по его ID.
     */
    boolean isExistFilmInDB(Integer id);
}