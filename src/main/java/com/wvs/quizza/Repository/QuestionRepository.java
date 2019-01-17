package com.wvs.quizza.Repository;

import com.wvs.quizza.dto.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}