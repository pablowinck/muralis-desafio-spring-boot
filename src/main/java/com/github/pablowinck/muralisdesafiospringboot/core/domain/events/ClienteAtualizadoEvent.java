package com.github.pablowinck.muralisdesafiospringboot.core.domain.events;

import com.github.pablowinck.muralisdesafiospringboot.core.domain.entity.Cliente;
import org.springframework.context.ApplicationEvent;

public class ClienteAtualizadoEvent extends ApplicationEvent {
    public ClienteAtualizadoEvent(Cliente cliente) {
        super(cliente);
    }
    public Cliente getCliente() {
        return (Cliente) getSource();
    }
}
