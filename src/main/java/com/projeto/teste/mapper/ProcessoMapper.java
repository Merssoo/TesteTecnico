package com.projeto.teste.mapper;

import com.projeto.teste.dto.ProcessoDTO;
import com.projeto.teste.model.Processo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProcessoMapper {

    ProcessoMapper INSTANCE = Mappers.getMapper(ProcessoMapper.class);

    //Método converte uma entidade Processo para um objeto DTO (Data Transfer Object).
    ProcessoDTO toDTO(Processo processo);

    //Método converte um DTO (Data Transfer Object) de Processo para a entidade Processo.
    Processo toEntity(ProcessoDTO dto);
}
