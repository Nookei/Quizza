package com.wvs.quizza.repository;

import com.wvs.quizza.dto.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test, Long> {
}
