package com.github.pablowinck.muralisdesafiospringboot.core.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableAutoConfiguration
@Configuration
@ComponentScan(basePackages = {"com.github.pablowinck.muralisdesafiospringboot"})
@EntityScan("com.github.pablowinck.muralisdesafiospringboot.core.domain.entity")
@EnableJpaAuditing
public class CoreConfiguration {
}
