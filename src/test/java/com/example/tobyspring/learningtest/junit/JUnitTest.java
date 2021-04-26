package com.example.tobyspring.learningtest.junit;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

public class JUnitTest {
    static Set<JUnitTest> testObjects = new HashSet<>();

    @BeforeEach
    public void setUp() {
        System.out.println("testObjects = " + testObjects);
    }

    @Test
    public void test1() {
        Assertions.assertThat(testObjects).doesNotContain(this);
        testObjects.add(this);
        System.out.println(this);
    }

    @Test
    public void test2() {
        Assertions.assertThat(testObjects).doesNotContain(this);
        testObjects.add(this);
        System.out.println(this);
    }

    @Test
    public void test3() {
        Assertions.assertThat(testObjects).doesNotContain(this);
        testObjects.add(this);
        System.out.println(this);
    }
}
