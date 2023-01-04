package com.github.pablowinck.muralisdesafiospringboot.core.domain.repository;

import com.github.pablowinck.muralisdesafiospringboot.core.domain.dto.ViacepDto;

import java.util.Optional;

public interface ViacepRepository {
    Optional<ViacepDto> findByCep(String cep);
}
