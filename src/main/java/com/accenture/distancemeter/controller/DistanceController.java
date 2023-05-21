package com.accenture.distancemeter.controller;

import com.accenture.distancemeter.dto.DistanceDTO;
import com.accenture.distancemeter.service.DistanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/distance")
public class DistanceController {

    private DistanceService distanceService;
    Logger logger = LoggerFactory.getLogger(DistanceController.class);

    public DistanceController(DistanceService distanceService) {
        this.distanceService = distanceService;
    }
    @GetMapping
    public ResponseEntity<DistanceDTO> getDistance(@RequestParam String code1, @RequestParam String code2){
        logger.info("Received request with postcodes {} and {}", code1, code2);
        DistanceDTO result = distanceService.getDistance(code1, code2);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
