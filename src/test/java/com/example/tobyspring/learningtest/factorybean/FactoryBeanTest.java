package com.example.tobyspring.learningtest.factorybean;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
//@ContextConfiguration(locations = "/FactoryBeanTest-context.xml")
@ContextConfiguration(classes = FactoryBeanTest.MessageFactoryBeanFactory.class)

class FactoryBeanTest {
    @Autowired
    ApplicationContext context;

    @Test
    public void getMessageFromFactoryBean() {
        Object message = context.getBean("message");
        assertThat(message).isInstanceOf(Message.class);
        assertThat(((Message)message).getText()).isEqualTo("Factory Bean");
    }

    @Test
    public void getFactoryBean() {
        Object factory = context.getBean("&message");
        assertThat(factory).isInstanceOf(MessageFactoryBean.class);
    }

    @Configuration
    static class MessageFactoryBeanFactory {
        @Bean
        public MessageFactoryBean message() {
            MessageFactoryBean messageFactoryBean = new MessageFactoryBean();
            messageFactoryBean.setText("Factory Bean");
            return messageFactoryBean;
        }
    }

}
