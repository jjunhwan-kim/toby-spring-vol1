package com.example.tobyspring.user.dao;

import com.example.tobyspring.user.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.*;

class UserDaoTest {

    @Test
    public void userDaoTest() throws SQLException, ClassNotFoundException {
//        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
        UserDao userDao = context.getBean("userDao", UserDao.class);

        User user = new User();
        user.setId("whiteship");
        user.setName("백기선");
        user.setPassword("married");

        userDao.add(user);

        System.out.println(user.getId() + " 등록 성공");

        User user2 = userDao.get(user.getId());
        if (!user.getName().equals(user2.getName())) {
            System.out.println("테스트 실패 (name)");
        }
        else if (!user.getPassword().equals(user2.getPassword())) {
            System.out.println("테스트 실패 (password)");
        }
        else {
            System.out.println("조회 테스트 성공");
        }

        assertThat(user.getId()).isEqualTo(user2.getId());
        assertThat(user.getName()).isEqualTo(user2.getName());
        assertThat(user.getPassword()).isEqualTo(user2.getPassword());
    }
}