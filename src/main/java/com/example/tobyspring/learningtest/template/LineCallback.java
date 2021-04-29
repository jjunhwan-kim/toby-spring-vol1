package com.example.tobyspring.learningtest.template;

import java.io.BufferedReader;
import java.io.IOException;

public interface LineCallback {
    Integer doSomethingWithLine(String line, Integer value) throws IOException;
}
