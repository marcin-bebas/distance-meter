package com.accenture.distancemeter.service;

import com.accenture.distancemeter.bean.Code;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CodeService {
    List<Code> getCodes(Pageable paging);

    Code getCodeById(Long id);

    Code getCodeByCode(String code);

    Code updateCode(Code code);
}
