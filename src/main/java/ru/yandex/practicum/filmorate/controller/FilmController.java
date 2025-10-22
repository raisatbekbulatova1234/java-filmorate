//package ru.yandex.practicum.filmorate.controller;
//
//
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.*;
//import ru.yandex.practicum.filmorate.model.Film;
//import ru.yandex.practicum.filmorate.service.FilmService;
//import ru.yandex.practicum.filmorate.storage.film.FilmStorage;
//
//import java.util.List;
//
//@Slf4j
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/films")
//public class FilmController {
//
//    private final FilmService filmService;
//    private final FilmStorage filmStorage;
//
//    //добавление фильма;
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public Film createFilm(@RequestBody @Valid Film film) {
//        return filmStorage.create(film);
//    }
//
//    //обновление фильма;
//    @PutMapping
//    @ResponseStatus(HttpStatus.OK)
//    public Film updateFilm(@RequestBody @Valid Film film) {
//        return filmStorage.update(film);
//    }
//
//    //получение всех фильмов
//    @GetMapping
//    @ResponseStatus(HttpStatus.OK)
//    public List<Film> getAllFilms() {
//        return filmStorage.getAll();
//    }
//}
