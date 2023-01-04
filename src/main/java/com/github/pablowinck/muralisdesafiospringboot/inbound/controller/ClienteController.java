package com.github.pablowinck.muralisdesafiospringboot.inbound.controller;

import com.github.pablowinck.muralisdesafiospringboot.core.domain.dto.CadastraClienteDto;
import com.github.pablowinck.muralisdesafiospringboot.core.domain.dto.ListaClienteDto;
import com.github.pablowinck.muralisdesafiospringboot.core.usecase.CadastraCliente;
import com.github.pablowinck.muralisdesafiospringboot.core.usecase.ConsultaCliente;
import com.github.pablowinck.muralisdesafiospringboot.core.usecase.ListaClientes;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/v1/clientes")
public class ClienteController {
    private final ListaClientes listaClientes;
    private final ConsultaCliente consultaCliente;
    private final CadastraCliente cadastraCliente;

    public ClienteController(ListaClientes listaClientes, ConsultaCliente consultaCliente, CadastraCliente cadastraCliente) {
        this.listaClientes = listaClientes;
        this.consultaCliente = consultaCliente;
        this.cadastraCliente = cadastraCliente;
    }

    @GetMapping
    public ResponseEntity<Page<ListaClienteDto>> lista(Optional<String> nome, Pageable pageable) {
        return ResponseEntity.ok(listaClientes.execute(nome, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListaClienteDto> consulta(@PathVariable Integer id) {
        return ResponseEntity.ok(consultaCliente.execute(id));
    }

    @PostMapping
    public ResponseEntity<?> cadastra(@RequestBody @Valid CadastraClienteDto dto) {
        cadastraCliente.execute(dto);
        return ResponseEntity.ok().build();
    }
}
