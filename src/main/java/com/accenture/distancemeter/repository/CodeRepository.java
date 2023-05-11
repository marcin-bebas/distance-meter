package com.accenture.distancemeter.repository;

import com.accenture.distancemeter.bean.Code;
import org.springframework.data.repository.CrudRepository;

public interface CodeRepository extends CrudRepository<Code, Long> {
    Code findByCode(String code1);
}
