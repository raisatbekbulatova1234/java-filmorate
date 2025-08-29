package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController("/films")
public class FilmController {
    Map<Integer, Film> films = new HashMap<>();

    //добавление фильма;
    public void createFilms(Film film) {
        LocalDate minReleaseDate = LocalDate.of(1895, 12, 28);
        Instant minTime = minReleaseDate.atStartOfDay(ZoneId.systemDefault()).toInstant();

        if (film != null) {
            if (film.getName().isEmpty()) {
                throw new ValidationException("Название не может быть пустым");
            }
            if (film.getDescription().length() > 200) {
                throw new ValidationException("Максимальная длина описания — 200 символов");
            }
            Instant releaseDate = film.getReleaseDate();
            if (releaseDate.isBefore(minTime)) {
                throw new ValidationException("Дата релиза должна быть не раньше 28 декабря 1895 года");
            }
            if (film.getDuration().isNegative()) {
                throw new ValidationException("Продолжительность фильма должна быть положительным числом");
            }
            if (films.containsKey(film.getId())) {
                throw new ValidationException("Фильм уже существует");
            }
            films.put(film.getId(), film);
        }
    }


    //обновление фильма;
    public void updateFilm(Film film) {
        if (film == null) {
            throw new ValidationException("В метод передано значение фильма равное null!");
        }
        if (!films.containsKey(film.getId())) {
            throw new ValidationException("Такого фильма не существует!");
        }

        if (film.getName() == null || film.getName().isEmpty()) {
            throw new ValidationException("Название не может быть пустым");
        }
        if (film.getDescription().length() > 200) {
            throw new ValidationException("Максимальная длина описания — 200 символов");
        }
        Instant releaseDate = film.getReleaseDate();
        LocalDate minReleaseDate = LocalDate.of(1895, 12, 28);
        Instant minTime = minReleaseDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
        if (releaseDate.isBefore(minTime)) {
            throw new ValidationException("Дата релиза должна быть не раньше 28 декабря 1895 года");
        }
        if (film.getDuration().isNegative()) {
            throw new ValidationException("Продолжительность фильма должна быть положительным числом");
        }

        films.put(film.getId(), film);
    }

    //получение всех фильмов
    public List<Film> getAllFilms() {
        return new ArrayList<>(films.values());
    }
}
