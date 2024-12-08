package com.example.api.service;

import com.example.api.dto.*;
import org.springframework.stereotype.Service;

@Service
public interface ShelterService {
    ShelterListResponseDTO findByParams(SearchConfigDTO searchParams);
    SingleShelterResponseDTO createShelter(ShelterDTO shelterDTO);
    ShelterListResponseDTO findAllShelters();
    BaseResponseDTO deleteShelterById(String id);
}
