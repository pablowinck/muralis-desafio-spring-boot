package com.github.pablowinck.muralisdesafiospringboot.core.usecase;

import com.github.pablowinck.muralisdesafiospringboot.core.domain.dto.ContatoDto;
import com.github.pablowinck.muralisdesafiospringboot.core.usecase.generators.ClienteGenerator;
import com.github.pablowinck.muralisdesafiospringboot.outbound.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
class CadastraClienteTest {

    @Autowired
    private CadastraCliente cadastraCliente;

    @Autowired
    private ClienteRepository clienteRepository;

    @BeforeEach
    void setUp() {
        clienteRepository.deleteAll();
    }

    @Test
    @DisplayName("Deve cadastrar um cliente")
    void deveCadastrarUmCliente() {
        var dto = ClienteGenerator.generateCadastraClienteDto();
        cadastraCliente.execute(dto);
        var cliente = clienteRepository.findByNomeLikeIgnoreCase(dto.getNome(), Pageable.unpaged());
        assertTrue(cliente.hasContent());
    }

    @Test
    @DisplayName("Deve cadastrar contatos do cliente")
    @Transactional
    void deveCadastrarContatosDoCliente() {
        var dto = ClienteGenerator.generateCadastraClienteDto();
        var contato = new ContatoDto();
        contato.setTipo("email");
        contato.setTexto("john.doe@email.com");
        dto.setContatos(List.of(contato));
        cadastraCliente.execute(dto);
        var cliente = clienteRepository.findByNomeLikeIgnoreCase(dto.getNome(), Pageable.unpaged());
        assertTrue(cliente.hasContent());
        var firstCliente = cliente.get().findFirst();
        assertTrue(firstCliente.isPresent());
        var contatos = firstCliente.get().getContatos();
        assertEquals(1, contatos.size());
        var firstContato = contatos.get(0);
        assertEquals("email", firstContato.getTipo());
        assertEquals("john.doe@email.com", firstContato.getTexto());
    }

}