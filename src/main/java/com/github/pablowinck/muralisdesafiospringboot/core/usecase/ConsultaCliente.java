package com.github.pablowinck.muralisdesafiospringboot.core.usecase;

import com.github.pablowinck.muralisdesafiospringboot.core.domain.dto.ListaClienteDto;
import com.github.pablowinck.muralisdesafiospringboot.core.domain.exception.DomainException;
import com.github.pablowinck.muralisdesafiospringboot.core.domain.mapper.ClienteMapper;
import com.github.pablowinck.muralisdesafiospringboot.outbound.repository.ClienteRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j2
@Transactional
public class ConsultaCliente {
    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    public ConsultaCliente(ClienteRepository clienteRepository, ClienteMapper clienteMapper) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
    }

    public ListaClienteDto execute(Integer id) {
        log.info("Consultando cliente: id '{}'", id);
        var cliente = clienteRepository.findById(id).orElseThrow(() -> DomainException.EX01_NotFound(id.toString()));
        log.info("Cliente encontrado: id '{}'", id);
        return clienteMapper.toListaDto(cliente);
    }

}
