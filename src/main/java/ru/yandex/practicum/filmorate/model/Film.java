package ru.yandex.practicum.filmorate.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor

public class Film {
    static Integer count = 1;

    @NotNull
    @NotBlank
    Integer id;
    @NotNull
    @NotBlank
    String name;

    String description;

    LocalDate releaseDate;

    @Positive
    Integer duration;

    @NotNull
    Mpa mpa;

    @JsonIgnore
    Set<Integer> likes = new HashSet<>();

    @NotNull
    Set<Genre> genres = new HashSet<>();

    /**
     * Метод генерации счётчика.
     */
    public static Integer getCount() {
        return count++;
    }
}