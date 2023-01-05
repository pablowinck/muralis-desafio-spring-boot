package com.github.pablowinck.muralisdesafiospringboot.core.usecase;

import com.github.pablowinck.muralisdesafiospringboot.core.generators.ClienteGenerator;
import com.github.pablowinck.muralisdesafiospringboot.outbound.repository.ClienteRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class MapeiaEnderecoPeloCepClienteTest {

    @Autowired
    private CadastraCliente cadastraCliente;

    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    @DisplayName("Deve cadastrar um cliente, e posteriormente consultar o CEP, enriquecendo o endereco")
    void deveCadastrarUmClienteEPesquisarCep() {
        var dto = ClienteGenerator.generatePersistClienteDto();
        assertNull(dto.getLogradouro());
        cadastraCliente.execute(dto);
        var cliente = clienteRepository.findByNomeContainingIgnoreCase(dto.getNome(), Pageable.unpaged());
        assertTrue(cliente.hasContent());
        assertNotNull(cliente.getContent().get(0).getEndereco().getLogradouro());
    }

}