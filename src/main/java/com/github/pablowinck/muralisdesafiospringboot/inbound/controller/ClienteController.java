package com.github.pablowinck.muralisdesafiospringboot.inbound.controller;

import com.github.pablowinck.muralisdesafiospringboot.core.domain.dto.ListaClienteDto;
import com.github.pablowinck.muralisdesafiospringboot.core.usecase.ConsultaCliente;
import com.github.pablowinck.muralisdesafiospringboot.core.usecase.ListaClientes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/v1/clientes")
public class ClienteController {
    private final ListaClientes listaClientes;
    private final ConsultaCliente consultaCliente;

    public ClienteController(ListaClientes listaClientes, ConsultaCliente consultaCliente) {
        this.listaClientes = listaClientes;
        this.consultaCliente = consultaCliente;
    }

    @GetMapping
    public ResponseEntity<Page<ListaClienteDto>> lista(Optional<String> nome, Pageable pageable) {
        return ResponseEntity.ok(listaClientes.execute(nome, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListaClienteDto> consulta(@PathVariable Integer id) {
        return ResponseEntity.ok(consultaCliente.execute(id));
    }
}
