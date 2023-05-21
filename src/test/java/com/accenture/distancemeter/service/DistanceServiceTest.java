package com.accenture.distancemeter.service;

import com.accenture.distancemeter.bean.Code;
import com.accenture.distancemeter.dto.DistanceDTO;
import com.accenture.distancemeter.repository.CodeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DistanceServiceTest {
    @Mock
    private CodeRepository codeRepository;
    @Mock
    private DistanceServiceImpl distanceService;
    @BeforeEach
    public void setup() {
        codeRepository = Mockito.mock(CodeRepository.class);
        distanceService = Mockito.mock(DistanceServiceImpl.class);
    }

    @Test
    public void testCalculateDistance() {
        double latitude1 = 52.123;
        double longitude1 = 4.567;
        double latitude2 = 51.123;
        double longitude2 = 3.456;
        double expectedDistance = 135.07;


        Mockito.when(distanceService.calculateDistance(latitude1, longitude1, latitude2, longitude2)).thenReturn(expectedDistance);
        double distance = distanceService.calculateDistance(latitude1, longitude1, latitude2, longitude2);

        assertEquals(expectedDistance, distance, 0.01); // Allow a small delta for floating-point precision
    }

    @Test
    public void testHaversine() {
        double degree1 = 1.23;
        double degree2 = 4.56;
        double expectedValue = 0.991;

        double result = DistanceServiceImpl.haversine(degree1, degree2);

        assertEquals(expectedValue, result, 0.001); // Allow a small delta for floating-point precision
    }

    @Test
    public void testSquare() {
        double x = 2.5;
        double expectedValue = 6.25;

        double result = DistanceServiceImpl.square(x);

        assertEquals(expectedValue, result, 0.01); // Allow a small delta for floating-point precision
    }

//    @Test
//    public void testGetDistance() {
//        String code1 = "8226BW";
//        String code2 = "1951HL";
//        Code code01 = new Code(1L, code1, 52.123, 4.567);
//        Code code02 = new Code(2L, code2, 51.123, 3.456);
//        double expectedDistance = 100.0;
//
////        Mockito.when(codeRepository.findByCode(code1)).thenReturn(Optional.of(code01));
////        Mockito.when(codeRepository.findByCode(code2)).thenReturn(Optional.of(code02));
//
////        Mockito.when(distanceService.calculateDistance(code01.getLatitude(), code01.getLongitude(), code02.getLatitude(), code02.getLongitude())).thenReturn(expectedDistance);
//        Mockito.when(distanceService.getDistance(code1, code2)).thenReturn(
//                DistanceDTO.builder()
//                        .location1(code01)
//                        .location2(code02)
//                        .distance(100.0)
//                        .build()
//        );
//
//        DistanceDTO distanceDTO = distanceService.getDistance(code1, code2);
//
//        assertEquals(code01, distanceDTO.getLocation1());
//        assertEquals(code02, distanceDTO.getLocation2());
//        assertEquals(expectedDistance, distanceDTO.getDistance());
//    }

    @Test
    void testGetDistance() {
        String code1 = "8226BW";
        String code2 = "1951HL";

        Code codeObj1 = new Code(1L, code1, 52.123, 4.567);
        Code codeObj2 = new Code(2L, code2, 51.123, 3.456);

        Mockito.when(codeRepository.findByCode(code1)).thenReturn(Optional.of(codeObj1));
        Mockito.when(codeRepository.findByCode(code2)).thenReturn(Optional.of(codeObj2));

        DistanceDTO expectedDistanceDTO = DistanceDTO.builder()
                .location1(codeObj1)
                .location2(codeObj2)
                .distance(930.0405055617751)
                .build();

        Mockito.when(distanceService.getDistance(code1, code2)).thenReturn(
                DistanceDTO.builder()
                        .location1(codeObj1)
                        .location2(codeObj2)
                        .distance(930.0405055617751)
                        .build()
        );

        DistanceDTO actualDistanceDTO = distanceService.getDistance(code1, code2);

        assertEquals(codeObj1, expectedDistanceDTO.getLocation1());
        assertEquals(codeObj2, expectedDistanceDTO.getLocation2());
        assertEquals(expectedDistanceDTO, actualDistanceDTO);
    }

}