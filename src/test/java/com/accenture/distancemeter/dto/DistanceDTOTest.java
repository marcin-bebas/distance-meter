package com.accenture.distancemeter.dto;

import com.accenture.distancemeter.bean.Code;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class DistanceDTOTest {

    @Autowired
    private JacksonTester<DistanceDTO> json;
    @Test
    public void testDistanceDTOToJson() throws Exception {
        Code location1 = new Code(57L,"8226BW", 52.49627549, 5.491812467);
        Code location2 = Code.builder()
                .id(7L)
                .code("1951HL")
                .latitude(52.47834711)
                .longitude(4.632574294)
                .build();

        double distanceDouble = 58.213433441027135;

        DistanceDTO distanceDTO = DistanceDTO.builder()
                .location1(location1)
                .location2(location2)
                .distance(distanceDouble)
                .build();

//        String expectedJson = "{\"location1\":{},\"location2\":{},\"distance\":10.5,\"unit\":\"km\"}";
        String expectedJson = "{\n" +
                "    \"location1\": {\n" +
                "        \"id\": 57,\n" +
                "        \"code\": \"8226BW\",\n" +
                "        \"latitude\": 52.49627549,\n" +
                "        \"longitude\": 5.491812467\n" +
                "    },\n" +
                "    \"location2\": {\n" +
                "        \"id\": 7,\n" +
                "        \"code\": \"1951HL\",\n" +
                "        \"latitude\": 52.47834711,\n" +
                "        \"longitude\": 4.632574294\n" +
                "    },\n" +
                "    \"distance\": 58.213433441027135,\n" +
                "    \"unit\": \"km\"\n" +
                "}";

        JsonContent<DistanceDTO> content = json.write(distanceDTO);

        assertThat(this.json.write(distanceDTO)).isEqualToJson(expectedJson);
        assertThat(content).hasJsonPathStringValue("unit");
        assertThat(content).hasJsonPathValue("@.distance");
        // Or use JSON path based assertions
//        assertThat(this.json.write(distanceDTO)).hasJsonPathStringValue("distance", 1000D);
//        assertThat(this.json.write(distanceDTO)).hasJsonPathStringValue("@.distance").isEqualTo(10.5);
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
