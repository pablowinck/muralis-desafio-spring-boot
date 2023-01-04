package com.github.pablowinck.muralisdesafiospringboot.core.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CadastraClienteDto {
    @NotNull(message = "O campo 'nome' é obrigatório")
    @NotBlank(message = "O campo 'nome' não pode ser vazio")
    private String nome;
    @NotNull(message = "O campo 'cep' é obrigatório")
    @NotBlank(message = "O campo 'cep' não pode ser vazio")
    private String cep;
    private String logradouro;
    private String cidade;
    @NotNull(message = "O campo 'numero' é obrigatório")
    @NotBlank(message = "O campo 'numero' não pode ser vazio")
    private String numero;
    private String complemento;
    private String bairro;
    private String estado;
    private List<ContatoDto> contatos;
}
