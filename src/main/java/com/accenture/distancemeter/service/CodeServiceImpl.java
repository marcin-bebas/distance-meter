package com.accenture.distancemeter.service;

import com.accenture.distancemeter.bean.Code;
import com.accenture.distancemeter.dto.DistanceDTO;
import com.accenture.distancemeter.repository.CodeRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CodeServiceImpl implements CodeService {

    private CodeRepository codeRepository;

    public CodeServiceImpl(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    @Override
    public List<Code> getCodes(Pageable paging) {

        List<Code> codes = new ArrayList<>();

        codeRepository.findAll(paging).forEach(codes::add);
        return codes;
    }

    @Override
    public Code getCodeById(Long id) {
        return codeRepository.findById(id).get();
    }

    @Override
    public DistanceDTO getDistance(String code1, String code2) {
        Code code01 = codeRepository.findByCode(code1).get();
        Code code02 = codeRepository.findByCode(code2).get();
        DistanceDTO result = DistanceDTO.builder()
                .location1(code01)
                .location2(code02)
                .distance(DistanceCalculator.calculateDistance(code01.getLatitude(), code01.getLongitude(), code02.getLatitude(), code02.getLongitude()))
                .build();
        return result;
    }

    @Override
    public Code updateCode(Code code) {
        Code codeDB = codeRepository.findByCode(code.getCode()).get();
        codeDB.setLatitude(code.getLatitude());
        codeDB.setLongitude(code.getLongitude());
        return codeRepository.save(codeDB);
    }
}
