package com.accenture.distancemeter.service;

import com.accenture.distancemeter.dto.DistanceDTO;

public interface DistanceService {
    DistanceDTO getDistance(String code1, String code2);
    double calculateDistance(double latitude, double longitude, double latitude2, double longitude2);
}
