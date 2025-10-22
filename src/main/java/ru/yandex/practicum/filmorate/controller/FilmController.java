package ru.yandex.practicum.filmorate.controller;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.GlobalExceptionHandler;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmService;
import ru.yandex.practicum.filmorate.service.UserService;
import ru.yandex.practicum.filmorate.storage.film.FilmStorage;

import java.util.List;

@RestController
@RequestMapping("/films")
@RequiredArgsConstructor
@Import(GlobalExceptionHandler.class)
public class FilmController {

    private final FilmService filmService;
    private final UserService userService;
    private final FilmStorage filmStorage;

    @GetMapping
    public List<Film> getAll() {
        return filmStorage.getAllFilms();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Film addFilm(@Valid @RequestBody Film film) {
        return filmStorage.addFilm(film);
    }

    @PutMapping
    public Film updateFilm(@Valid @RequestBody Film film) {
        return filmStorage.updateFilm(film);
    }

    @GetMapping("/{id}")
    public Film getFilmById(@PathVariable @Positive long id) {
        return filmStorage.getFilmById(id);
    }

    @PutMapping("/{id}/like/{userId}")
    public void addLike(@PathVariable @Positive long id,
                        @PathVariable @Positive long userId) {
        userService.getUserById(userId); // проверяем, что пользователь существует
        filmService.addLike(id, userId);
    }

    @DeleteMapping("/{id}/like/{userId}")
    public void removeLike(@PathVariable @Positive long id,
                           @PathVariable @Positive long userId) {
        userService.getUserById(userId); // проверяем, что пользователь существует
        filmService.removeLike(id, userId);
    }

    @GetMapping("/popular")
    public List<Film> getPopular(@RequestParam(defaultValue = "10") @Positive int count) {
        return filmService.getPopular(count);
    }
}