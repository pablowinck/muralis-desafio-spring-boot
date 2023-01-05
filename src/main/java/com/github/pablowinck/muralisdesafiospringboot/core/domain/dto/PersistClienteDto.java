package com.github.pablowinck.muralisdesafiospringboot.core.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersistClienteDto {
    @NotNull(message = "O campo 'nome' é obrigatório")
    @NotBlank(message = "O campo 'nome' não pode ser vazio")
    @Length(min = 3, max = 100, message = "O campo 'nome' deve ter entre 3 e 100 caracteres")
    private String nome;
    @NotNull(message = "O campo 'cep' é obrigatório")
    @NotBlank(message = "O campo 'cep' não pode ser vazio")
    @Pattern(regexp = "^\\d{5}-*\\d{3}$", message = "O campo 'cep' deve estar no formato '00000-000' ou '00000000'")
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
