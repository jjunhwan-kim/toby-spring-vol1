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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.mail.MailSender;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
//@ImportResource("/test-applicationContext.xml")
public class TestApplicationContext {
    @Bean
    public DataSource dataSource() {
        SimpleDriverDataSource ds = new SimpleDriverDataSource();
        ds.setDriverClass(com.mysql.cj.jdbc.Driver.class);
        ds.setUrl("jdbc:mysql://localhost/testdb");
        ds.setUsername("root");
        ds.setPassword("");
        return ds;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        DataSourceTransactionManager tm = new DataSourceTransactionManager();
        tm.setDataSource(dataSource());
        return tm;
    }

    @Bean
    public UserDao userDao() {
        UserDaoJdbc dao = new UserDaoJdbc();
        dao.setDataSource(dataSource());
        dao.setSqlService(sqlService());
        return dao;
    }

    @Bean
    public UserService userService() {
        UserServiceImpl service = new UserServiceImpl();
        service.setUserDao(userDao());
        service.setMailSender(mailSender());
        return service;
    }

    @Bean
    public UserService testUserService() {
        TestUserService testService = new TestUserService();
        testService.setUserDao(userDao());
        testService.setMailSender(mailSender());
        return testService;
    }

    @Bean
    public MailSender mailSender() {
        return new DummyMailSender();
    }

    @Bean
    public SqlService sqlService() {
        OxmSqlService sqlService = new OxmSqlService();
        sqlService.setUnmarshaller(unmarshaller());
        sqlService.setSqlRegistry(sqlRegistry());
        sqlService.setSqlmap(new ClassPathResource("/sqlmap.xml"));
        return sqlService;
    }

    @Bean
    public SqlRegistry sqlRegistry() {
        EmbeddedDbSqlRegistry sqlRegistry = new EmbeddedDbSqlRegistry();
        sqlRegistry.setDataSource(embeddedDatabase());
        return sqlRegistry;
    }

    @Bean
    public Unmarshaller unmarshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.example.tobyspring.user.sqlservice.jaxb");
        return marshaller;
    }

    @Bean
    public DataSource embeddedDatabase() {
        return new EmbeddedDatabaseBuilder()
                .setName("embeddedDatabase")
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:sqlRegistrySchema.sql")
                .build();
    }
}
