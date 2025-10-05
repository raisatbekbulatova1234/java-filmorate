package ru.yandex.practicum.filmorate.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.yandex.practicum.filmorate.validation.Film.DescriptionConstraint;
import ru.yandex.practicum.filmorate.validation.Film.FilmDurationConstraint;
import ru.yandex.practicum.filmorate.validation.Film.ReleaseDateConstraint;

import java.time.Duration;
import java.time.LocalDate;

/**
 * Film.
 */

@Data
@AllArgsConstructor
@Builder
public class Film {

    private long id;
    @NotBlank
    private String name;
    @DescriptionConstraint
    private String description;
    @ReleaseDateConstraint
    private LocalDate releaseDate;
    @FilmDurationConstraint
    private Duration duration;
}
