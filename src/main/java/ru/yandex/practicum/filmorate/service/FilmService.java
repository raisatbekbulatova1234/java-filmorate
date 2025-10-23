package ru.yandex.practicum.filmorate.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.messages.LogMessages;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.film.InMemoryFilmStorage;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class FilmService {

    private final InMemoryFilmStorage storage;

    public Film getById(long id) {
        return storage.getFilmById(id);
    }

    public void addLike(long filmId, long userId) {
        log.info(String.valueOf(LogMessages.TRY_ADD_LIKE));
        Film film = getById(filmId);
        film.getLikes().add(userId);
        log.info(String.valueOf(LogMessages.LIKE_DONE));

    }

    public void removeLike(long filmId, long userId) {
        log.info(String.valueOf(LogMessages.TRY_REMOVE_LIKE));
        Film film = getById(filmId);
        film.getLikes().remove(userId);
        log.info(String.valueOf(LogMessages.LIKE_CANCEL));
    }

    public List<Film> getPopular(int count) {
        log.info(String.valueOf(LogMessages.TRY_GET_POPULAR));
        return storage.getAllFilms().stream()
                .sorted(Comparator.comparingInt((Film f) -> f.getLikes().size()).reversed())
                .limit(count)
                .collect(Collectors.toList());
    }

}
