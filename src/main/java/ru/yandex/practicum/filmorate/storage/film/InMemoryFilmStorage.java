package ru.yandex.practicum.filmorate.storage.film;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.messages.LogMessages;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Slf4j
@Component
public class InMemoryFilmStorage implements FilmStorage {
    private final Map<Long, Film> films = new HashMap<>();


    public List<Film> getAllFilms() {
        log.info(String.valueOf(LogMessages.COUNT));
        return films.values().stream().toList();

    }

    public Film addFilm(Film film) {
        log.info(String.valueOf(LogMessages.TRY_ADD));
        film.setId(nextId());
        films.put(film.getId(), film);
        log.info(String.valueOf(LogMessages.ADD));
        return film;
    }

    public Film updateFilm(Film film) {
        log.info(String.valueOf(LogMessages.TRY_UPDATE));
        if (!films.containsKey(film.getId())) {
            log.info(String.valueOf(LogMessages.MISSING));
            throw new NoSuchElementException("Фильм не найден");
        }
        films.put(film.getId(), film);
        log.info(String.valueOf(LogMessages.UPDATE));
        return film;
    }

    public Film getFilmById(long id) {
        log.info(String.valueOf(LogMessages.TRY_GET_OBJECT));
        Film film = films.get(id);
        if (film == null) {
            log.info(String.valueOf(LogMessages.MISSING));
            throw new NoSuchElementException("Фильм не найден");
        }
        return film;
    }

    private Long nextId() {
        long currentMaxId = films.keySet()
                .stream()
                .mapToLong(id -> id)
                .max()
                .orElse(0);
        return ++currentMaxId;
    }
}
