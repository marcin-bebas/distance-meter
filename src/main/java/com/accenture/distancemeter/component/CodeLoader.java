package com.accenture.distancemeter.component;

import com.accenture.distancemeter.bean.Code;
import com.accenture.distancemeter.repository.CodeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

@Component
public class CodeLoader implements CommandLineRunner {

    Logger logger = LoggerFactory.getLogger(CodeLoader.class);
    private CodeRepository codeRepository;

    public CodeLoader(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    @Override
    public void run(String... args) {
        loadCodes();
    }

    public void loadCodes() {
        if (codeRepository.count() == 0) {
            logger.info("Sample loading...");
            long start = System.nanoTime();
            Path filePath = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "postcodes-coordinates-NL.csv");
            try (Stream<String> lines = Files.lines(filePath)) {
                lines
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
                logger.error(e.getMessage());
            }
            long duration = System.nanoTime() - start;
            long convert = TimeUnit.SECONDS.convert(duration, TimeUnit.NANOSECONDS);
            logger.info("Sample Loaded in " + convert + " secs" );

        }
    }
}
