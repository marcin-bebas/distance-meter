package com.accenture.distancemeter.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DistanceCalculatorTest {

    @Mock
    private DistanceCalculatorImpl distanceCalculator;
    @BeforeEach
    public void setup() {
        distanceCalculator = Mockito.mock(DistanceCalculatorImpl.class);
    }

    @Test
    public void testCalculateDistance() {
        double latitude1 = 52.123;
        double longitude1 = 4.567;
        double latitude2 = 51.123;
        double longitude2 = 3.456;
        double expectedDistance = 135.07;


        Mockito.when(distanceCalculator.calculateDistance(latitude1, longitude1, latitude2, longitude2)).thenReturn(expectedDistance);
        double distance = distanceCalculator.calculateDistance(latitude1, longitude1, latitude2, longitude2);

        assertEquals(expectedDistance, distance, 0.01); // Allow a small delta for floating-point precision
    }

    @Test
    public void testHaversine() {
        double degree1 = 1.23;
        double degree2 = 4.56;
        double expectedValue = 0.991;

        double result = DistanceCalculatorImpl.haversine(degree1, degree2);

        assertEquals(expectedValue, result, 0.001); // Allow a small delta for floating-point precision
    }

    @Test
    public void testSquare() {
        double x = 2.5;
        double expectedValue = 6.25;

        double result = DistanceCalculatorImpl.square(x);

        assertEquals(expectedValue, result, 0.01); // Allow a small delta for floating-point precision
    }
}