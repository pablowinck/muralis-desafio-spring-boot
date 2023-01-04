package com.github.pablowinck.muralisdesafiospringboot.core.domain.mapper;

import com.github.pablowinck.muralisdesafiospringboot.core.domain.dto.CadastraClienteDto;
import com.github.pablowinck.muralisdesafiospringboot.core.domain.dto.ListaClienteDto;
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
    ListaClienteDto toListaDto(Cliente cliente);

    @Mapping(source = "cep", target = "endereco.cep")
    @Mapping(source = "logradouro", target = "endereco.logradouro")
    @Mapping(source = "cidade", target = "endereco.cidade")
    @Mapping(source = "numero", target = "endereco.numero")
    @Mapping(source = "complemento", target = "endereco.complemento")
    @Mapping(source = "bairro", target = "endereco.bairro")
    @Mapping(source = "estado", target = "endereco.estado")
    Cliente toEntity(CadastraClienteDto dto);
}
