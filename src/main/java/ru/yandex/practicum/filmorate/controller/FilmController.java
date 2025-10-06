package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/films")
public class FilmController {
    Map<Integer, Film> films = new HashMap<>();

    //добавление фильма;
    @PostMapping
    public Film createFilm(@RequestBody @Valid Film film) {
        film.setId(nextId());
        films.put(film.getId(), film);
        log.info("Фильм '{}' добавлен, id = '{}'", film.getName(), film.getId());
        return film;
    }

    //обновление фильма;
    @PutMapping
    public Film updateFilm(@RequestBody @Valid Film film) {

        if (film == null) {
            throw new ValidationException("В метод передано значение фильма равное null!");
        }
        if (!films.containsKey(film.getId())) {
            throw new ValidationException("Такого фильма не существует!");
        } else {
            films.put(film.getId(), film);
            log.info("Фильм '{}' обновлен, id = '{}'", film.getName(), film.getId());
        }
        return film;
    }

    //получение всех фильмов
    @GetMapping
    public List<Film> getAllFilms() {
        return new ArrayList<>(films.values());
    }

    private int nextId() {
        int currentMaxId = Math.toIntExact(films.keySet()
                .stream()
                .mapToLong(id -> id)
                .max()
                .orElse(0));
        return ++currentMaxId;
    }
}
