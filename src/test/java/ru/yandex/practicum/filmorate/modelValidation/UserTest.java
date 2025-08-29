package ru.yandex.practicum.filmorate.modelValidation;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.controller.UserController;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    UserController controller = new UserController();

    @Test
    public void createUserSuccessTest() {
        User user = new User(1, "email@mail.com", "1234", "Raisat", "1997-10-26");
        controller.createUser(user);
        assertEquals(user, controller.getAllUsers().getFirst());
    }

    @Test
    public void emptyEmailTest() {
        User user = new User(1, "", "1234", "Raisat", "1997-10-26");

        assertThrows(ValidationException.class, () -> controller.createUser(user));
    }

    @Test
    public void emptyEmailWithoutCharTest() {
        User user = new User(1, "emailmail.com", "1234", "Raisat", "1997-10-26");

        assertThrows(ValidationException.class, () -> controller.createUser(user));
    }

    @Test
    public void emptyLoginTest() {
        User user = new User(1, "email@mail.com", "", "Raisat", "1997-10-26");

        assertThrows(ValidationException.class, () -> controller.createUser(user));
    }

    @Test
    public void emptyLoginWithWhiteSpaceTest() {
        User user = new User(1, "email@mail.com", "1 2 3 4", "Raisat", "1997-10-26");

        assertThrows(ValidationException.class, () -> controller.createUser(user));
    }

    @Test
    public void emptyIncorrectBirthdayTest() {
        User user = new User(1, "email@mail.com", "1234", "Raisat", "2028-10-26");

        assertThrows(ValidationException.class, () -> controller.createUser(user));
    }

    /// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void testUpdateUserSuccess() {
        User user = new User(1, "email@mail.com", "1234", "Raisat", "1997-10-26");
        controller.createUser(user);

        user.setName("Belle");
        controller.updateUser(user);

        List<User> allFilms = controller.getAllUsers();
        User updatedUser = allFilms.getFirst();
        assertNotNull(updatedUser);
        assertEquals("Belle", updatedUser.getName());
    }

    @Test
    public void testUserDoesNotExist() {
        User user = new User(1, "email@mail.com", "1234", "Raisat", "1997-10-26");
        assertThrows(ValidationException.class, () -> controller.updateUser(user));

    }

    @Test
    public void testEmptyUser() {
        User user = null;
        assertThrows(ValidationException.class, () -> controller.updateUser(user));

    }

    @Test
    public void emptyLoginWithWhiteSpaceUpdateTest() {
        User user = new User(1, "email@mail.com", "1234", "Raisat", "1997-10-26");
        controller.createUser(user);
        user.setLogin("1 2 3 4");
        assertThrows(ValidationException.class, () -> controller.updateUser(user));
    }

    @Test
    public void emptyEmailWithoutCharUpdateTest() {
        User user = new User(1, "email@mail.com", "1234", "Raisat", "1997-10-26");
        controller.createUser(user);
        user.setEmail("emailmail.com");
        assertThrows(ValidationException.class, () -> controller.updateUser(user));
    }

    /// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void getAllUsersTest() {
        User user1 = new User(1, "email@mail.com", "1234", "Raisat", "1997-10-26");
        User user2 = new User(2, "email@mail.com", "1234", "Raisat", "1997-10-26");
        User user3 = new User(3, "email@mail.com", "1234", "Raisat", "1997-10-26");
        controller.createUser(user1);
        controller.createUser(user2);
        controller.createUser(user3);
        List<User> list = controller.getAllUsers();
        assertEquals(3, list.size());
    }
}
