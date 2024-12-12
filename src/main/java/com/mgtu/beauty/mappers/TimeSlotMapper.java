package com.mgtu.beauty.mappers;

import com.mgtu.beauty.entity.TimeSlot;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TimeSlotMapper implements RowMapper<TimeSlot> {
    @Override
    public TimeSlot mapRow(ResultSet rs, int rowNum) throws SQLException {
        return TimeSlot.builder()
                .timeStart(rs.getTime("time_start").toLocalTime())
                .timeEnd(rs.getTime("time_end").toLocalTime())
                .id(rs.getInt("id"))
                .build();
    }
}
