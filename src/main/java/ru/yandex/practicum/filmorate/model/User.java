package ru.yandex.practicum.filmorate.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder(toBuilder = true)
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @JsonIgnore
    static Integer count = 1;

    Integer id;

    String email;

    String login;

    String name;

    LocalDate birthday;

    @JsonIgnore
    Set<Integer> idsFriends = new HashSet<>();

    public static Integer getCount() {
        return count++;
    }

    /**
     * Есть ли в списке друзей друг с ID = id?
     */
    public boolean containsFriend(Integer id) {
        return idsFriends.contains(id);
    }

    /**
     * Добавить ID друга в список друзей.
     */
    public void addIdFriend(Integer id) {
        idsFriends.add(id);
    }

    /**
     * Удалить ID друга из списка друзей.
     */
    public void removeIdFriend(Integer id) {
        idsFriends.remove(id);
    }
}