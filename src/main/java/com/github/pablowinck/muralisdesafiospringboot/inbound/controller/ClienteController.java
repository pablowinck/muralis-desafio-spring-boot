package com.github.pablowinck.muralisdesafiospringboot.inbound.controller;

import com.github.pablowinck.muralisdesafiospringboot.core.domain.dto.PersistClienteDto;
import com.github.pablowinck.muralisdesafiospringboot.core.domain.dto.ListaClienteDto;
import com.github.pablowinck.muralisdesafiospringboot.core.usecase.AtualizaCliente;
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
    private final AtualizaCliente atualizaCliente;

    public ClienteController(ListaClientes listaClientes, ConsultaCliente consultaCliente, CadastraCliente cadastraCliente, AtualizaCliente atualizaCliente) {
        this.listaClientes = listaClientes;
        this.consultaCliente = consultaCliente;
        this.cadastraCliente = cadastraCliente;
        this.atualizaCliente = atualizaCliente;
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
    public ResponseEntity<?> cadastra(@RequestBody @Valid PersistClienteDto dto) {
        cadastraCliente.execute(dto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualiza(@PathVariable Integer id, @RequestBody @Valid PersistClienteDto dto) {
        atualizaCliente.execute(id, dto);
        return ResponseEntity.ok().build();
    }
}
