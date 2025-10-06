//package ru.yandex.practicum.filmorate.controller;
//
//import jakarta.validation.ConstraintViolation;
//import jakarta.validation.Validation;
//import jakarta.validation.Validator;
//import org.junit.jupiter.api.Test;
//import ru.yandex.practicum.filmorate.model.User;
//
//import java.time.LocalDate;
//import java.util.Set;
//
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//
//public class UserValidationTest {
//
//    @Test
//    public void testUserValidationPass() {
//        // Пример корректного пользователя
//        User validUser = User.builder()
//                .id(1L)
//                .email("user@example.com")
//                .login("user_login")
//                .name("User Name")
//                .birthday(LocalDate.of(1990, 1, 1))
//                .build();
//
//        // Проверка корректного пользователя
//        assertTrue(isValid(validUser), "Valid user should pass validation");
//    }
//
//    @Test
//    public void testUserValidationPassFail() {
//        // Пример некорректного пользователя (некорректный email)
//        User invalidUserEmail = User.builder()
//                .id(2L)
//                .email("invalid_email")
//                .login("user_login")
//                .name("User Name")
//                .birthday(LocalDate.of(1990, 1, 1))
//                .build();
//
//        // Проверка некорректного пользователя
//        assertFalse(isValid(invalidUserEmail), "Invalid email should fail validation");
//    }
//
////
////    private boolean isValid(User user) {
////        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
////        ConstraintViolations<User> violations = validator.validate(user);
////        return violations.isEmpty();
////    }
//    @Test
//    public void testLoginConstraint() {
//        // Пример корректного логина
//        User validUser = User.builder()
//                .login("valid_login")
//                .build();
//
//        // Проверка корректного логина
//        assertTrue(isValid(validUser), "Valid login should pass validation");
//
//        // Пример некорректного логина
//        User invalidUser = User.builder()
//                .login("invalid login")
//                .build();
//
//        // Проверка некорректного логина
//        assertFalse(isValid(invalidUser), "Invalid login should fail validation");
//    }
//
//    private boolean isValid(User user) {
//        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
//        Set<ConstraintViolation<User>> violations = validator.validate(user);
//        return violations.isEmpty();
//    }
//}
//
