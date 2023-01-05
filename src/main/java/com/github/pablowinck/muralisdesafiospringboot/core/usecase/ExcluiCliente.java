package com.github.pablowinck.muralisdesafiospringboot.core.usecase;

import com.github.pablowinck.muralisdesafiospringboot.outbound.repository.ClienteRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class ExcluiCliente {
    private final ConsultaCliente consultaCliente;
    private final ClienteRepository clienteRepository;

    public ExcluiCliente(ConsultaCliente consultaCliente, ClienteRepository clienteRepository) {
        this.consultaCliente = consultaCliente;
        this.clienteRepository = clienteRepository;
    }

    public void execute(Integer id) {
        log.info("Excluindo cliente: {}", id);
        consultaCliente.execute(id);
        clienteRepository.deleteById(id);
        log.info("Cliente excluído: {}", id);
    }
}
