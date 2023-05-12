package com.accenture.distancemeter.repository;

import com.accenture.distancemeter.bean.Code;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CodeRepository extends CrudRepository<Code, Long> {
    Optional<Code> findByCode(String code1);
}
