package com.accenture.distancemeter.dto;

import com.accenture.distancemeter.bean.Code;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DistanceDTO {
    private Code location1;
    private Code location2;
    private double distance;
    private final String unit = "km";
}
