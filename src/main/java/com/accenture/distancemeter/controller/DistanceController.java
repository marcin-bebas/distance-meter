package com.accenture.distancemeter.controller;

import com.accenture.distancemeter.bean.Code;
import com.accenture.distancemeter.dto.DistanceDTO;
import com.accenture.distancemeter.service.CodeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/distance")
public class DistanceController {

    private CodeService codeService;

    public DistanceController(CodeService codeService) {
        this.codeService = codeService;
    }
    @GetMapping
    public ResponseEntity<DistanceDTO> getDistance(@RequestParam String code1, @RequestParam String code2){
        DistanceDTO result = codeService.getDistance(code1, code2);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
