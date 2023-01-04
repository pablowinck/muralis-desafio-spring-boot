package com.github.pablowinck.muralisdesafiospringboot.core.domain.entity;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {
    @Column(nullable = false)
    private String cep;
    private String logradouro;
    private String cidade;
    private String numero;
    private String complemento;
    private String bairro;
    private String estado;
}
