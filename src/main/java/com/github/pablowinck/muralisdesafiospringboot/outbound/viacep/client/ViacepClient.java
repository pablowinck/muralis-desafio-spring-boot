package com.github.pablowinck.muralisdesafiospringboot.outbound.viacep.client;

import com.github.pablowinck.muralisdesafiospringboot.core.domain.dto.ViacepDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "viacep", url = "https://viacep.com.br/ws")
public interface ViacepClient {
    @GetMapping("/{cep}/json")
    ViacepDto getCep(@PathVariable String cep);
}
