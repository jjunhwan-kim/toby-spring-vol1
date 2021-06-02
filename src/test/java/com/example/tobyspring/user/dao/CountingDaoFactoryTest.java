package com.example.tobyspring.user.dao;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

class CountingDaoFactoryTest {

    //@Test
    public void countingDaoFacoryTest() throws SQLException, ClassNotFoundException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(CountingDaoFactory.class);
        UserDaoJdbc dao = context.getBean("userDao", UserDaoJdbc.class);
/*
        // DAO
        User user = new User();
        user.setId("whiteship");
        user.setName("백기선");
        user.setPassword("married");
        dao.add(user);
        User user2 = dao.get(user.getId());

        CountingConnectionMaker ccm = context.getBean("connectionMaker", CountingConnectionMaker.class);
        System.out.println("Connection count: " + ccm.getCounter());
        Assertions.assertThat(ccm.getCounter()).isEqualTo(2);
*/
    }

}