package com.github.pablowinck.muralisdesafiospringboot.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.ComponentScan;

@TestConfiguration
@ComponentScan(basePackages = { "com.github.pablowinck.muralisdesafiospringboot" })
@EnableAutoConfiguration
public class TestConfig {

}