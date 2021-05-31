package com.example.tobyspring.learningtest.jdk;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;

class ReflectionTest {
    @Test
    public void invokeMethod() throws Exception {
        String name = "Spring";

        // length()
        assertThat(name.length()).isEqualTo(6);

        Method lengthMethod = String.class.getMethod("length");
        assertThat(lengthMethod.invoke(name)).isEqualTo(6);
        assertThat(name.getClass().getMethod("length").invoke(name)).isEqualTo(6);

        // charAt()
        assertThat(name.charAt(0)).isEqualTo('S');

        Method charAtMethod = String.class.getMethod("charAt", int.class);
        assertThat((Character)charAtMethod.invoke(name, 0)).isEqualTo('S');
    }

    @Test
    public void classClass() throws ClassNotFoundException {
        Class c1 = String.class;
        Class c2 = new String().getClass();
        Class c3 = Class.forName("java.lang.String");


        Constructor[] constructors = c1.getConstructors();
        for (Constructor constructor : constructors) {
            System.out.println(constructor);
        }
        System.out.println();
        Method[] methods = c1.getMethods();
        for (Method method : methods) {
            System.out.println(method);
        }
    }
}
