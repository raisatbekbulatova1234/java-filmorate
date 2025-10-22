package ru.yandex.practicum.filmorate.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;
import ru.yandex.practicum.filmorate.messages.ExceptionMessages;
import ru.yandex.practicum.filmorate.validation.User.LoginConstraint;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * User.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;

    // ✅ Вместо Set<Long> теперь Map<друг, статус>
    private Map<Long, FriendshipStatus> friends = new HashMap<>();
    @Email(message = ExceptionMessages.INCORRECT_EMAIL)
    private String email;
    @NotBlank(message = ExceptionMessages.EMPTY_LOGIN)
    @LoginConstraint(message = ExceptionMessages.LOGIN_WITHOUT_SPACE)
    private String login;
    private String name;
    @PastOrPresent(message = ExceptionMessages.INCORRECT_BIRTHDAY)
    private LocalDate birthday;
   // private Set<Long> friends = new HashSet<>();
}