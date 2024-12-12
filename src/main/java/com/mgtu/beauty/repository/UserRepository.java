package com.mgtu.beauty.repository;

import com.mgtu.beauty.entity.User;
import com.mgtu.beauty.mappers.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;

    public User findByPhone(String phone) {
        String sql = """
                select u.id        as user_id,
                       u.fio       as user_fio,
                       u.phone     as user_phone,
                       u.password  as user_password,
                       u.role_name as user_role,
                       ur.id       as role_id
                from users u
                         join user_role ur on u.role_name = ur.name
                where phone = ?
                """.trim();
        return jdbcTemplate.query(sql, new UserMapper(), phone).stream().findFirst().orElse(null);
    }

    public void save(User map) {
        String sql = """
                insert into users(fio, phone, password)
                VALUES (?, ?, ?)
                """.trim();
        jdbcTemplate.update(sql, map.getFio(), map.getPhone(), map.getPassword());
    }
}
