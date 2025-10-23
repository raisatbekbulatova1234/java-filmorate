package ru.yandex.practicum.filmorate.messages;

public enum ValidationExceptionMessages {
    FRIEND_TO_FRIEND("Пользователь не может быть другом сам себе");
   // NOT_FRIENDS("Пользователи не друзья"),
   // WITHOUT_LIKE("Пользователь не ставил лайк фильму");


    private final String text;

    ValidationExceptionMessages(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
