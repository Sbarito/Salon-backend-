package com.mgtu.beauty.repository;

import com.mgtu.beauty.entity.Master;
import com.mgtu.beauty.entity.MasterAndService;
import com.mgtu.beauty.mappers.MasterMapper;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
@AllArgsConstructor
public class MasterAndServiceRepository {
    private JdbcTemplate jdbcTemplate;

    public void save(MasterAndService masterAndService) {
        String sql = """
                INSERT INTO master_service (master_id, service_id) VALUES (?, ?)
                """.trim();
        jdbcTemplate.update(sql, masterAndService.getMasterId(), masterAndService.getServiceId());
    }

    public List<Master> findByServiceId(Integer serviceId) {
        String sql = """
                SELECT b.id as id, b.fio as fio
                FROM master_service a
                JOIN masters b on a.master_id = b.id
                WHERE a.service_id = ?
                """.trim();
        return jdbcTemplate.query(sql, new MasterMapper(), serviceId);
    }

    public List<Master> findAll() {
        String sql = """
                select * from masters
                """.trim();
        return jdbcTemplate.query(sql, new MasterMapper());
    }
}
