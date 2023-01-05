package com.github.pablowinck.muralisdesafiospringboot.core.usecase;

import com.github.pablowinck.muralisdesafiospringboot.core.domain.dto.PersistClienteDto;
import com.github.pablowinck.muralisdesafiospringboot.core.domain.events.ClienteCadastradoEvent;
import com.github.pablowinck.muralisdesafiospringboot.core.domain.mapper.ClienteMapper;
import com.github.pablowinck.muralisdesafiospringboot.outbound.repository.ClienteRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j2
@Transactional
public class CadastraCliente {
    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;
    private final ApplicationEventPublisher publisher;

    public CadastraCliente(ClienteRepository clienteRepository, ClienteMapper clienteMapper, ApplicationEventPublisher publisher) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
        this.publisher = publisher;
    }

    public void execute(PersistClienteDto persistClienteDto) {
        log.info("Cadastrando cliente: {}", persistClienteDto);
        var cliente = clienteRepository.save(clienteMapper.toEntity(persistClienteDto));
        log.info("Cliente cadastrado com sucesso: {}", cliente);
        publisher.publishEvent(new ClienteCadastradoEvent(cliente));
    }
}
