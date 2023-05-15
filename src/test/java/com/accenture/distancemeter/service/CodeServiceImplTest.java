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

    @BeforeEach
    public void setup() {
        codeRepository = Mockito.mock(CodeRepository.class);
        codeService = new CodeServiceImpl(codeRepository);
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
    public void testGetDistance() {
        String code1 = "12345";
        String code2 = "67890";
        Code code01 = new Code(1L, code1, 52.123, 4.567);
        Code code02 = new Code(2L, code2, 51.123, 3.456);
        double expectedDistance = 100.0;

        Mockito.when(codeRepository.findByCode(code1)).thenReturn(Optional.of(code01));
        Mockito.when(codeRepository.findByCode(code2)).thenReturn(Optional.of(code02));
        Mockito.mockStatic(DistanceCalculator.class);

        Mockito.when(DistanceCalculator.calculateDistance(code01.getLatitude(), code01.getLongitude(), code02.getLatitude(), code02.getLongitude())).thenReturn(expectedDistance);

        DistanceDTO distanceDTO = codeService.getDistance(code1, code2);

        assertEquals(code01, distanceDTO.getLocation1());
        assertEquals(code02, distanceDTO.getLocation2());
        assertEquals(expectedDistance, distanceDTO.getDistance());
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