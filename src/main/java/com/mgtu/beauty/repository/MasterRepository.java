package com.mgtu.beauty.repository;

import com.mgtu.beauty.entity.Master;
import com.mgtu.beauty.mappers.MasterMapper;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class MasterRepository {
    private final JdbcTemplate jdbcTemplate;

    public void save(Master master) {
        String sql = """
                INSERT INTO masters (fio) VALUES (?)
                """.trim();
        jdbcTemplate.update(sql, master.getFio());
    }

    public Master findById(Integer id) {
        String sql = """
                SELECT id, fio from masters where id = ?
                """.trim();
        return jdbcTemplate.query(sql, new MasterMapper(), id).stream().findFirst().orElse(null);
    }
}
