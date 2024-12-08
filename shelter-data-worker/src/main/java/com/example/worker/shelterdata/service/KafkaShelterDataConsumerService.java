package com.example.worker.shelterdata.service;

import com.example.core.dto.KafkaShelterDTO;
import com.example.worker.shelterdata.mapper.Shelter1CMapper;
import com.example.worker.shelterdata.model.Shelter1CModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@Slf4j
public class KafkaShelterDataConsumerService {
    private final Shelter1CService shelter1CService;

    public KafkaShelterDataConsumerService(
            Shelter1CService shelter1CService
    ) {
        this.shelter1CService = shelter1CService;
    }

    @KafkaListener(
            containerFactory = "kafkaShelterDTOKafkaListenerContainerFactory",
            groupId = "${spring.kafka.consumer.group-id}",
            topics = "#{kafkaShelterDataConnectionParams.getTopicName()}"
    )
    public void kafkaShelterDataListener(
            @Payload(required = false) KafkaShelterDTO kafkaShelterDTO,
            @Header(name = KafkaHeaders.RECEIVED_TOPIC, required = false) String topicName,
            @Header(value = KafkaHeaders.RECEIVED_PARTITION, required = false) Integer partition,
            @Header(value = KafkaHeaders.RECEIVED_KEY, required = false) String messageKey,
            Acknowledgment acknowledgment
    ) {
        log.info("--- key {} -----------------------------------------", messageKey);
        log.info("CONSUMER. RECEIVE. Key {}. Shelter-data-worker. Partition {}, topic {}, received data {} ", messageKey, partition, topicName, kafkaShelterDTO);

        Shelter1CModel shelter1CModel = Shelter1CMapper.INSTANCE.toShelter1CModel(kafkaShelterDTO);
        try {
            shelter1CService.processShelter1CModel(shelter1CModel);
            log.info("CONSUMER. SUCCESS. Key {}", messageKey);
            acknowledgment.acknowledge();
        } catch (Exception e) {
            acknowledgment.nack(Duration.ofMillis(0));
            log.info("CONSUMER. FAILED. Key {}", messageKey);
        }
    }
}
