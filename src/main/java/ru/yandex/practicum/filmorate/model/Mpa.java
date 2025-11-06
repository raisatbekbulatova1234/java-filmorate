package ru.yandex.practicum.filmorate.model;

import lombok.*;

@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class Mpa {
    Integer id;
    String name;
    String description;

}