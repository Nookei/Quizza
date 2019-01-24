package com.wvs.quizza.controller;

import com.wvs.quizza.assembler.QuestionResourceAssembler;
import com.wvs.quizza.dto.Question;
import com.wvs.quizza.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@RestController
public class QuestionController {

    @Autowired
    private final QuestionRepository repository;
    private final QuestionResourceAssembler assembler;

    private Long rand;

    public QuestionController(QuestionRepository repository, QuestionResourceAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;

        repository.save(new Question(2L, Arrays.asList(1L, 2L), "foo2Frage", "answer", "falsch", "falscher", "am falschesten"));
        repository.save(new Question(3L, Arrays.asList(2L), "fooFrage", "answer", "falsch", "falscher", "am falschesten"));
        repository.save(new Question(4L, Arrays.asList(1L), "foo3Frage", "answer", "falsch", "falscher", "am falschesten"));
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
        rand = ThreadLocalRandom.current().nextLong(0, repository.count());
        return repository.getOne(rand);
    }

    @GetMapping("/list/{testid}/question")
    public Question getRandQuestionFromTest(@PathVariable Long testId) {
        Question q;  // TODO: Klären dass es keinen Test ohne Fragen geben darf
        do {
            q = this.getRandQuestion();
        } while (q.isInTest(testId));
        return q;
    }

    @GetMapping("/list/{testid}")
    public List<Question> getAllQuestionsFromList(@PathVariable Long testId) {
        List<Question> back = Collections.emptyList();
        for (Question q : repository.findAll()) {
            if (q.isInTest(testId))
                back.add(q);
        }
        return back;
    }

    @PostMapping("/question")
    public Question newQuestion(@RequestBody Question question) {
        return repository.save(question);
    }

    @PutMapping("/question/{id}")
    public Question replaceQuestion(@RequestBody Question newQuestion, @PathVariable Long id) {
        return repository.findById(id).map(question -> {
            question.setId(newQuestion.getId());
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

    @GetMapping("/question/{id}/removeFromTest/{testId}")
    public void removeQuestionFromTest(@PathVariable Long id, @PathVariable Long testId) {
        repository.getOne(id).removeFromTest(testId);
    }

    @GetMapping("/question/{id}/addToTest/{testId}")
    public void addQuestionToTest(@PathVariable Long id, @PathVariable Long testId) {
        repository.getOne(id).addToTest(testId);
    }

    @DeleteMapping("question/{id}")
    public void deleteQuestion(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
