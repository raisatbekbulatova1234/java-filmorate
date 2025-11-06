package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmService;

import java.util.List;

@Slf4j
@Component
@RestController
public class FilmController {

    @Qualifier("FilmDBStorage")
    private final FilmService filmService;

    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    static final String PATH_FOR_FILMS = "/films";
    private static final String PATH_FOR_POPULAR = "/popular";
    private static final String PATH_FOR_LIKE = "/like";
    private static final String PATH_FOR_ID_VARIABLE = "/{id}";
    private static final String PATH_FOR_USER_ID_VARIABLE = "/{userId}";


    /**
     * Получение списка всех фильмов.
     */
    @GetMapping("/films")
    public List<Film> getAllFilms() {
        log.info("Выдан ответ на запрос всех фильмов.");
        return filmService.getAllFilms();
    }

    /**
     * Создание фильма
     */
    @PostMapping("/films")
    public ResponseEntity<?> createFilm(@RequestBody Film film) {
        return ResponseEntity.ok(filmService.add(film));
    }

    /**
     * Обновление информации о существующем фильме.
     */
    @PutMapping("/films")
    public ResponseEntity<?> updateFilm(@RequestBody Film film) {
        return ResponseEntity.ok(filmService.update(film));
    }

    /**
     * PUT /films/{id}/like/{userId}
     */
    @GetMapping("/films" + "/{id}")
    public Film getFilmById(@PathVariable Integer id) {
        Film film = filmService.getFilmById(id);
        log.info("Выдан ответ на запрос фильма по ID = {}.\t{}", id, film);
        return film;
    }

}