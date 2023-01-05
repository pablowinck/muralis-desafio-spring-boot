package com.github.pablowinck.muralisdesafiospringboot.core.usecase;

import com.github.pablowinck.muralisdesafiospringboot.core.domain.dto.PersistClienteDto;
import com.github.pablowinck.muralisdesafiospringboot.core.domain.events.ClienteAtualizadoEvent;
import com.github.pablowinck.muralisdesafiospringboot.core.domain.exception.DomainException;
import com.github.pablowinck.muralisdesafiospringboot.core.domain.mapper.ClienteMapper;
import com.github.pablowinck.muralisdesafiospringboot.outbound.repository.ClienteRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class AtualizaCliente {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;
    private final ApplicationEventPublisher publisher;

    public AtualizaCliente(ClienteRepository clienteRepository, ClienteMapper clienteMapper, ApplicationEventPublisher publisher) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
        this.publisher = publisher;
    }

    public void execute(Integer id, PersistClienteDto dto) {
        log.info("Atualizando cliente: {}", id);
        var cliente = clienteRepository.findById(id).orElseThrow(DomainException::EX01_NotFound);
        log.info("Cliente encontrado: {}", cliente);
        cliente = clienteMapper.mapPersistClienteDtoToCliente(cliente, dto);
        clienteRepository.save(cliente);
        log.info("Cliente atualizado: {}", cliente);
        publisher.publishEvent(new ClienteAtualizadoEvent(cliente));
    }
}
