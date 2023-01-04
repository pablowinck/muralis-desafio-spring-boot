package com.github.pablowinck.muralisdesafiospringboot.core.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    private Integer id;

    @Column(nullable = false)
    private String nome;

    @CreatedDate
    private String dataCadastro;

    @Embedded
    private Endereco endereco;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente")
    private List<Contato> contatos = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(id, cliente.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
