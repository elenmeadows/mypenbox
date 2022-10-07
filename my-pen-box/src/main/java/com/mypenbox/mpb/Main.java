package com.mypenbox.mpb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MpbApplicationLocal {

    public static void main(String[] args) {
        System.setProperty("PORT", "8080");
        SpringApplication.run(MpbApplicationLocal.class, args);
    }

}
