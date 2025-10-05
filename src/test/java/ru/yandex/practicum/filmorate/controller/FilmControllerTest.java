//package ru.yandex.practicum.filmorate.controller;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//import ru.yandex.practicum.filmorate.exception.ValidationException;
//import ru.yandex.practicum.filmorate.model.Film;
//
//import java.time.Duration;
//import java.time.LocalDate;
//
//@SpringBootTest
//public class FilmControllerTest {
//    private final FilmController controller = new FilmController();
//    private final Film film = Film.builder()
//            .id(1)
//            .name("Аватар")
//            .description("Американский эпический научно-фантастический фильм 2009 года.")
//            .releaseDate(LocalDate.of(2009, 12, 18))
//            .duration(Duration.ofMinutes(162))
//            .build();
//
//    @Test
//    void createAddEqualsFilmTest() {
//        Film thisFilm = new Film(1, "Аватар", "Американский эпический научно-фантастический фильм 2009 года.",
//                LocalDate.of(2009, 12, 18), Duration.ofMinutes(162));
//        controller.createFilm(thisFilm);
//
//        Assertions.assertEquals(film, thisFilm);
//        Assertions.assertEquals(1, controller.getAllFilms().size());
//    }
//
//    @Test
//    void updateShouldUpdateFilmData() {
//        Film thisFilm = new Film(1, "Титаник", "Американская эпическая романтическая драма и фильм-катастрофа 1997 года.",
//                LocalDate.of(1997, 2, 2), Duration.ofMinutes(120));
//        controller.createFilm(film);
//        controller.updateFilm(thisFilm);
//
//        Assertions.assertEquals("Американская эпическая романтическая драма и фильм-катастрофа 1997 года.", thisFilm.getDescription());
//        Assertions.assertEquals(1, controller.getAllFilms().size());
//    }
//
//    @Test
//    void createShouldNotAddAFilmWithAnEmptyName() {
//        film.setName("");
//
//        Assertions.assertThrows(ValidationException.class, () -> controller.createFilm(film));
//        Assertions.assertEquals(0, controller.getAllFilms().size());
//    }
//
//    @Test
//    void createShouldNotAddAFilmWithDescriptionMoreThan200() {
//
//        StringBuilder str = new StringBuilder("Описание");
//        str.append(String.valueOf(str).repeat(30));
//        film.setDescription(str.toString());
//        Assertions.assertThrows(ValidationException.class, () -> controller.createFilm(film));
//        Assertions.assertEquals(0, controller.getAllFilms().size());
//    }
//
//    @Test
//    void createShouldNotAddAFilmWithDateReleaseMoreThan1895() {
//        film.setReleaseDate(LocalDate.of(1891, 2, 2));
//
//        Assertions.assertThrows(ValidationException.class, () -> controller.createFilm(film));
//        Assertions.assertEquals(0, controller.getAllFilms().size());
//    }
//
//    @Test
//    void createShouldNotAddAFilmIfDurationIsMoreThan0() {
//        film.setDuration(Duration.ofMinutes(-120));
//
//        Assertions.assertThrows(ValidationException.class, () -> controller.createFilm(film));
//        Assertions.assertEquals(0, controller.getAllFilms().size());
//    }
//
//}
