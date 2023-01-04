package com.github.pablowinck.muralisdesafiospringboot.core.usecase;

import com.github.pablowinck.muralisdesafiospringboot.core.usecase.generators.ClienteGenerator;
import com.github.pablowinck.muralisdesafiospringboot.outbound.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class ListaClientesTest {

    @Autowired
    private ListaClientes listaClientes;

    @Autowired
    private ClienteRepository clienteRepository;

    @BeforeEach
    void setUp() {
        clienteRepository.deleteAll();
    }

    @Test
    @DisplayName("Deve listar todos os clientes")
    void deveListarTodosOsClientes() {
        var cliente = clienteRepository.save(ClienteGenerator.create());
        var clientes = listaClientes.execute(Optional.empty(), Pageable.unpaged());
        assertEquals(1, clientes.getTotalElements());
        assertEquals(cliente, clientes.getContent().get(0));
    }

    @Test
    @DisplayName("Deve listar todos os clientes por nome")
    void deveListarTodosOsClientesPorNome() {
        var cliente = clienteRepository.save(ClienteGenerator.create());
        clienteRepository.save(ClienteGenerator.create("aleatory-name"));
        var clientes = listaClientes.execute(Optional.of(cliente.getNome()), Pageable.unpaged());
        assertEquals(1, clientes.getTotalElements());
        assertEquals(cliente, clientes.getContent().get(0));
    }

}