package ru.yandex.practicum.filmorate.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;
import ru.yandex.practicum.filmorate.messages.ExceptionMessages;
import ru.yandex.practicum.filmorate.validation.Film.ReleaseDateConstraint;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Film
 */

@Data
public class Film {

    private Long id;

    @NotBlank(message = ExceptionMessages.EMPTY_NAME)
    private String name;
    @Size(max = 200, message = ExceptionMessages.MAX_DESCRIPTION)
    private String description;
    @ReleaseDateConstraint(message = ExceptionMessages.INCORRECT_DATE)
    private LocalDate releaseDate;
    @PositiveOrZero(message = ExceptionMessages.POSITIVE_DURATION)
    private int duration;
    // @FilmGenreConstraint(message = ExceptionMessages.INCORRECT_GENRES)
    // private Set<String> genres = new HashSet<>();
    //@MpaRatingConstraint(message = ExceptionMessages.EMPTY_MPA_RATING)
    // private MpaRating mpaRating;
    private Set<Long> likes = new HashSet<>();

}
