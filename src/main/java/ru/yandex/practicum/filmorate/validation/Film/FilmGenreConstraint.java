//package ru.yandex.practicum.filmorate.validation.Film;
//
//import jakarta.validation.Constraint;
//import jakarta.validation.Payload;
//
//import java.lang.annotation.*;
//
//@Documented
//@Constraint(validatedBy = FilmGenreValidator.class)
//@Target(ElementType.FIELD)
//@Retention(RetentionPolicy.RUNTIME)
//public @interface FilmGenreConstraint {
//    String message();
//
//    Class<?>[] groups() default {};
//
//    Class<? extends Payload>[] payload() default {};
//}