package com.accenture.distancemeter.service;

import com.accenture.distancemeter.bean.Code;
import com.accenture.distancemeter.dto.DistanceDTO;
import com.accenture.distancemeter.repository.CodeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CodeServiceImplTest {

    private CodeServiceImpl codeService;

    @Mock
    private CodeRepository codeRepository;
    @Mock
    private DistanceServiceImpl distanceCalculator;

    @BeforeEach
    public void setup() {
        codeRepository = Mockito.mock(CodeRepository.class);
        distanceCalculator = Mockito.mock(DistanceServiceImpl.class);
        codeService = new CodeServiceImpl(codeRepository, distanceCalculator);
    }

    @Test
    public void testGetCodes() {
        List<Code> expectedCodes = new ArrayList<>();
        expectedCodes.add(new Code(1L, "12345", 52.123, 4.567));
        expectedCodes.add(new Code(2L, "67890", 51.123, 3.456));
        Page<Code> pagedResponse = new PageImpl(expectedCodes);

        Mockito.when(codeRepository.findAll(Mockito.any(PageRequest.class))).thenReturn(pagedResponse);

        List<Code> codes = codeService.getCodes(PageRequest.of(0, 10));

        assertEquals(expectedCodes, codes);
    }

    @Test
    public void testGetCodeById() {
        Long id = 1L;
        Code expectedCode = new Code(id, "12345", 52.123, 4.567);

        Mockito.when(codeRepository.findById(id)).thenReturn(Optional.of(expectedCode));

        Code code = codeService.getCodeById(id);

        assertEquals(expectedCode, code);
    }

    @Test
    public void testGetCode() {
        String code = "1L12345";
        Code expectedCode = new Code(1L, code, 52.123, 4.567);

        Mockito.when(codeRepository.findByCode(code)).thenReturn(Optional.of(expectedCode));

        Code codeObj = codeService.getCodeByCode(code);

        assertEquals(expectedCode, codeObj);
    }

    @Test
    void testGetCodeByCode() {
        String code = "CODE1";
        Code mockCode = new Code(1L, code, 52.123, 4.567);

        Mockito.when(codeRepository.findByCode(code)).thenReturn(Optional.of(mockCode));

        Code expectedCode = mockCode;
        Code actualCode = codeService.getCodeByCode(code);

        assertEquals(expectedCode, actualCode);
    }

    @Test
    public void testUpdateCode() {
        Code codeToUpdate = new Code(1L, "12345", 52.123, 4.567);
        Code updatedCode = new Code(1L, "12345", 51.234, 3.456);

        Mockito.when(codeRepository.findByCode(codeToUpdate.getCode())).thenReturn(Optional.of(codeToUpdate));
        Mockito.when(codeRepository.save(codeToUpdate)).thenReturn(updatedCode);

        Code result = codeService.updateCode(codeToUpdate);

        assertEquals(updatedCode, result);
    }
}