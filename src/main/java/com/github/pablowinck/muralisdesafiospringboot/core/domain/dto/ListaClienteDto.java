package com.github.pablowinck.muralisdesafiospringboot.core.domain.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class ListaClienteDto {
    private Integer id;
    @NotNull(message = "O campo 'nome' é obrigatório")
    private String nome;
    @NotNull(message = "O campo 'cep' é obrigatório")
    private String cep;
    private String logradouro;
    private String cidade;
    private String numero;
    private String complemento;
    private String bairro;
    private String estado;
    private List<ContatoDto> contatos;
}
