package com.example.tobyspring.user.dao;

import com.example.tobyspring.user.domain.Level;
import com.example.tobyspring.user.domain.User;
import com.example.tobyspring.user.sqlservice.SqlService;
import com.mysql.cj.exceptions.MysqlErrorNumbers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
public class UserDaoJdbc implements UserDao {
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    private SqlService sqlService;

    private RowMapper<User> userMapper = new RowMapper<User>() {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getString("id"));
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setLevel(Level.valueOf(rs.getInt("level")));
            user.setLogin(rs.getInt("login"));
            user.setRecommend(rs.getInt("recommend"));
            return user;
        }
    };

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void add(User user) {
        this.jdbcTemplate.update(sqlService.getSql("userAdd"), user.getId(), user.getName(), user.getPassword(),user.getEmail(), user.getLevel().intValue(), user.getLogin(), user.getRecommend());
    }

    /*
    public void add(User user) throws DuplicateUserIdException {
        try {
            this.jdbcTemplate.update("insert into users(id, name, password) values(?,?,?)", user.getId(), user.getName(), user.getPassword());
        }
        catch (SQLException e) {
            if (e.getErrorCode() == MysqlErrorNumbers.ER_DUP_ENTRY)
                throw new DuplicateUserIdException(e);  // 예외 전환
            else
                throw new RuntimeException(e);          // 예외 포장
        }
    }
    */

    @Override
    public User get(String id) {
        return this.jdbcTemplate.queryForObject(sqlService.getSql("userGet"), this.userMapper, id);
    }

    @Override
    public void update(User user) {
        this.jdbcTemplate.update(sqlService.getSql("userUpdate"), user.getName(), user.getPassword(), user.getEmail(), user.getLevel().intValue(), user.getLogin(), user.getRecommend(), user.getId());
    }

    @Override
    public void deleteAll() {
        this.jdbcTemplate.update(sqlService.getSql("userDeleteAll"));
    }

    @Override
    public int getCount() {
        Integer value = this.jdbcTemplate.queryForObject(sqlService.getSql("userGetCount"), Integer.class);
        if (value == null) return 0;
        return value;
    }

    @Override
    public List<User> getAll() {
        return this.jdbcTemplate.query(sqlService.getSql("userGetAll"), this.userMapper);
    }
}
