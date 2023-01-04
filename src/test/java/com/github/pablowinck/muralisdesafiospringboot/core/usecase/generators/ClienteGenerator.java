package com.github.pablowinck.muralisdesafiospringboot.core.usecase.generators;

import com.github.pablowinck.muralisdesafiospringboot.core.domain.entity.Cliente;
import com.github.pablowinck.muralisdesafiospringboot.core.domain.entity.Endereco;

import java.util.List;

public class ClienteGenerator {
    private static final List<String> names = List.of("John Doe", "Jane Doe", "John Smith", "Jane Smith");
    private static final List<String> ceps = List.of("12345678", "98765432", "21436578", "89674532");

    public static Cliente create() {
        return Cliente.builder()
                .nome(names.get(randomIndex(names.size())))
                .endereco(Endereco.builder().cep(ceps.get(randomIndex(ceps.size()))).build())
                .build();
    }

    public static Cliente create(String nome) {
        return Cliente.builder()
                .nome(nome)
                .endereco(Endereco.builder().cep(ceps.get(randomIndex(ceps.size()))).build())
                .build();
    }

    private static int randomIndex(int size) {
        return (int) (Math.random() * size);
    }
}
