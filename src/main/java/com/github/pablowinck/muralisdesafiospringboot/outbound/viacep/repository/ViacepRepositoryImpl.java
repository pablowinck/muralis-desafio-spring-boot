package com.github.pablowinck.muralisdesafiospringboot.outbound.viacep.repository;

import com.github.pablowinck.muralisdesafiospringboot.core.domain.dto.ViacepDto;
import com.github.pablowinck.muralisdesafiospringboot.core.domain.repository.ViacepRepository;
import com.github.pablowinck.muralisdesafiospringboot.outbound.viacep.client.ViacepClient;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@Profile("!test")
public class ViacepRepositoryImpl implements ViacepRepository {

    private final ViacepClient viacepClient;

    public ViacepRepositoryImpl(ViacepClient viacepClient) {
        this.viacepClient = viacepClient;
    }

    public ViacepDto findByCep(String cep) {
        log.info("Consultando CEP: {}", cep);
        var viacepDto = viacepClient.getCep(cep);
        log.info("CEP consultado: {}", viacepDto);
        return viacepDto;
    }
}
