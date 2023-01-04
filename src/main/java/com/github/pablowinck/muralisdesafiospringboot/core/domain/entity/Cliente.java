package com.github.pablowinck.muralisdesafiospringboot.core.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "cliente", indexes = {
        @Index(name = "idx_nome", columnList = "nome"),
})
public class Cliente {
    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private String nome;

    @CreatedDate
    private String dataCadastro;

    @Embedded
    private Endereco endereco;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "cliente")
    private List<Contato> contato = new ArrayList<>();
}
