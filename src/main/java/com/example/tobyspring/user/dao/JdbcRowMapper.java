package com.example.tobyspring.user.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface JdbcRowMapper<T> {
    public T mapRow(ResultSet rs) throws SQLException;
}
