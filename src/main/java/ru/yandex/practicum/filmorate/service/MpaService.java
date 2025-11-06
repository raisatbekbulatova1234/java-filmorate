package ru.yandex.practicum.filmorate.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Mpa;
import ru.yandex.practicum.filmorate.storage.film.dao.MpaStorage;

import java.util.List;

@Service
@Qualifier("MpaDBService")
@Slf4j
public class MpaService {

    @Qualifier("MpaDBStorage")
    private final MpaStorage mpaDBStorage;
    private final ValidationService validationService;

    public MpaService(MpaStorage mpaDBStorage, ValidationService validationService) {
        this.mpaDBStorage = mpaDBStorage;
        this.validationService = validationService;
    }

    /**
     * Получение MPA-рейтинга по его ID.
     */
    public Mpa getMpaById(Integer mpaId) {
        validationService.checkExistMpaInDB(mpaId);
        return mpaDBStorage.getMpaById(mpaId);
    }

    /**
     * Получить список всех MPA из БД.
     */
    public List<Mpa> getAllMpa() {
        return mpaDBStorage.getAllMpa();
    }

}