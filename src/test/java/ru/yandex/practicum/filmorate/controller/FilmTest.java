//package ru.yandex.practicum.filmorate.controller;
//
//import org.junit.jupiter.api.Test;
//import ru.yandex.practicum.filmorate.exception.ValidationException;
//import ru.yandex.practicum.filmorate.model.Film;
//
//import java.time.Duration;
//import java.time.Instant;
//import java.time.LocalDate;
//import java.time.ZoneId;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class FilmTest {
//    FilmController controller = new FilmController();
//
//    @Test
//    public void testCreateFilmSuccess() {
//        Film film = new Film(1, "Название", "Описание", Instant.now(), Duration.ofMinutes(120));
//        controller.createFilms(film);
//        assertEquals(film, controller.getAllFilms().getFirst());
//    }
//
//    @Test
//    public void emptyFieldTest() {
//        Film film = new Film(1, "", "", Instant.now(), Duration.ofMinutes(120));
//
//        assertThrows(ValidationException.class, () -> controller.createFilms(film));
//    }
//
//    @Test
//    public void testCreateIncorrectDescriptionFilm() {
//
//        StringBuilder str = new StringBuilder("Описание");
//        str.append(String.valueOf(str).repeat(30));
//        Film film = new Film(1, "Название", str.toString(), Instant.now(), Duration.ofMinutes(120));
//
//        assertThrows(ValidationException.class, () -> controller.createFilms(film));
//
//    }
//
//    @Test
//    public void testCreateNegativeDurationFilm() {
//        Film film = new Film(1, "Название", "Описание", Instant.now(), Duration.ofMinutes(-120));
//
//        assertThrows(ValidationException.class, () -> controller.createFilms(film));
//    }
//
//    @Test
//    public void testCreateIncorrectReleaseDateFilm() {
//        LocalDate minReleaseDate = LocalDate.of(1895, 12, 27);
//        Instant minTime = minReleaseDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
//        Film film = new Film(1, "Название", "Описание", minTime, Duration.ofMinutes(120));
//
//        assertThrows(ValidationException.class, () -> controller.createFilms(film));
//    }
//
//    /// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
//    @Test
//    public void testUpdateFilmSuccess() {
//        Film film = new Film(1, "Название", "Описание", Instant.now(), Duration.ofMinutes(120));
//        controller.createFilms(film);
//
//        film.setName("Новое название");
//        controller.updateFilm(film);
//
//        List<Film> allFilms = controller.getAllFilms();
//        Film updatedFilm = allFilms.getFirst();
//        assertNotNull(updatedFilm);
//        assertEquals("Новое название", updatedFilm.getName());
//    }
//
//
//    @Test
//    public void testUpdateIncorrectDescriptionFilm() {
//        Film film = new Film(1, "Название", "Описание", Instant.now(), Duration.ofMinutes(120));
//        controller.createFilms(film);
//
//        StringBuilder str = new StringBuilder();
//        str.append("description".repeat(20));
//        film.setDescription(str.toString());
//
//        assertThrows(ValidationException.class, () -> controller.updateFilm(film));
//    }
//
//    @Test
//    public void testUpdateNegativeDurationFilm() {
//        Film film = new Film(1, "Название", "Описание", Instant.now(), Duration.ofMinutes(-120));
//        film.setDuration(Duration.ofMinutes(-120));
//        assertThrows(ValidationException.class, () -> controller.updateFilm(film));
//    }
//
//    @Test
//    public void testUpdateEmptyNameFilm() {
//        Film film = new Film(1, "Название", "Описание", Instant.now(), Duration.ofMinutes(120));
//        film.setName("");
//        assertThrows(ValidationException.class, () -> controller.updateFilm(film));
//    }
//
//    @Test
//    public void testUpdateEmptyFilm() {
//
//        assertThrows(ValidationException.class, () -> controller.updateFilm(null));
//    }
//
//    /// /////////////////////////////////////////////////////////////////////////////////////////////////
//
//    @Test
//    public void testGetAllFilms() {
//        Film film1 = new Film(1, "Название1", "Описание1", Instant.now(), Duration.ofMinutes(120));
//        Film film2 = new Film(2, "Название2", "Описание2", Instant.now(), Duration.ofMinutes(120));
//        Film film3 = new Film(3, "Название3", "Описание3", Instant.now(), Duration.ofMinutes(120));
//        controller.createFilms(film1);
//        controller.createFilms(film2);
//        controller.createFilms(film3);
//        List<Film> films = controller.getAllFilms();
//        assertEquals(3, films.size());
//    }
//
//}