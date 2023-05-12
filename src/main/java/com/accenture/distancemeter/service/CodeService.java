package com.accenture.distancemeter.service;

import com.accenture.distancemeter.bean.Code;
import com.accenture.distancemeter.dto.DistanceDTO;

import java.util.List;

public interface CodeService {
    List<Code> getCodes();

    Code getCodeById(Long id);

    DistanceDTO getDistance(String code1, String code2);

    Code updateCode(Code code);
}
