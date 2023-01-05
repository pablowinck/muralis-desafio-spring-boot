package com.github.pablowinck.muralisdesafiospringboot.core.usecase;

import com.github.pablowinck.muralisdesafiospringboot.core.domain.exception.DomainException;
import com.github.pablowinck.muralisdesafiospringboot.core.usecase.generators.ClienteGenerator;
import com.github.pablowinck.muralisdesafiospringboot.outbound.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class AtualizaClienteTest {

    @Autowired
    private AtualizaCliente atualizaCliente;
    @Autowired
    private CadastraCliente cadastraCliente;
    @Autowired
    private ClienteRepository clienteRepository;

    @BeforeEach
    void setUp() {
        clienteRepository.deleteAll();
    }

    @Test
    @DisplayName("Deve atualizar um cliente")
    void deveAtualizarUmCliente() {
        var dto = ClienteGenerator.generatePersistClienteDto();
        cadastraCliente.execute(dto);
        var cliente = clienteRepository.findAll().get(0);
        assertEquals(dto.getNome(), cliente.getNome());
        dto.setNome("Pablo Winck");
        atualizaCliente.execute(cliente.getId(), dto);
        cliente = clienteRepository.findAll().get(0);
        assertEquals("Pablo Winck", cliente.getNome());
    }

    @Test
    @DisplayName("Deve lançar exceção quando o cliente não for encontrado")
    void deveLancarExcecaoQuandoClienteNaoForEncontrado() {
        var dto = ClienteGenerator.generatePersistClienteDto();
        DomainException domainException = assertThrows(DomainException.class, () -> atualizaCliente.execute(1, dto));
        assertEquals("EX-01", domainException.getCode());
        assertEquals("Recurso não encontrado", domainException.getMessage());
        assertEquals(HttpStatus.NOT_FOUND, domainException.getStatus());
    }

}