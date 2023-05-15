package com.accenture.distancemeter.dto;

import com.accenture.distancemeter.bean.Code;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class DistanceDTOTest {

    @Autowired
    private JacksonTester<DistanceDTO> json;
    @Test
    public void testDistanceDTOToJson() throws Exception {
        Code location1 = new Code();
        Code location2 = new Code();
        double distance = 10.5;

        DistanceDTO distanceDTO = DistanceDTO.builder()
                .location1(location1)
                .location2(location2)
                .distance(distance)
                .build();

        String expectedJson = "{\"location1\":{},\"location2\":{},\"distance\":10.5,\"unit\":\"km\"}";

        assertThat(this.json.write(distanceDTO)).isEqualToJson(expectedJson);
        // Or use JSON path based assertions
//        assertThat(this.json.write(distanceDTO)).hasJsonPathStringValue("@.distance");
//        assertThat(this.json.write(distanceDTO)).extractingJsonPathStringValue("@.distance").isEqualTo(10.5);

//        ObjectMapper objectMapper = new ObjectMapper();
//        String json = objectMapper.writeValueAsString(distanceDTO);
//
//        String expectedJson = "{\"location1\":{},\"location2\":{},\"distance\":10.5,\"unit\":\"km\"}";
//
//        assertEquals(expectedJson, json);
    }

    @Test
    public void testJsonToDistanceDTO() throws Exception {
        String content = "{\"location1\":{},\"location2\":{},\"distance\":10.5,\"unit\":\"km\"}";
        assertThat(this.json.parse(content))
                .isEqualTo(new DistanceDTO(new Code(),new Code(),10.5));
        assertThat(this.json.parseObject(content).getDistance()).isEqualTo(10.5);

//        String json = "{\"location1\":{},\"location2\":{},\"distance\":10.5,\"unit\":\"km\"}";

//        ObjectMapper objectMapper = new ObjectMapper();
//        DistanceDTO distanceDTO = objectMapper.readValue(json, DistanceDTO.class);
//
//        Code location1 = distanceDTO.getLocation1();
//        Code location2 = distanceDTO.getLocation2();
//        double distance = distanceDTO.getDistance();
//        String unit = distanceDTO.getUnit();
//
//        assertEquals(10.5, distance);
//        assertEquals("km", unit);
        // Add assertions for location1 and location2 if needed
    }
}
