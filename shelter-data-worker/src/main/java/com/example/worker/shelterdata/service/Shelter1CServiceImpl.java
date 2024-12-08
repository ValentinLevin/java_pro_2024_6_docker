package com.example.worker.shelterdata.service;

import com.example.worker.shelterdata.model.Shelter1CModel;
import com.example.worker.shelterdata.repository.Shelter1CRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
@Slf4j
public class Shelter1CServiceImpl implements Shelter1CService {
    private final Shelter1CRepository shelter1CRepository;
    private final Random random = new Random(System.currentTimeMillis());

    public Shelter1CServiceImpl(Shelter1CRepository shelter1CRepository) {
        this.shelter1CRepository = shelter1CRepository;
    }

    @Override
    @Transactional
    public void processShelter1CModel(Shelter1CModel shelter1CModel) {
        if (random.nextInt(1, 5) != 1) {
            throw new RuntimeException("Some exception");
        }

        Shelter1CModel savedEntity = shelter1CRepository.save(shelter1CModel);

        log.info("CONSUMER. SAVE. Saved as {}", savedEntity);
    }
}
