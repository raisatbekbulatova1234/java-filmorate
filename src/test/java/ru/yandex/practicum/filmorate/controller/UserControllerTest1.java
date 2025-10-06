//package ru.yandex.practicum.filmorate.controller;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//import ru.yandex.practicum.filmorate.exception.ValidationException;
//import ru.yandex.practicum.filmorate.model.User;
//
//import java.time.LocalDate;
//
//@SpringBootTest
//public class UserControllerTest {
//    private final UserController controller = new UserController();
//    private final User user = User.builder()
//            .id(1)
//            .email("yandex@yandex.ru")
//            .login("user")
//            .name("Raisat")
//            .birthday(LocalDate.of(1997, 10, 26))
//            .build();
//
//    @Test
//    void createShouldCreateUser() {
//        User thisUser = new User(1, "yandex@yandex.ru", "user", "Raisat",
//                LocalDate.of(1997, 10, 26));
//        controller.createUser(thisUser);
//
//        Assertions.assertEquals(user, thisUser);
//        Assertions.assertEquals(1, controller.getAllUsers().size());
//    }
//
//    @Test
//    void updateShouldUpdateUserData() {
//        User thisUser = new User(1, "vic@yandex.ru", "sdnkjsdksl", "Victor",
//                LocalDate.of(1976, 9, 20));
//        controller.createUser(user);
//        controller.updateUser(thisUser);
//
//        Assertions.assertEquals("vic@yandex.ru", thisUser.getEmail());
//        Assertions.assertEquals(user.getId(), thisUser.getId());
//        Assertions.assertEquals(1, controller.getAllUsers().size());
//    }
//
//    @Test
//    void createShouldCreateUserIfNameIsEmpty() {
//        User thisUser = new User(1, "mail@yandex.ru", "user", null,
//                LocalDate.of(1990, 1, 1));
//        controller.createUser(thisUser);
//
//        Assertions.assertEquals(1, thisUser.getId());
//        Assertions.assertEquals("user", thisUser.getName());
//    }
//
//    @Test
//    void createShouldThrowExceptionIfEmailIncorrect() {
//        user.setEmail("vic.mail.ru");
//
//        Assertions.assertThrows(ValidationException.class, () -> controller.createUser(user));
//        Assertions.assertEquals(0, controller.getAllUsers().size());
//    }
//
//    @Test
//    void createShouldThrowExceptionIfEmailIsEmpty() {
//        user.setEmail("");
//
//        Assertions.assertThrows(ValidationException.class, () -> controller.createUser(user));
//        Assertions.assertEquals(0, controller.getAllUsers().size());
//    }
//
//    @Test
//    void createShouldNotAddUserIfLoginIsEmpty() {
//        user.setLogin("");
//
//        Assertions.assertThrows(ValidationException.class, () -> controller.createUser(user));
//        Assertions.assertEquals(0, controller.getAllUsers().size());
//    }
//
//    @Test
//    void createShouldNotAddUserIfBirthdayIsInTheFuture() {
//        user.setBirthday(LocalDate.of(3025, 8, 28));
//
//        Assertions.assertThrows(ValidationException.class, () -> controller.createUser(user));
//        Assertions.assertEquals(0, controller.getAllUsers().size());
//    }
//
//}
