package com.example.tobyspring;

import com.example.tobyspring.user.dao.UserDao;
import com.example.tobyspring.user.service.DummyMailSender;
import com.example.tobyspring.user.service.TestUserService;
import com.example.tobyspring.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.MailSender;

@Configuration
@Profile("test")
public class TestAppContext {
    @Bean
    public UserService testUserService() {
        return new TestUserService();
    }

    @Bean
    public MailSender mailSender() {
        return new DummyMailSender();
    }
}
