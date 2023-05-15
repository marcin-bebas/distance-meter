package com.accenture.distancemeter.component;

import com.accenture.distancemeter.bean.Code;
import com.accenture.distancemeter.repository.CodeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class CodeLoaderTest {

    private CodeLoader codeLoader;

    @Mock
    private CodeRepository codeRepository;

    @BeforeEach
    public void setup() {
        codeRepository = Mockito.mock(CodeRepository.class);
        codeLoader = new CodeLoader(codeRepository);
    }

    @Disabled
    @Test
    public void testLoadCodes() throws IOException {
        // Mock the Files.lines() method to return a stream of lines from the CSV file
        Path filePath =  Paths.get(System.getProperty("user.dir") + "\\src\\main\\resources\\postcodes-coordinates-NL.csv");
        Stream<String> lines = Stream.of("1,12345,52.123,4.567", "2,67890,51.123,3.456");
//        Mockito.mockStatic(Files.class);
        Mockito.when(Files.lines(filePath)).thenReturn(lines);

        // Invoke the loadCodes() method
        codeLoader.loadCodes();

        // Verify that the codeRepository.save() method is called twice with the expected arguments
        Mockito.verify(codeRepository, Mockito.times(2)).save(Mockito.any(Code.class));
    }
}