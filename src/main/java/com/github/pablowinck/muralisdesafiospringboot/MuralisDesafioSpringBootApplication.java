package com.github.pablowinck.muralisdesafiospringboot;

import com.github.pablowinck.muralisdesafiospringboot.core.config.CoreConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MuralisDesafioSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoreConfiguration.class, args);
    }

}
