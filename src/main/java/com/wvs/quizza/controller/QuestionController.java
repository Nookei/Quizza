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

    private Long rand;

    public QuestionController(QuestionRepository repository, QuestionResourceAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/question")
    public List<Question> getAllQuestion() {
        repository.save(new Question(2L, "foo2Frage", "answer", "falsch", "falscher", "am falschesten"));

        repository.save(new Question(3L, "fooFrage", "answer", "falsch", "falscher", "am falschesten"));
        return repository.findAll();
    }

    @GetMapping("/question/{id}")
    public Question getQuestion(@PathVariable Long id) {
        return repository.getOne(id);
    }

    @GetMapping("/randQuestion")
    public Question getRandQuestion() {
        rand = ThreadLocalRandom.current().nextLong(0, 2); // Todo bound auf entries db setzen
        return repository.getOne(rand);
    }

    @GetMapping("/list/{listid}/question/{id}") // TODO: implement
    public Question getQuestionFromList(@PathVariable Long listid, @PathVariable Long id) {
        return repository.getOne(id);
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


    @DeleteMapping("question/{id}")
    public void deleteQuestion(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
