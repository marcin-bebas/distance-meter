package com.accenture.distancemeter.controller;

import com.accenture.distancemeter.bean.Code;
import com.accenture.distancemeter.service.CodeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/code")
public class CodeController {
    private CodeService codeService;

    public CodeController(CodeService codeService) {
        this.codeService = codeService;
    }

    @GetMapping
    public ResponseEntity<List<Code>> getAllCodes() {
        List<Code> codes = codeService.getCodes();
        return new ResponseEntity<>(codes, HttpStatus.OK);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Code> getTodo(@PathVariable Long id) {
        return new ResponseEntity<>(codeService.getCodeById(id), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Code> updateCode(@RequestBody Code code){
        Code result = codeService.updateCode(code);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
