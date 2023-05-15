package com.accenture.distancemeter.repository;

import com.accenture.distancemeter.bean.Code;
import com.accenture.distancemeter.repository.CodeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class CodeRepositoryTest {

    @Autowired
    private CodeRepository codeRepository;

    @Test
    public void testFindByCode() {
        // Create a code and save it to the repository
        Code code = new Code();
        code.setCode("12345");
        code.setLatitude(52.123);
        code.setLongitude(4.567);
        codeRepository.save(code);

        // Invoke the findByCode() method
        Optional<Code> result = codeRepository.findByCode("12345");

        // Assert that the code is found and matches the expected values
        assertTrue(result.isPresent());
        assertEquals("12345", result.get().getCode());
        assertEquals(52.123, result.get().getLatitude());
        assertEquals(4.567, result.get().getLongitude());
    }
}