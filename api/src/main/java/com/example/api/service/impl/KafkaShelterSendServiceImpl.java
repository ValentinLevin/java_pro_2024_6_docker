package com.example.api.service.impl;

import com.example.api.mapper.KafkaShelterDTOMapper;
import com.example.api.model.ShelterModel;
import com.example.api.service.KafkaShelterSendService;
import com.example.core.dto.KafkaShelterDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaShelterSendServiceImpl implements KafkaShelterSendService {
    private final KafkaTemplate<String, KafkaShelterDTO> kafkaTemplate;

    public KafkaShelterSendServiceImpl(
            KafkaTemplate<String, KafkaShelterDTO> kafkaTemplate
    ) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void send(ShelterModel shelterModel) {
        KafkaShelterDTO kafkaShelterDTO = KafkaShelterDTOMapper.INSTANCE.toKafkaShelterDTO(shelterModel);
        kafkaTemplate.sendDefault(kafkaShelterDTO.getId(), kafkaShelterDTO).thenAccept(
                result ->
                    log.info("PRODUCER. Shelter-data was sent to kafka {}", result.getProducerRecord().value())
        );
    }
}
