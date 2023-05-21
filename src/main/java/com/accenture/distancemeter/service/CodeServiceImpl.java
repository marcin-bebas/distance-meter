package com.accenture.distancemeter.service;

import com.accenture.distancemeter.bean.Code;
import com.accenture.distancemeter.repository.CodeRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CodeServiceImpl implements CodeService {

    private CodeRepository codeRepository;
    private DistanceService distanceCalculator;

    public CodeServiceImpl(CodeRepository codeRepository, DistanceService distanceCalculator) {
        this.codeRepository = codeRepository;
        this.distanceCalculator = distanceCalculator;
    }

    @Override
    public List<Code> getCodes(Pageable paging) {

        List<Code> codes = new ArrayList<>();

        codeRepository.findAll(paging).forEach(codes::add);
        return codes;
    }

    @Override
    public Code getCodeById(Long id) {
        return codeRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementException("NO CODE PRESENT WITH ID = " + id));
    }

    @Override
    public Code getCodeByCode(String code) {
        return codeRepository.findByCode(code)
                .orElseThrow(()->new NoSuchElementException("NO CODE PRESENT WITH CODE = " + code));
    }

    @Override
    public Code updateCode(Code code) {
        Code codeDB = getCodeByCode(code.getCode());
        codeDB.setLatitude(code.getLatitude());
        codeDB.setLongitude(code.getLongitude());
        return codeRepository.save(codeDB);
    }
}
