package com.accenture.distancemeter.repository;

import com.accenture.distancemeter.bean.Code;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CodeRepository extends JpaRepository<Code, Long> {
    Optional<Code> findByCode(String code1);
}
