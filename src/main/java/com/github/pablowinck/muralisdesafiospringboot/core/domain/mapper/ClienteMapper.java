package com.github.pablowinck.muralisdesafiospringboot.core.domain.mapper;

import com.github.pablowinck.muralisdesafiospringboot.core.domain.dto.ClienteDto;
import com.github.pablowinck.muralisdesafiospringboot.core.domain.entity.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    @Mapping(target = "cep", source = "endereco.cep")
    @Mapping(target = "logradouro", source = "endereco.logradouro")
    @Mapping(target = "cidade", source = "endereco.cidade")
    @Mapping(target = "numero", source = "endereco.numero")
    @Mapping(target = "complemento", source = "endereco.complemento")
    @Mapping(target = "bairro", source = "endereco.bairro")
    @Mapping(target = "estado", source = "endereco.estado")
    ClienteDto toDto(Cliente cliente);
}
