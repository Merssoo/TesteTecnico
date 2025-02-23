package com.projeto.teste.mapper;

import com.projeto.teste.dto.ReuProcessoDTO;
import com.projeto.teste.model.ReuProcesso;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ReuProcessoMapper {

    ReuProcessoMapper INSTANCE = Mappers.getMapper(ReuProcessoMapper.class);

    //Converte uma entidade `ReuProcesso` para um DTO `ReuProcessoDTO`. O método realiza o mapeamento dos objetos `Reu` e `Processo` para seus respectivos
    @Mapping(target = "reuDTO", source = "reu")
    @Mapping(target = "processoDTO", source = "processo")
    ReuProcessoDTO toDTO(ReuProcesso processo);

    //Converte uma entidade `ReuProcesso` para um DTO `ReuProcessoDTO`. O método realiza o mapeamento dos objetos `Reu` e `Processo` para seus respectivos
    @Mapping(target = "reu", source = "reuDTO")
    @Mapping(target = "processo", source = "processoDTO")
    ReuProcesso toEntity(ReuProcessoDTO dto);

}
