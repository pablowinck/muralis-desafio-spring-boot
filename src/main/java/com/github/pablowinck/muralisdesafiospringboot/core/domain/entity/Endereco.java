package com.github.pablowinck.muralisdesafiospringboot.core.domain.entity;

import jakarta.persistence.Column;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Endereco {
    @Column(nullable = false)
    private String cep;
    private String logradouro;
    private String cidade;
    @Column(nullable = false)
    private String numero;
    private String complemento;
    private String bairro;
    private String estado;

    public void setCep(String cep) {
        if (cep.isBlank()) return;
        this.cep = cep;
    }

    public void setLogradouro(String logradouro) {
        if (logradouro.isBlank()) return;
        this.logradouro = logradouro;
    }

    public void setCidade(String cidade) {
        if (cidade.isBlank()) return;
        this.cidade = cidade;
    }

    public void setComplemento(String complemento) {
        if (complemento.isBlank()) return;
        this.complemento = complemento;
    }

    public void setBairro(String bairro) {
        if (bairro.isBlank()) return;
        this.bairro = bairro;
    }

    public void setEstado(String estado) {
        if (estado.isBlank()) return;
        this.estado = estado;
    }
}
