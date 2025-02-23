package com.projeto.teste.mapper;

import com.projeto.teste.dto.ReuDTO;
import com.projeto.teste.model.Reu;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ReuMapper {

    ReuMapper INSTANCE = Mappers.getMapper(ReuMapper.class);

    //Método converte uma entidade Reu para um objeto DTO (Data Transfer Object).
    ReuDTO toDTO(Reu reu);

    //Método converte um DTO (Data Transfer Object) de Reu para a entidade Reu.
    Reu toEntity(ReuDTO dto);

    //Atualiza uma entidade Reu com os dados de um DTO ReuDTO. Este método utiliza o MapStruct para mapear os valores do DTO para a entidade,
    @Mapping(target = "id", ignore = true)
    void atualizarReuComDTO(ReuDTO dto, @MappingTarget Reu reu);
}
