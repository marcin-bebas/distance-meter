package com.accenture.distancemeter.service;

import com.accenture.distancemeter.bean.Code;
import com.accenture.distancemeter.dto.DistanceDTO;
import com.accenture.distancemeter.repository.CodeRepository;
import org.springframework.stereotype.Service;

@Service
public class DistanceServiceImpl implements DistanceService {
    private final static double EARTH_RADIUS = 6371; // radius in kilometers
    private CodeRepository codeRepository;

    public DistanceServiceImpl(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    public double calculateDistance(double latitude, double longitude, double latitude2, double longitude2) {
        // Using Haversine formula. See Wikipedia.
        double lon1Radians = Math.toRadians(longitude);
        double lon2Radians = Math.toRadians(longitude2);
        double lat1Radians = Math.toRadians(latitude);
        double lat2Radians = Math.toRadians(latitude2);
        double a = haversine(lat1Radians, lat2Radians) + Math.cos(lat1Radians) * Math.cos(lat2Radians) * haversine(lon1Radians, lon2Radians);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return (EARTH_RADIUS * c);
    }

    public static double haversine(double degree1, double degree2) {
        return square(Math.sin((degree1 - degree2) / 2.0));
    }

    public static double square(double x) {
        return x * x;
    }

    @Override
    public DistanceDTO getDistance(String code1, String code2) {
        Code code01 = codeRepository.findByCode(code1).get();
        Code code02 = codeRepository.findByCode(code2).get();
        DistanceDTO result = DistanceDTO.builder()
                .location1(code01)
                .location2(code02)
                .distance(calculateDistance(code01.getLatitude(), code01.getLongitude(), code02.getLatitude(), code02.getLongitude()))
                .build();
        return result;
    }
}
