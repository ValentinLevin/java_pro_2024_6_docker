package com.example.worker.shelterdata.repository;

import com.example.worker.shelterdata.model.Shelter1CModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface Shelter1CRepository extends MongoRepository<Shelter1CModel, String> {
}
