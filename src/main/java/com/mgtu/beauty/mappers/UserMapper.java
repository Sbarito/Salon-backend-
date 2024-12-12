package com.mgtu.beauty.mappers;

import com.mgtu.beauty.entity.User;
import com.mgtu.beauty.entity.UserRole;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return User.builder()
                .id(rs.getInt("user_id"))
                .phone(rs.getString("user_phone"))
                .fio(rs.getString("user_fio"))
                .password(rs.getString("user_password"))
                .role(UserRole.builder()
                        .id(rs.getInt("role_id"))
                        .name(rs.getString("user_role"))
                        .build())
                .build();
    }
}
