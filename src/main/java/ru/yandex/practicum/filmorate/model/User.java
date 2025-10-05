package ru.yandex.practicum.filmorate.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.yandex.practicum.filmorate.validation.User.LoginConstraint;

import java.time.LocalDate;

/**
 * User.
 */

@Data
@AllArgsConstructor
@Builder
public class User {
    private long id;
    @Email
    private String email;
    @NotBlank
    @LoginConstraint
    private String login;
    private String name;
    private LocalDate birthday;
}
