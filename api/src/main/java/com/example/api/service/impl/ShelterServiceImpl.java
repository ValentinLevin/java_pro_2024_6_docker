package com.example.api.service.impl;

import com.example.api.dto.*;
import com.example.api.exception.ShelterNotFoundException;
import com.example.api.mapper.ShelterMapper;
import com.example.api.service.KafkaShelterSendService;
import com.example.api.model.QShelterModel;
import com.example.api.model.ShelterModel;
import com.example.api.repository.ShelterRepository;
import com.example.api.service.ShelterService;
import com.example.api.utils.OrderParamUtils;
import com.example.api.utils.SearchParamUtils;
import com.querydsl.core.BooleanBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ShelterServiceImpl implements ShelterService {
    private final ShelterRepository shelterRepository;
    private final KafkaShelterSendService kafkaShelterSendService;

    public ShelterServiceImpl(
            ShelterRepository shelterRepository,
            KafkaShelterSendService kafkaShelterSendService
    ) {
        this.shelterRepository = shelterRepository;
        this.kafkaShelterSendService = kafkaShelterSendService;
    }

    @Override
    public ShelterListResponseDTO findByParams(SearchConfigDTO searchParams) {
        QShelterModel qShelterModel = QShelterModel.shelterModel;

        BooleanBuilder filterPredicate =
                SearchParamUtils.generateSearchPredicate(
                        searchParams.getFilterParams(),
                        qShelterModel,
                        QShelterModel.class
                );

        Pageable pageable = OrderParamUtils.generateOrderSpecifiers(
                searchParams.getSortParams(),
                searchParams.getPageNumber() - 1,
                searchParams.getPageSize()
        );

        List<ShelterDTO> items = new ArrayList<>();

        shelterRepository.findAll(filterPredicate, pageable)
                .forEach(shelterModel -> items.add(ShelterMapper.INSTANCE.toDTO(shelterModel)));

        return new ShelterListResponseDTO(items);
    }

    @Override
    @Transactional
    public SingleShelterResponseDTO createShelter(ShelterDTO shelterDTO) {
        ShelterModel shelterModel = ShelterMapper.INSTANCE.toEntity(shelterDTO);
        shelterModel = shelterRepository.save(shelterModel);
        if (shelterModel.getIsGovernmentFunded()) {
            kafkaShelterSendService.send(shelterModel);
        }
        return new SingleShelterResponseDTO(ShelterMapper.INSTANCE.toDTO(shelterModel));
    }

    @Override
    public ShelterListResponseDTO findAllShelters() {
        List<ShelterDTO> items = shelterRepository.findAll().stream()
                .map(ShelterMapper.INSTANCE::toDTO)
                .toList();
        return new ShelterListResponseDTO(items);
    }

    @Override
    @Transactional
    public BaseResponseDTO deleteShelterById(String id) {
        if (!shelterRepository.existsById(id)) {
            throw new ShelterNotFoundException(id);
        }
        shelterRepository.deleteById(id);
        return new BaseResponseDTO();
    }
}
