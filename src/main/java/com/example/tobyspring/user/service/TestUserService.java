package com.example.tobyspring.user.service;

import com.example.tobyspring.user.domain.User;

import java.util.List;

public class TestUserService extends UserServiceImpl {
    private String id = "madnite1";

    @Override
    protected void upgradeLevel(User user) {
        if (user.getId().equals(this.id)) throw new TestUserServiceException();
        super.upgradeLevel(user);
    }

    @Override
    public List<User> getAll() {
        for (User user : super.getAll()) {
            super.update(user);
        }
        return null;
    }
}
