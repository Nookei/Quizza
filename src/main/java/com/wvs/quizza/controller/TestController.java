package com.wvs.quizza.controller;

import com.wvs.quizza.dto.Question;
import com.wvs.quizza.dto.Test;
import com.wvs.quizza.repository.QuestionRepository;
import com.wvs.quizza.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@RestController
public class TestController {

    @Autowired
    private
    TestRepository repo;

    @Autowired
    private
    QuestionRepository questionRepository;

    public TestController(TestRepository repo, QuestionRepository questionRepository) {
        this.repo = repo;
        this.questionRepository = questionRepository;
    }

    @GetMapping("/test/{testId}")
    public Test getTest(@PathVariable Long testId) {
        return repo.getOne(testId);
    }

    @GetMapping("/test/{testId}/randQuestion")
    public Test getRandQuestionFromTest(@PathVariable Long testId) {
        return repo.getOne(ThreadLocalRandom.current().nextLong(0, repo.count()));
    }

    @GetMapping("/test/{testId}/question") // oder nur Referenzen zurückgeben?
    public List<Question> getAllQuestionsFromTest(@PathVariable Long testId) {
        List<Question> back = Collections.emptyList();
        Test t = repo.getOne(testId);

        for (Long id : t.getFragen()) {
            back.add(questionRepository.getOne(id));
        }
        return back;
    }

    @PutMapping("/test/{testId}")
    public Test replaceTest(@RequestBody Test newTest, @PathVariable Long testId) {
        return repo.findById(testId).map(test -> {
            test.setId(newTest.getId());
            test.setFragen(newTest.getFragen());
            test.setName(newTest.getName());

            return repo.save(test);
        }).orElseGet(() -> {
            newTest.setId(testId);

            return repo.save(newTest);
        });
    }
}
