package com.github.pablowinck.muralisdesafiospringboot.core.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "cliente", indexes = {
        @Index(name = "idx_nome", columnList = "nome"),
})
@ToString
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

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ToString.Exclude
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
