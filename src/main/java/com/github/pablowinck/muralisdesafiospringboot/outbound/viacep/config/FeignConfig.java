package com.github.pablowinck.muralisdesafiospringboot.outbound.viacep.config;

import com.github.pablowinck.muralisdesafiospringboot.outbound.viacep.client.ViacepClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackageClasses = { ViacepClient.class })
public class FeignConfig {
}
