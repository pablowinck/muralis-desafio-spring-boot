package com.github.pablowinck.muralisdesafiospringboot.core.usecase;

import com.github.pablowinck.muralisdesafiospringboot.core.domain.dto.ClienteDto;
import com.github.pablowinck.muralisdesafiospringboot.core.domain.entity.Cliente;
import com.github.pablowinck.muralisdesafiospringboot.core.domain.mapper.ClienteMapper;
import com.github.pablowinck.muralisdesafiospringboot.outbound.repository.ClienteRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Log4j2
@Transactional
public class ListaClientes {
    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    public ListaClientes(ClienteRepository clienteRepository, ClienteMapper clienteMapper) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
    }

    public Page<ClienteDto> execute(Optional<String> nome, Pageable pageable) {
        var clientePage = getClientePage(nome, pageable);
        return clientePage.map(clienteMapper::toDto);
    }

    private Page<Cliente> getClientePage(Optional<String> nome, Pageable pageable) {
        if (nome.isPresent()) {
            log.info("Listando clientes: nome '{}', pageable '{}'", nome, pageable);
            Page<Cliente> clientesByNome = clienteRepository.findByNomeLikeIgnoreCase(nome.get(), pageable);
            log.info("{} clientes encontrados: nome '{}', pageable '{}'", clientesByNome.getSize(), nome, pageable);
            return clientesByNome;
        }
        log.info("Listando clientes: pageable '{}'", pageable);
        Page<Cliente> clientes = clienteRepository.findAll(pageable);
        log.info("{} clientes encontrados: pageable '{}'", clientes.getSize(), pageable);
        return clientes;
    }
}
