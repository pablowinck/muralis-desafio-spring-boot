package com.github.pablowinck.muralisdesafiospringboot.core.usecase;

import com.github.pablowinck.muralisdesafiospringboot.core.domain.entity.Cliente;
import com.github.pablowinck.muralisdesafiospringboot.core.domain.events.ClienteAtualizadoEvent;
import com.github.pablowinck.muralisdesafiospringboot.core.domain.events.ClienteCadastradoEvent;
import com.github.pablowinck.muralisdesafiospringboot.core.domain.mapper.ClienteMapper;
import com.github.pablowinck.muralisdesafiospringboot.core.domain.repository.ViacepRepository;
import com.github.pablowinck.muralisdesafiospringboot.outbound.repository.ClienteRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class MapeiaEnderecoPeloCepCliente {

    private final ViacepRepository viacepRepository;
    private final ClienteMapper clienteMapper;
    private final ClienteRepository clienteRepository;

    public MapeiaEnderecoPeloCepCliente(ViacepRepository viacepRepository, ClienteMapper clienteMapper, ClienteRepository clienteRepository) {
        this.viacepRepository = viacepRepository;
        this.clienteMapper = clienteMapper;
        this.clienteRepository = clienteRepository;
    }

    @EventListener
    @Async
    public void on(ClienteCadastradoEvent event) {
        this.execute(event.getCliente());
    }

    @EventListener
    @Async
    public void on(ClienteAtualizadoEvent event) {
        this.execute(event.getCliente());
    }

    private void execute(Cliente cliente) {
        log.info("Preenchendo endereco do cliente {} pelo CEP: {}", cliente.getId(), cliente.getEndereco().getCep());
        var cep = cliente.getEndereco().getCep();
        var viacepDto = viacepRepository.findBy(cep);
        var endereco = clienteMapper.mapViacepDtoToEndereco(cliente.getEndereco(), viacepDto);
        cliente.setEndereco(endereco);
        clienteRepository.save(cliente);
        log.info("Endereco do cliente {} preenchido com sucesso", cliente.getId());
    }

}
