package com.wvs.quizza.controller;

import com.wvs.quizza.assembler.QuestionResourceAssembler;
import com.wvs.quizza.dto.Question;
import com.wvs.quizza.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@RestController
public class QuestionController {

    @Autowired
    private final QuestionRepository repository;
    private final QuestionResourceAssembler assembler;

    public QuestionController(QuestionRepository repository, QuestionResourceAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;

        repository.save(new Question("Frage","antwort","a2","a3","a4"));
        repository.save(new Question("Frage2","antwor2t","a2","a3","a4"));
    }

    @GetMapping("/question")
    public List<Question> getAllQuestion() {
        return repository.findAll();
    }

    @GetMapping("/question/{id}")
    public Question getQuestion(@PathVariable Long id) {
        return repository.getOne(id);
    }

    @GetMapping("/randQuestion")
    public Question getRandQuestion() {
        Long rand = ThreadLocalRandom.current().nextLong(0, repository.count());
        return repository.getOne(rand);
    }

    @PostMapping("/question")
    public Question newQuestion(@RequestBody Question question) {
        return repository.save(question);
    }

    @PutMapping("/question/{id}")
    public Question replaceQuestion(@RequestBody Question newQuestion, @PathVariable Long id) {
        return repository.findById(id).map(question -> {
            question.setrAnswer(newQuestion.getrAnswer());
            question.setwAnswer1(newQuestion.getwAnswer1());
            question.setwAnswer2(newQuestion.getwAnswer2());
            question.setwAnswer3(newQuestion.getwAnswer3());
            question.setQuestion(newQuestion.getQuestion());
            return repository.save(question);
        }).orElseGet(() -> {
            newQuestion.setId(id);
            return repository.save(newQuestion);
        });
    }

    @DeleteMapping("question/{id}")
    public void deleteQuestion(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
