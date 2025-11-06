package ru.yandex.practicum.filmorate.storage.like.dao;

import ru.yandex.practicum.filmorate.model.Film;

import java.util.List;

public interface LikeStorage {

    /**
     * Пользователь удаляет лайк фильму.
     */
    void removeLike(Integer filmId, Integer userId);
    /**
     * Пользователь снимает ранее поставленный лайк фильму.
     */
    void addLike(Integer filmId, Integer userId);

    /**
     * Возвращает список из первых count фильмов по количеству лайков.
     */
    public List<Film> getPopularFilm(Integer count);
}
