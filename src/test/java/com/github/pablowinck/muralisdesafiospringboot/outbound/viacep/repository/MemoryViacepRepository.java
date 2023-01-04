package com.github.pablowinck.muralisdesafiospringboot.outbound.viacep.repository;

import com.github.pablowinck.muralisdesafiospringboot.core.domain.dto.ViacepDto;
import com.github.pablowinck.muralisdesafiospringboot.core.domain.repository.ViacepRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("test")
public class MemoryViacepRepository implements ViacepRepository {


    @Override
    public ViacepDto findByCep(String cep) {
        return ViacepDto.builder()
                .cep(cep)
                .logradouro("Rua dos Bobos")
                .complemento("de nº 0 até nº 100")
                .bairro("Bairro dos Bobos")
                .localidade("São Paulo")
                .uf("SP")
                .ibge("3550308")
                .gia("1004")
                .build();
    }
}
