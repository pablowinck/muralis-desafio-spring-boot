package com.github.pablowinck.muralisdesafiospringboot.core.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class ListaClienteDto {
    private Integer id;
    private String nome;
    private String cep;
    private String logradouro;
    private String cidade;
    private String numero;
    private String complemento;
    private String bairro;
    private String estado;
    private List<ContatoDto> contatos;
}
