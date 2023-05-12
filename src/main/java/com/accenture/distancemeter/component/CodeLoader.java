package com.accenture.distancemeter.component;

import com.accenture.distancemeter.bean.Code;
import com.accenture.distancemeter.repository.CodeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

@Component
public class CodeLoader implements CommandLineRunner {
    private CodeRepository codeRepository;

    public CodeLoader(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    @Override
    public void run(String... args) {
        loadCodes();
    }

    private void loadCodes() {
        if (codeRepository.count() == 0) {
            System.out.println("Sample loading...");
            long start = System.nanoTime();
            Path filePath =  Paths.get(System.getProperty("user.dir") + "\\src\\main\\resources\\postcodes-coordinates-NL.csv");
            try {
                Files.lines(filePath)
                        .skip(1)
                        .forEach(s -> {
                    String[] lineArray = s.split(",");
                    codeRepository.save(
                            Code.builder()
                                    .id(Long.valueOf(lineArray[0]))
                                    .code(lineArray[1])
                                    .latitude(Double.valueOf(lineArray[2]))
                                    .longitude(Double.valueOf(lineArray[3]))
                                    .build()
                    );
                });
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            long duration = System.nanoTime() - start;
            long convert = TimeUnit.SECONDS.convert(duration, TimeUnit.NANOSECONDS);
            System.out.println("Sample Loaded in " + convert + " secs" );

        }
    }
}
