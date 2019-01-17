package com.wvs.quizza.Assembler;

import com.wvs.quizza.dto.Question;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;

/**
 * @author Martin Beyer
 * Erweitert zurückgesendete Ressource um Felder die auf die Tests zeigen
 */


public class QuestionResourceAssembler implements ResourceAssembler<Question, Resource<Question>> {

    @Override
    public Resource<Question> toResource(Question question) {

        //TODO: Verlinkung auf Lehrer erstellten Test
        return new Resource<>(question);
    }
}
