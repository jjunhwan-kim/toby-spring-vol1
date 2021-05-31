package com.example.tobyspring.learningtest.junit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = JUnitTest.TestConfig.class)
class JUnitTest {
    @Autowired
    ApplicationContext context;

    static Set<JUnitTest> testObjects = new HashSet<>();
    static ApplicationContext contextObject = null;

    @BeforeEach
    public void setUp() {
        System.out.println("testObjects = " + testObjects);
    }

    @Test
    public void test1() {
        assertThat(testObjects).doesNotContain(this);
        testObjects.add(this);
        System.out.println(this);
        
        assertThat(contextObject == null || contextObject == this.context, is(true));
        contextObject = this.context;
    }

    @Test
    public void test2() {
        assertThat(testObjects).doesNotContain(this);
        testObjects.add(this);
        System.out.println(this);

        assertTrue(contextObject == null || contextObject == this.context);
        contextObject = this.context;
    }

    @Test
    public void test3() {
        assertThat(testObjects).doesNotContain(this);
        testObjects.add(this);
        System.out.println(this);

        assertThat(contextObject, either(is(nullValue())).or(is(this.context)));
        contextObject = this.context;
    }

    @Configuration
    static class TestConfig {

    }
}
