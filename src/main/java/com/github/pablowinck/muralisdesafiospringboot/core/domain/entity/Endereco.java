package com.github.pablowinck.muralisdesafiospringboot.core.domain.entity;

import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
