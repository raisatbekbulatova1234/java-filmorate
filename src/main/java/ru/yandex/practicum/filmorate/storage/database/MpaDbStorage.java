package ru.yandex.practicum.filmorate.storage.database;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.mapper.MpaRowMapper;
import ru.yandex.practicum.filmorate.model.Mpa;
import ru.yandex.practicum.filmorate.storage.MpaStorage;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MpaDbStorage implements MpaStorage {

    private final JdbcTemplate jdbcTemplate;
    private final MpaRowMapper mpaRowMapper = new MpaRowMapper();

    String sqlExistsById = "SELECT COUNT(*) FROM rating WHERE rating_id = ?";
    String sqlFindAll = "SELECT * FROM rating ORDER BY rating_id";
    String sqlGetById = "SELECT * FROM rating WHERE rating_id = ?";


    @Override
    public Collection<Mpa> findAll() {
        return jdbcTemplate.query(sqlFindAll, mpaRowMapper);
    }

    @Override
    public Optional<Mpa> getById(int id) {
        List<Mpa> result = jdbcTemplate.query(sqlGetById, mpaRowMapper, id);
        return result.stream().findFirst();
    }

    public boolean existsById(int id) {
        Integer count = jdbcTemplate.queryForObject(sqlExistsById, Integer.class, id);
        return count > 0;
    }

    public List<Mpa> getMpaByIds(List<Integer> ids) {
        if (ids == null || ids.isEmpty()) {
            return List.of();
        }

        String inClause = ids.stream()
                .map(x -> "?")
                .collect(Collectors.joining(","));
        String sql = String.format("SELECT * FROM mpa_ratings " +
                "WHERE id IN (%s) " +
                "ORDER BY id", inClause);

        return jdbcTemplate.query(sql, ids.toArray(), mpaRowMapper);
    }

}