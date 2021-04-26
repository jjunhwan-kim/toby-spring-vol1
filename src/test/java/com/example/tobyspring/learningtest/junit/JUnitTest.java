package com.example.tobyspring.learningtest.junit;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class JUnitTest {
    static JUnitTest testObject;
    
    @Test
    public void test1() {
        Assertions.assertThat(this).isNotSameAs(testObject);
        testObject = this;
    }

    @Test
    public void test2() {
        Assertions.assertThat(this).isNotSameAs(testObject);
        testObject = this;
    }

    @Test
    public void test3() {
        Assertions.assertThat(this).isNotSameAs(testObject);
        testObject = this;
    }
}
