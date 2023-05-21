package com.accenture.distancemeter.service;

import com.accenture.distancemeter.bean.Code;
import com.accenture.distancemeter.repository.CodeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

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
    void should_find_and_return_one_code_by_id() {
        // Arrange
        Code expectedCode = Code.builder()
                .id(1L)
                .code("1234")
                .longitude(1.23)
                .latitude(2.23)
                .build();
        when(codeRepository.findById(anyLong())).thenReturn(Optional.of(expectedCode));

        // Act
        Code actual = codeService.getCodeById(getRandomLong());

        // Assert
        assertThat(actual).usingRecursiveComparison().isEqualTo(expectedCode);
        verify(codeRepository, times(1)).findById(anyLong());
        verifyNoMoreInteractions(codeRepository);
    }

    @Test
    void should_not_found_a_code_that_doesnt_exists_by_id() {
        // Arrange
        when(codeRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Act & Assert
        Assertions.assertThrows(NoSuchElementException.class, () -> codeService.getCodeById(getRandomLong()));
        verify(codeRepository, times(1)).findById(anyLong());
        verifyNoMoreInteractions(codeRepository);
    }

    @Test
    void should_find_and_return_one_code_by_code() {
        // Arrange
        Code expectedCode = Code.builder()
                .id(1L)
                .code("1234")
                .longitude(1.23)
                .latitude(2.23)
                .build();
        when(codeRepository.findByCode("123")).thenReturn(Optional.of(expectedCode));

        // Act
        Code actual = codeService.getCodeByCode("123");

        // Assert
        assertThat(actual).usingRecursiveComparison().isEqualTo(expectedCode);
        verify(codeRepository, times(1)).findByCode("123");
        verifyNoMoreInteractions(codeRepository);
    }

    @Test
    void should_not_found_a_code_that_doesnt_exists_by_code() {
        // Arrange
        when(codeRepository.findByCode(anyString())).thenReturn(Optional.empty());

        // Act & Assert
        Assertions.assertThrows(NoSuchElementException.class, () -> codeService.getCodeByCode(anyString()));
        verify(codeRepository, times(1)).findByCode(anyString());
        verifyNoMoreInteractions(codeRepository);
    }

    @Test
    void should_find_and_return_all_codes() {
        List<Code> expectedCodes = new ArrayList<>();
        expectedCodes.add(
                Code.builder()
                        .id(1L)
                        .code("1234")
                        .longitude(1.23)
                        .latitude(2.23)
                        .build()
        );
        expectedCodes.add(
                Code.builder()
                        .id(2L)
                        .code("67890")
                        .longitude(51.123)
                        .latitude(3.456)
                        .build()
        );
        Page<Code> pagedResponse = new PageImpl(expectedCodes);
        Pageable paging = PageRequest.of(0, 100);
        // Arrange
        when(codeRepository.findAll(paging)).thenReturn(pagedResponse);

        // Act & Assert
        assertThat(codeService.getCodes(paging)).hasSize(2);
        verify(codeRepository, times(1)).findAll(Mockito.any(PageRequest.class));
        verifyNoMoreInteractions(codeRepository);
    }

    @Test
    void should_save_one_code() {
        // Arrange
        Code codeToSave = Code.builder()
                .id(1L)
                .code("1234")
                .longitude(12.23)
                .latitude(21.23)
                .build();
        Code updatedCode = Code.builder()
                .id(1L)
                .code("1234")
                .longitude(12.23)
                .latitude(21.23)
                .build();
        when(codeRepository.findByCode(codeToSave.getCode())).thenReturn(Optional.of(codeToSave));
        when(codeRepository.save(any(Code.class))).thenReturn(updatedCode);

        // Act
        Code actual = codeService.updateCode(codeToSave);

        // Assert
        assertThat(actual).usingRecursiveComparison().isEqualTo(codeToSave);
        verify(codeRepository, times(1)).findByCode(anyString());
        verify(codeRepository, times(1)).save(any(Code.class));
        verifyNoMoreInteractions(codeRepository);
    }

    private long getRandomLong() {
        return new Random().longs(1, 10).findFirst().getAsLong();
    }
}