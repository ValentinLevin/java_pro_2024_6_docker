package com.example.worker.shelterdata.mapper;

import com.example.core.dto.KafkaShelterDTO;
import com.example.worker.shelterdata.model.Shelter1CModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface Shelter1CMapper {
    Shelter1CMapper INSTANCE = Mappers.getMapper(Shelter1CMapper.class);

    @Mapping(target = "createdAt", expression = "java(java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()))")
    Shelter1CModel toShelter1CModel(KafkaShelterDTO kafkaShelterDTO);
}


