package com.mgtu.beauty.mappers;

import com.mgtu.beauty.entity.Master;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MasterMapper implements RowMapper<Master> {
    @Override
    public Master mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Master.builder()
                .id(rs.getInt("id"))
                .fio(rs.getString("fio"))
                .build();
    }
}
