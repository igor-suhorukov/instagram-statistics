package com.github.suhorukov.instagram.statistics;

import org.springframework.boot.builder.SpringApplicationBuilder;

public class Launcher {
    public static void main(String[] args) throws Exception {
        new SpringApplicationBuilder(SpringContext.class).web(false).run(args);
    }
}
