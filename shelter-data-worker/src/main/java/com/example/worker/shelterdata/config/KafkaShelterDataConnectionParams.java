package com.example.worker.shelterdata.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@PropertySource(value = "classpath:kafka-shelter-data.properties")
@Configuration
@Getter
@Setter
public class KafkaShelterDataConnectionParams {
    private String topicName;
    private Integer partitionCount;
    private Short replicationFactor;

    public KafkaShelterDataConnectionParams(
            @Value("${kafka.shelter-data.topic-name}") String topicName,
            @Value("${kafka.shelter-data.partition-count}") Integer partitionCount,
            @Value("${kafka.shelter-data.replication-factor}") Short replicationFactor
    ) {
        this.topicName = topicName;
        this.partitionCount = partitionCount;
        this.replicationFactor = replicationFactor;
    }
}
