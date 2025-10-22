package ru.yandex.practicum.filmorate.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import ru.yandex.practicum.filmorate.messages.ExceptionMessages;
import ru.yandex.practicum.filmorate.validation.User.LoginConstraint;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * User.
 */

@Data
@Getter
@Setter
@Builder
public class User {
    private Long id;
    @Email(message = ExceptionMessages.INCORRECT_EMAIL)
    private String email;
    @NotBlank(message = ExceptionMessages.EMPTY_LOGIN)
    @LoginConstraint(message = ExceptionMessages.LOGIN_WITHOUT_SPACE)
    private String login;
    private String name;
    @PastOrPresent(message = ExceptionMessages.INCORRECT_BIRTHDAY)
    private LocalDate birthday;
    private Set<Long> friends;
}