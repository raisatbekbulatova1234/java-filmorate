package ru.yandex.practicum.filmorate.storage.film.dao;

import ru.yandex.practicum.filmorate.model.Genre;

import java.util.List;
import java.util.Set;

public interface GenreStorage {

    /**
     * Внесение данных в таблицу фильмов и жанров.
     */
    Set<Genre> addInDBFilm_Genre(Integer filmId, Set<Genre> genres);

    /**
     * Получить список жанров фильма с ID = idFilm.
     */
    Set<Genre> findGenresOfFilmId(Integer idFilm);

    /**
     * Получить все жанры из БД.
     */
    List<Genre> getAllGenres();

    /**
     * Получить жанр из БД по его ID.
     */
    Genre getGenreById(Integer genreId);

    /**
     * Проверить наличие жанра в БД по его ID.
     */
    boolean existGenreInDBById(Integer genreId);
}
