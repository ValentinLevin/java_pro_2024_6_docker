package com.example.worker.shelterdata.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;

@Document("shelter1C")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Shelter1CModel {
    @Id
    private String id;
    private String name;
    private String location;
    private Integer capacity;
    private String type;
    private Double rating;
    private Boolean isGovernmentFunded;
    private Integer averageAdoptionTime;
    private Double dailyCost;
    private Timestamp createdAt;
}
