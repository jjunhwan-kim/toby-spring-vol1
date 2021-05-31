package com.example.tobyspring.user.sqlservice.updatable;

import com.example.tobyspring.user.sqlservice.UpdatableSqlRegistry;

class ConcurrentHashMapSqlRegistryTest extends AbstractUpdatableSqlRegistryTest {
    @Override
    protected UpdatableSqlRegistry createUpdatableSqlRegistry() {
        return new ConcurrentHashMapSqlRegistry();
    }
}
