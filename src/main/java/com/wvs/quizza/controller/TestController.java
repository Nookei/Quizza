package com.wvs.quizza.controller;

import com.wvs.quizza.dto.Question;
import com.wvs.quizza.dto.Test;
import com.wvs.quizza.repository.QuestionRepository;
import com.wvs.quizza.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@RestController
public class TestController {

    @Autowired
    private TestRepository repo;

    @Autowired
    private QuestionRepository questionRepository;

    public TestController(TestRepository repo, QuestionRepository questionRepository) {
        this.repo = repo;
        this.questionRepository = questionRepository;
    }

    @GetMapping("/test/{testId}")
    public Test getTest(@PathVariable Long testId) {
        return repo.getOne(testId);
    }

    @GetMapping("/test/{testId}/randQuestion")
    public Question getRandQuestionFromTest(@PathVariable Long testId) {
        List<Long> allQuestions = repo.getOne(testId).getFragen();

        return questionRepository.getOne(allQuestions.get(ThreadLocalRandom.current().nextInt(0, allQuestions.size())));
    }

    @GetMapping("/test")
    public List<Test> getAllTests() {
        return repo.findAll();
    }

    @GetMapping("/test/{testId}/question")
    public List<Question> getAllQuestionsFromTest(@PathVariable Long testId) {
        List<Question> back = new ArrayList<>();
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

    @PostMapping("/test")
    public Test createTest(@RequestBody Test test) {
        return repo.save(test);
    }

    @DeleteMapping("/test/{testId}")
    public void deleteTest(@PathVariable Long testId) {
        repo.deleteById(testId);
    }

}
