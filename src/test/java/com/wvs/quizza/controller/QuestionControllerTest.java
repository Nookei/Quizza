package com.wvs.quizza.controller;

import com.wvs.quizza.assembler.QuestionResourceAssembler;
import com.wvs.quizza.dto.Question;
import com.wvs.quizza.repository.QuestionRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


@RunWith(MockitoJUnitRunner.class)
public class QuestionControllerTest {

    @InjectMocks
    QuestionController underTest;

    @Autowired
    @Mock
    QuestionRepository questionRepository;

    @Autowired
    @Mock
    QuestionResourceAssembler questionResourceAssembler;

    @Before
    public void setUp() {
        Mockito.when(questionRepository.getOne(1L)).thenReturn(new Question(1L, "foo?", "bar", "a", "b", "c"));
        Mockito.when(questionRepository.findAll()).thenReturn(createTestData());
    }

    @Test
    public void getAllQuestion() {
        //arrange
        //act
        List<Question> question = underTest.getAllQuestion();
        //assert
        Assert.assertEquals(question.size(), 3);
        Assert.assertEquals(question.get(1).getQuestion(), "foo?");
    }

    @Test
    public void getQuestion() {
        //arrange
        //act
        Question question = underTest.getQuestion(1L);
        //assert
        Assert.assertNotNull(question);
        Assert.assertEquals(question.getQuestion(), "foo?");
    }

    @Test
    public void getQuestionFromList() {
    }

    @Test
    public void newQuestion() {
        //arrange
        Question dirty = new Question(42L, "foobar?", "foobar", "barfoo", "barfoo", "barfoo");
        //act
        underTest.newQuestion(dirty);
        //assert

        Question back = underTest.getQuestion(42L);

        Assert.assertNotNull(back);
    }

    @Test
    public void replaceQuestion() {
    }

    @Test
    public void deleteQuestion() {
    }

    public List<Question> createTestData() {
        List<Question> back = new ArrayList<>();
        back.add(new Question(1L, "foo?", "bar", "a", "b", "c"));
        back.add(new Question(2L, "foo?", "bar", "a1", "b1", "c1"));
        back.add(new Question(3L, "foo?", "bar", "a2", "b2", "c2"));
        return back;
    }
}