package com.github.pablowinck.muralisdesafiospringboot.core.domain.repository;

import com.github.pablowinck.muralisdesafiospringboot.core.domain.dto.ViacepDto;

public interface ViacepRepository {
    ViacepDto findBy(String cep);
}
