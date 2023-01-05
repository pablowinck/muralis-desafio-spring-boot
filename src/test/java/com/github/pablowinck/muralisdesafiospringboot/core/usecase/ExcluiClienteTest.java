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
class ExcluiClienteTest {

    @Autowired
    private ExcluiCliente excluiCliente;
    @Autowired
    private ClienteRepository clienteRepository;

    @BeforeEach
    void setUp() {
        clienteRepository.deleteAll();
    }

    @Test
    @DisplayName("Deve excluir um cliente")
    void deveExcluirUmCliente() {
        var cliente = ClienteGenerator.generate();
        cliente = clienteRepository.save(cliente);
        assertTrue(clienteRepository.findAll().size() > 0);
        excluiCliente.execute(cliente.getId());
        assertTrue(clienteRepository.findAll().isEmpty());
    }

    @Test
    @DisplayName("Deve lançar exceção quando o cliente não for encontrado")
    void deveLancarExcecaoQuandoClienteNaoForEncontrado() {
        DomainException domainException = assertThrows(DomainException.class, () -> excluiCliente.execute(1111));
        assertEquals("EX-01", domainException.getCode());
        assertEquals("Recurso não encontrado para o termo '1111'", domainException.getMessage());
        assertEquals(HttpStatus.NOT_FOUND, domainException.getStatus());
    }

}