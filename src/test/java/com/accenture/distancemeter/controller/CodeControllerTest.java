package com.accenture.distancemeter.controller;

import com.accenture.distancemeter.bean.Code;
import com.accenture.distancemeter.service.CodeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CodeControllerTest {

    @Mock
    private CodeService codeService;

    private CodeController codeController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        codeController = new CodeController(codeService);
    }

    @Test
    public void testGetAllCodes() {
        Pageable paging = PageRequest.of(0, 100);
        List<Code> expectedCodes = Arrays.asList(new Code(), new Code());

        when(codeService.getCodes(paging)).thenReturn(expectedCodes);

        ResponseEntity<List<Code>> response = codeController.getAllCodes();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedCodes, response.getBody());
    }

    @Test
    public void testGetCodeById() {
        Long id = 1L;
        Code expectedCode = new Code();

        when(codeService.getCodeById(id)).thenReturn(expectedCode);

        ResponseEntity<Code> response = codeController.getCode(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedCode, response.getBody());
    }

    @Test
    public void testUpdateCode() {
        Code code = new Code();
        Code updatedCode = new Code();

        when(codeService.updateCode(code)).thenReturn(updatedCode);

        ResponseEntity<Code> response = codeController.updateCode(code);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedCode, response.getBody());
    }
}
