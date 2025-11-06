package ru.yandex.practicum.filmorate.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.film.dao.GenreStorage;
import ru.yandex.practicum.filmorate.storage.like.dao.LikeStorage;

import java.util.HashSet;
import java.util.List;

@Service
@Qualifier("LikeDBService")
@Slf4j
public class LikeService {

    @Qualifier("LikeDBStorage")
    private final LikeStorage likeStorage;

    @Qualifier("GenreDBStorage")
    private final GenreStorage genreStorage;

    private final ValidationService validationService;

    @Autowired
    public LikeService(LikeStorage likeStorage, GenreStorage genreStorage, ValidationService validationService) {
        this.likeStorage = likeStorage;
        this.genreStorage = genreStorage;
        this.validationService = validationService;
    }

    /**
     * Пользователь снимает ранее поставленный лайк фильму. Потом ставит новый.
     */
    public void addLike(Integer filmId, Integer userId) {
        likeStorage.addLike(filmId, userId);
    }

    /**
     * Пользователь удаляет лайк фильму.
     */
    public void removeLike(Integer filmId, Integer userId) {
        validationService.checkExistUserInDB(userId);
        validationService.checkExistFilmInDB(filmId);
        likeStorage.removeLike(filmId, userId);
    }

    /**
     * Возвращает список из первых count фильмов по количеству лайков.
     */
    public List<Film> getPopularFilm(Integer count) {
        List<Film> result = likeStorage.getPopularFilm(count);
        for (Film film : result) {
            film.setGenres(new HashSet<>(genreStorage.findGenresOfFilmId(film.getId())));
        }
        return result;
    }
}
