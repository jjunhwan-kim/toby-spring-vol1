package com.example.tobyspring.user.dao;

import com.example.tobyspring.user.domain.User;

import java.util.List;

public interface UserDao {
    void add(User user);
    User get(String id);
    void update(User user);
    List<User> getAll();
    void deleteAll();
    int getCount();
}
