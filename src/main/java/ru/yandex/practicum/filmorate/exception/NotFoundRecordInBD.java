package ru.yandex.practicum.filmorate.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NotFoundRecordInBD extends RuntimeException {

    public NotFoundRecordInBD(String message) {
        super(message);
    }
}