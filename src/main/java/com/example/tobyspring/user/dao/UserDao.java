package com.example.tobyspring.user.dao;

import com.example.tobyspring.user.domain.User;

import javax.sql.DataSource;
import java.sql.*;

public class UserDao {
    private DataSource dataSource;
    private JdbcContext jdbcContext;

    public void setDataSource(DataSource dataSource) {
        this.jdbcContext = new JdbcContext();

        this.jdbcContext.setDataSource(dataSource);

        this.dataSource = dataSource;
    }

    public void add(User user) throws SQLException {
        this.jdbcContext.workWithStatementStrategy(c -> {
            PreparedStatement ps = c.prepareStatement("insert into users(id, name, password) values(?,?,?)");

            ps.setString(1, user.getId());
            ps.setString(2, user.getName());
            ps.setString(3, user.getPassword());

            return ps;
        });
    }

    public User get(String id) throws SQLException {
        return this.jdbcContext.workWithStatementStrategyAndRowMapper(
            c -> {
                PreparedStatement ps = c.prepareStatement("select * from users where id = ?");
                ps.setString(1, id);

                return ps;
            },
            rs -> {
                User user;
                user = new User();
                user.setId(rs.getString("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                return user;
            }
        );
    }

    public void deleteAll() throws SQLException {
        this.jdbcContext.executeSql("delete from users");
    }

    public int getCount() throws SQLException {
        return this.jdbcContext.workWithStatementStrategyAndRowMapper(
            c -> {
                PreparedStatement ps = c.prepareStatement("select count(*) from users");
                return ps;
            },
            rs -> rs.getInt(1)
        );
    }
}
