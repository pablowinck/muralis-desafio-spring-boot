package com.github.pablowinck.muralisdesafiospringboot.core.usecase;

import com.github.pablowinck.muralisdesafiospringboot.core.domain.events.ClienteCadastradoEvent;
import com.github.pablowinck.muralisdesafiospringboot.core.domain.mapper.ClienteMapper;
import com.github.pablowinck.muralisdesafiospringboot.core.domain.repository.ViacepRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j2
@Transactional
public class ConsultaCepCliente {

    private final ViacepRepository viacepRepository;
    private final ClienteMapper clienteMapper;

    public ConsultaCepCliente(ViacepRepository viacepRepository, ClienteMapper clienteMapper) {
        this.viacepRepository = viacepRepository;
        this.clienteMapper = clienteMapper;
    }

    @EventListener
    @Async
    public void on(ClienteCadastradoEvent event) {
        var cliente = event.getCliente();
        log.info("Preenchendo endereco do cliente {} pelo CEP: {}", cliente.getId(), cliente.getEndereco().getCep());
        var cep = cliente.getEndereco().getCep();
        var viacepDto = viacepRepository.findByCep(cep);
        var endereco = clienteMapper.mapViacepDtoToEndereco(cliente.getEndereco(), viacepDto);
        cliente.setEndereco(endereco);
        log.info("Endereco do cliente {} preenchido com sucesso", cliente.getId());
    }

}
