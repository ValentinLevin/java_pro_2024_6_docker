package com.example.api.service;

import com.example.api.model.ShelterModel;

public interface KafkaShelterSendService {
    void send(ShelterModel shelterModel);
}
