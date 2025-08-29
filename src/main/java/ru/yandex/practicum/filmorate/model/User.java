package ru.yandex.practicum.filmorate.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * User.
 */
@Slf4j
@Data
@AllArgsConstructor
public class User {
    int id;
    String email;
    String login;
    String name;
    String birthday;
}
