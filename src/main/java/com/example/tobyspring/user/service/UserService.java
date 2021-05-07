package com.example.tobyspring.user.service;

import com.example.tobyspring.user.domain.User;

public interface UserService {
    void add(User user);
    void upgradeLevels();
}
