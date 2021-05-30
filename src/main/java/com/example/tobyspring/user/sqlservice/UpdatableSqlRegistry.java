package com.example.tobyspring.user.sqlservice;

import java.util.Map;

public interface UpdatableSqlRegistry extends SqlRegistry {
    public void updateSql(String key, String value) throws SqlUpdateFailureException;
    public void updateSql(Map<String, String> sqlmap) throws SqlUpdateFailureException;
}
