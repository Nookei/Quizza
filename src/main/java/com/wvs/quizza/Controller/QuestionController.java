package com.wvs.quizza.Controller;

import com.wvs.quizza.Assembler.QuestionResourceAssembler;
import com.wvs.quizza.Repository.QuestionRepository;
import com.wvs.quizza.dto.Question;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@RestController
public class QuestionController {

    private final QuestionRepository repository;
    private final QuestionResourceAssembler assembler;

    private Long rand;

    public QuestionController(QuestionRepository repository, QuestionResourceAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/question")
    public List<Question> getAllQuestion() {
        return repository.findAll();
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
            question.setAnswers(newQuestion.getAnswers());
            question.setQuestion(newQuestion.getQuestion());
            return repository.save(question);
        }).orElseGet(() -> {
            newQuestion.setId(id);
            return repository.save(newQuestion);
        });
    }


    @DeleteMapping("question/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
