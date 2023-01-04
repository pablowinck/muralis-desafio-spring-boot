package com.github.pablowinck.muralisdesafiospringboot.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@Profile("!test")
@EnableAsync
public class AsyncConfiguration {
}
