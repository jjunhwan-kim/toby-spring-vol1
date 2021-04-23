package com.example.tobyspring.user.dao;

import com.example.tobyspring.user.dao.ConnectionMaker;
import com.example.tobyspring.user.dao.DConnectionMaker;
import com.example.tobyspring.user.dao.DaoFactory;
import com.example.tobyspring.user.dao.UserDao;
import com.example.tobyspring.user.domain.User;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.*;

class UserDaoTest {

    @Test
    public void userDaoTest() throws SQLException, ClassNotFoundException {
        DaoFactory daoFactory = new DaoFactory();
        UserDao userDao = daoFactory.userDao();

        User user = new User();
        user.setId("whiteship");
        user.setName("백기선");
        user.setPassword("married");

        userDao.add(user);

        System.out.println(user.getId() + " 등록 성공");

        User user2 = userDao.get(user.getId());
        System.out.println(user2.getName());
        System.out.println(user2.getPassword());
        System.out.println(user2.getId() + " 조회 성공");

        assertThat(user.getId()).isEqualTo(user2.getId());
        assertThat(user.getName()).isEqualTo(user2.getName());
        assertThat(user.getPassword()).isEqualTo(user2.getPassword());
    }
}