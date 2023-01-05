package com.github.pablowinck.muralisdesafiospringboot.core.usecase;

import com.github.pablowinck.muralisdesafiospringboot.core.domain.exception.DomainException;
import com.github.pablowinck.muralisdesafiospringboot.core.generators.ClienteGenerator;
import com.github.pablowinck.muralisdesafiospringboot.outbound.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class ConsultaClienteTest {

    @Autowired
    private ConsultaCliente consultaCliente;

    @Autowired
    private ClienteRepository clienteRepository;

    @BeforeEach
    void setUp() {
        clienteRepository.deleteAll();
    }

    @Test
    @DisplayName("Deve consultar um cliente")
    void deveConsultarUmCliente() {
        var cliente = clienteRepository.save(ClienteGenerator.generate());
        var clienteDto = consultaCliente.execute(cliente.getId());
        assertNotNull(clienteDto);
        assertEquals(cliente.getId(), clienteDto.getId());
    }

    @Test
    @DisplayName("Deve lançar exceção quando o cliente não for encontrado")
    void deveLancarExcecaoQuandoOClienteNaoForEncontrado() {
        DomainException domainException = assertThrows(DomainException.class, () -> consultaCliente.execute(1111111111));
        assertEquals("EX-01", domainException.getCode());
        assertEquals("Recurso não encontrado para o termo '1111111111'", domainException.getMessage());
        assertEquals(404, domainException.getStatus().value());
    }

}