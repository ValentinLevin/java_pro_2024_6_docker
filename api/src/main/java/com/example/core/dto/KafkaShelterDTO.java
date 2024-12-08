package com.example.core.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class KafkaShelterDTO {
    @JsonProperty("id")
    private final String id;

    @JsonProperty("name")
    private final String name;

    @JsonProperty("location")
    private final String location;

    @JsonProperty("capacity")
    private final Integer capacity;

    @JsonProperty("type")
    private final String type;

    @JsonProperty("rating")
    private final Double rating;

    @JsonProperty("isGovernmentFunded")
    private final Boolean isGovernmentFunded;

    @JsonProperty("averageAdoptionTime")
    private final Integer averageAdoptionTime;

    @JsonProperty("dailyCost")
    private final Double dailyCost;

    @JsonCreator()
    public KafkaShelterDTO(
            @JsonProperty("id") String id,
            @JsonProperty("name") String name,
            @JsonProperty("location") String location,
            @JsonProperty("capacity") Integer capacity,
            @JsonProperty("type") String type,
            @JsonProperty("rating") Double rating,
            @JsonProperty("isGovernmentFunded") Boolean isGovernmentFunded,
            @JsonProperty("averageAdoptionTime") Integer averageAdoptionTime,
            @JsonProperty("dailyCost") Double dailyCost
    ) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.capacity = capacity;
        this.type = type;
        this.rating = rating;
        this.isGovernmentFunded = isGovernmentFunded;
        this.averageAdoptionTime = averageAdoptionTime;
        this.dailyCost = dailyCost;
    }
}
