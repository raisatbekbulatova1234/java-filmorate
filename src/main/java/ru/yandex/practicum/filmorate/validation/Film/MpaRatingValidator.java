//package ru.yandex.practicum.filmorate.validation.Film;
//
//import jakarta.validation.ConstraintValidator;
//import jakarta.validation.ConstraintValidatorContext;
//import ru.yandex.practicum.filmorate.model.Film;
//
//public class MpaRatingValidator
//        implements ConstraintValidator<MpaRatingConstraint, Film> {
//    @Override
//    public void initialize(MpaRatingConstraint mpaRatingConstraint) {
//    }
//
//    @Override
//    public boolean isValid(Film film,
//                           ConstraintValidatorContext cxt) {
//        return (film.getMpaRating() != null);
//    }
//}
