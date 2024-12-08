package com.example.api.config;

import com.example.core.dto.KafkaShelterDTO;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaShelterDataProducerConfig {
    private final String bootstrapServers;
    private final KafkaShelterDataConnectionParams kafkaShelterDataConnectionParams;

    public KafkaShelterDataProducerConfig(
            @Value("${spring.kafka.bootstrap-servers}") String bootrstrapServers,
            KafkaShelterDataConnectionParams kafkaShelterDataConnectionParams
    ) {
        this.kafkaShelterDataConnectionParams = kafkaShelterDataConnectionParams;
        this.bootstrapServers = bootrstrapServers;
    }

    @Bean
    public ProducerFactory<String, KafkaShelterDTO> kafkaShelterProducerFactory() {
        Map<String, Object> props = new HashMap<>();

        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, this.bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        props.put(ProducerConfig.ACKS_CONFIG, "all");

        return new DefaultKafkaProducerFactory<>(props);
    }

    @Bean
    public KafkaTemplate<String, KafkaShelterDTO> kafkaShelterDTOKafkaTemplate(ProducerFactory<String, KafkaShelterDTO> producerFactory) {
        KafkaTemplate<String, KafkaShelterDTO> kafkaTemplate = new KafkaTemplate<>(producerFactory);
        kafkaTemplate.setDefaultTopic(kafkaShelterDataConnectionParams.getTopicName());
        return kafkaTemplate;
    }

    @Bean
    public KafkaAdmin kafkaAdmin() {
        return new KafkaAdmin(
                Collections.singletonMap(
                        AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG,
                        this.bootstrapServers
                )
        );
    }

    @Bean
    public NewTopic shelterDataTopic() {
        return new NewTopic(
                this.kafkaShelterDataConnectionParams.getTopicName(),
                this.kafkaShelterDataConnectionParams.getPartitionCount(),
                this.kafkaShelterDataConnectionParams.getReplicationFactor()
        );
    }
}

