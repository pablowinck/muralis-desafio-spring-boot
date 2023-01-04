package com.github.pablowinck.muralisdesafiospringboot.core.usecase;

import com.github.pablowinck.muralisdesafiospringboot.core.domain.entity.Cliente;
import com.github.pablowinck.muralisdesafiospringboot.outbound.repository.ClienteRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
public class ListaClientes {
    private final ClienteRepository clienteRepository;

    public ListaClientes(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Page<Cliente> execute(Optional<String> nome, Pageable pageable) {
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
