package com.mgtu.beauty.repository;

import com.mgtu.beauty.controller.RecordController.response.GetRecordResponse;
import com.mgtu.beauty.entity.Record;
import com.mgtu.beauty.entity.TimeSlot;
import com.mgtu.beauty.mappers.TimeSlotMapper;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Repository
@AllArgsConstructor
public class RecordRepository {
    private JdbcTemplate jdbcTemplate;

    public List<TimeSlot> findFreeTimeSlots(LocalDate date, Integer masterId) {
        String sql = """
                select * from time_slots
                except
                SELECT b.* FROM records a
                left join time_slots b on a.time_slot_id = b.id
                where a.date = ?::date and a.master_id = ?
                order by 2
                """.trim();
        return jdbcTemplate.query(sql, new TimeSlotMapper(), date, masterId);
    }

    public void save(Record record) {
        String sql = """
                INSERT INTO records (date, time_slot_id, master_id, service_id, user_id) VALUES (?, ?, ?, ?, ?)
                """.trim();

        jdbcTemplate.update(sql, record.getDate(), record.getTimeSlotId(), record.getMasterId(), record.getServiceId(), record.getUserId());
    }

    public List<GetRecordResponse> findByUserPhone(String phoneFromToken) {
        String sql = """
                select records.id as record_id,
                       masters.fio as master_fio,
                       name as service_name,
                       time_start,
                       time_end,
                       date
                from records
                join masters on records.master_id = masters.id
                join services on records.service_id = services.id
                join time_slots on records.time_slot_id = time_slots.id
                join users on records.user_id = users.id
                where users.phone = ?
                """.trim();
        return jdbcTemplate.query(sql, new RowMapper<GetRecordResponse>() {
            @Override
            public GetRecordResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
                return GetRecordResponse.builder()
                        .recordId(rs.getInt("record_id"))
                        .date(rs.getDate("date").toLocalDate())
                        .master_fio(rs.getString("master_fio"))
                        .service_name(rs.getString("service_name"))
                        .time_start(rs.getTime("time_start").toLocalTime())
                        .time_end(rs.getTime("time_end").toLocalTime())
                        .build();
            }
        }, phoneFromToken);
    }

    public List<GetRecordResponse> findActive() {
        String sql = """
                select records.id  as record_id,
                       masters.fio as master_fio,
                       name        as service_name,
                       phone,
                       time_start,
                       time_end,
                       date
                from records
                         join masters on records.master_id = masters.id
                         join services on records.service_id = services.id
                         join time_slots on records.time_slot_id = time_slots.id
                         join users on records.user_id = users.id
                where records.date >= now()::date
                """.trim();
        return jdbcTemplate.query(sql, new RowMapper<GetRecordResponse>() {
            @Override
            public GetRecordResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
                return GetRecordResponse.builder()
                        .phone(rs.getString("phone"))
                        .recordId(rs.getInt("record_id"))
                        .date(rs.getDate("date").toLocalDate())
                        .master_fio(rs.getString("master_fio"))
                        .service_name(rs.getString("service_name"))
                        .time_start(rs.getTime("time_start").toLocalTime())
                        .time_end(rs.getTime("time_end").toLocalTime())
                        .build();
            }
        });
    }

    public void delete(Integer recordId) {
        String sql = """
                DELETE FROM records WHERE id = ?
                """.trim();
        jdbcTemplate.update(sql, recordId);
    }
}
