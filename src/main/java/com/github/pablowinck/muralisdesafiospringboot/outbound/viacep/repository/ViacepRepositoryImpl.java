package com.github.pablowinck.muralisdesafiospringboot.outbound.viacep.repository;

import com.github.pablowinck.muralisdesafiospringboot.core.domain.dto.ViacepDto;
import com.github.pablowinck.muralisdesafiospringboot.core.domain.repository.ViacepRepository;
import com.github.pablowinck.muralisdesafiospringboot.outbound.viacep.client.ViacepClient;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
@Profile("!test")
public class ViacepRepositoryImpl implements ViacepRepository {

    private final ViacepClient viacepClient;

    public ViacepRepositoryImpl(ViacepClient viacepClient) {
        this.viacepClient = viacepClient;
    }

    @Override
    @Retry(name = "viacep", fallbackMethod = "fallback")
    public ViacepDto findBy(String cep) {
        log.info("Consultando CEP: {}", cep);
        var viacepDto = viacepClient.getEnderecoBy(cep);
        log.info("CEP consultado: {}", viacepDto);
        return viacepDto;
    }

    public Optional<ViacepDto> fallback(String cep, Exception ignoredEx) {
        log.info("Erro ao consultar CEP: {} retornando vazio", cep);
        return Optional.empty();
    }
}
