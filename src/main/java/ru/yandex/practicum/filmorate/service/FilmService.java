package ru.yandex.practicum.filmorate.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.messages.LogMessages;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.film.InMemoryFilmStorage;
import ru.yandex.practicum.filmorate.storage.user.InMemoryUserStorage;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class FilmService {

    private final InMemoryFilmStorage filmStorage;
    private final InMemoryUserStorage userStorage;

    public Film getById(long id) {
        return filmStorage.getFilmById(id);
    }
    public List<Film> getAllFilms() {
        return  filmStorage.getAllFilms();
    }

    public Film addFilm(Film film) {
        return filmStorage.addFilm(film);
    }

    public Film updateFilm(Film film) {
        return filmStorage.updateFilm(film);
    }

    public Film getFilmById(long id) {
        return filmStorage.getFilmById(id);
    }

    public void addLike(long filmId, long userId) {
        log.info(String.valueOf(LogMessages.TRY_ADD_LIKE));
        userStorage.getUserById(userId); // проверяем, что пользователь существует
        Film film = getById(filmId);
        film.getLikes().add(userId);
        log.info(String.valueOf(LogMessages.LIKE_DONE));

    }

    public void removeLike(long filmId, long userId) {
        log.info(String.valueOf(LogMessages.TRY_REMOVE_LIKE));
        userStorage.getUserById(userId); // проверяем, что пользователь существует
        Film film = getById(filmId);
        film.getLikes().remove(userId);
        log.info(String.valueOf(LogMessages.LIKE_CANCEL));
    }

    public List<Film> getPopular(int count) {
        log.info(String.valueOf(LogMessages.TRY_GET_POPULAR));
        return filmStorage.getAllFilms().stream()
                .sorted(Comparator.comparingInt((Film f) -> f.getLikes().size()).reversed())
                .limit(count)
                .collect(Collectors.toList());
    }


}
