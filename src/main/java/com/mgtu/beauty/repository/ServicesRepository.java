package com.mgtu.beauty.repository;

import com.mgtu.beauty.entity.ServiceEntity;
import com.mgtu.beauty.mappers.ServiceMapper;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class ServicesRepository {
    private final JdbcTemplate jdbcTemplate;

    public void save(ServiceEntity service) {
        String sql = """
                INSERT INTO services (name, price) VALUES (?, ?)
                """.trim();
        jdbcTemplate.update(sql, service.getName(), service.getPrice());
    }

    public ServiceEntity findById(Integer id) {
        String sql = """
                SELECT id, name, price FROM services WHERE id = ?
                """.trim();
        return jdbcTemplate.query(sql, new ServiceMapper(), id).stream().findFirst().orElse(null);
    }

    public void update(ServiceEntity service) {
        String sql = """
                UPDATE services SET name = ?, price = ? WHERE id = ?
                """.trim();
        jdbcTemplate.update(sql, service.getName(), service.getPrice(), service.getId());
    }

    public void delete(Integer serviceId) {
        String sql = """
                DELETE FROM services WHERE id = ?
                """.trim();
        jdbcTemplate.update(sql, serviceId);
    }

    public List<ServiceEntity> findAll() {
        String sql = """
                SELECT id, name, price FROM services
                """.trim();
        return jdbcTemplate.query(sql, new ServiceMapper());
    }
}
