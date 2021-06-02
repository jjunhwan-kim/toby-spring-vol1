package com.example.tobyspring;

import com.example.tobyspring.user.dao.UserDao;
import com.example.tobyspring.user.dao.UserDaoJdbc;
import com.example.tobyspring.user.service.DummyMailSender;
import com.example.tobyspring.user.service.TestUserService;
import com.example.tobyspring.user.service.UserService;
import com.example.tobyspring.user.service.UserServiceImpl;
import com.example.tobyspring.user.sqlservice.OxmSqlService;
import com.example.tobyspring.user.sqlservice.SqlRegistry;
import com.example.tobyspring.user.sqlservice.SqlService;
import com.example.tobyspring.user.sqlservice.updatable.EmbeddedDbSqlRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
//@ImportResource("/test-applicationContext.xml")
@ComponentScan(basePackages = "com.example.tobyspring.user")
@Import(SqlServiceContext.class)
@PropertySource("/database.properties")
public class AppContext {
    @Autowired
    Environment env;

    @Bean
    public DataSource dataSource() {
        SimpleDriverDataSource ds = new SimpleDriverDataSource();

        try {
            ds.setDriverClass((Class<? extends java.sql.Driver>)Class.forName(env.getProperty("db.driverClass")));
        }
        catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        ds.setUrl(env.getProperty("db.url"));
        ds.setUsername(env.getProperty("db.username"));
        ds.setPassword(env.getProperty("db.password"));

        return ds;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        DataSourceTransactionManager tm = new DataSourceTransactionManager();
        tm.setDataSource(dataSource());
        return tm;
    }

    @Configuration
    @Profile("production")
    public static class ProductionAppContext {
        @Bean
        public MailSender mailSender() {
            JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
            mailSender.setHost("mail.mycompany.com");
            return mailSender;
        }
    }

    @Configuration
    @Profile("test")
    public static class TestAppContext {
        @Bean
        public UserService testUserService() {
            return new TestUserService();
        }

        @Bean
        public MailSender mailSender() {
            return new DummyMailSender();
        }
    }
}
