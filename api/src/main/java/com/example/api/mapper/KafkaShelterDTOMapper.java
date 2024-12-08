package com.example.api.mapper;

import com.example.api.model.ShelterModel;
import com.example.core.dto.KafkaShelterDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface KafkaShelterDTOMapper {
    KafkaShelterDTOMapper INSTANCE = Mappers.getMapper(KafkaShelterDTOMapper.class);

    KafkaShelterDTO toKafkaShelterDTO(ShelterModel shelterModel);
}
