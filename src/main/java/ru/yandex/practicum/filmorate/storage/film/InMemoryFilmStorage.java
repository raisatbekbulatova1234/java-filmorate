package ru.yandex.practicum.filmorate.storage.film;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.film.dao.FilmStorage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Qualifier("InMemoryFilmStorage")

//Используется для однозначности использования классов наследников интерфейса.
public class InMemoryFilmStorage implements FilmStorage {

    private final Map<Integer, Film> films = new HashMap<>();


    /**
     * Получить список всех фильмов.
     */
    @Override
    public List<Film> getAllFilms() {
        return new ArrayList<>(films.values());
    }


    /**
     * Добавить фильм в библиотеку.
     */
    @Override
    public Film addInStorage(Film film) {
        films.put(film.getId(), film);
        return film;
    }

    /**
     * Обновление фильма в библиотеке.
     */
    @Override
    public Film updateInStorage(Film film) {
        films.put(film.getId(), film);
        return films.get(film.getId());
    }

    /**
     * Удалить фильм из библиотеки.
     */
    @Override
    public Film removeFromStorage(Film film) {
        if (films.remove(film.getId(), film)) {
            return film;
        }
        return null;
    }

    /**
     * Метод получения фильма из библиотеки по его ID.
     */
    @Override
    public Film getFilmById(Integer id) {
        return films.getOrDefault(id, null);
    }

    /**
     * Получить фильм по названию.
     */
    @Override
    public Film getFilmByName(String name) {
        return films.values().stream().filter(f -> f.getName().equals(name)).findFirst().orElse(null);
    }

    /**
     * Найти популярные фильмы.
     */
    @Override
    public List<Film> getPopularFilms(Integer count) {
        return null;
    }

    /**
     * Удалить фильм из библиотеки.

     */
    @Override
    public void removeFromStorageById(Integer filmId) {
    }

    /**
     * Проверка наличия фильма в БД по его ID.
     */
    @Override
    public boolean isExistFilmInDB(Integer id) {
        return false;
    }
}