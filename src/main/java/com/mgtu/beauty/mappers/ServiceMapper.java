package com.mgtu.beauty.mappers;

import com.mgtu.beauty.entity.ServiceEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceMapper implements RowMapper<ServiceEntity> {
    @Override
    public ServiceEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        return ServiceEntity.builder()
                .name(rs.getString("name"))
                .id(rs.getInt("id"))
                .price(rs.getDouble("price"))
                .build();
    }
}
