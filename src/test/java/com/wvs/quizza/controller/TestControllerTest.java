package com.wvs.quizza.controller;

import com.wvs.quizza.dto.Question;
import com.wvs.quizza.repository.TestRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

/**
 * @author Martin Beyer
 * Unit Test zu TestControllerklasse
 */
@RunWith(SpringJUnit4ClassRunner.class)
class TestControllerTest {

    @InjectMocks
    TestController underTest;

    @Mock
    QuestionController questionController;

    @Mock
    TestRepository repository;

    @Before
    private void setup() {
        Mockito.when(questionController.getQuestion(22L))
                .thenReturn(new Question(22L, "eine Frage im Test", "foo", "bar", "foobar", "barfoo"));
        Mockito.when(repository.getOne(11L))
                .thenReturn(new com.wvs.quizza.dto.Test(11L, Arrays.asList(22L, 33L, 44L), "Test zum Testen"));
        Mockito.when(repository.findAll()).thenReturn(createTestData());
    }

    private List<com.wvs.quizza.dto.Test> createTestData() {
        return Arrays.asList(new com.wvs.quizza.dto.Test(11L, Arrays.asList(11L, 22L), "ein Test"), new com.wvs.quizza.dto.Test(22L, Arrays.asList(22L, 33L), "test2"));
    }

    @Test
    void getTest() {
        //Arrange
        //Act
        com.wvs.quizza.dto.Test back = underTest.getTest(22L);
        //Assert
        Assert.assertNotNull(back);
    }


    @Test
    void getAllTests() {
    }

    @Test
    void getAllQuestionsFromTest() {
    }

    @Test
    void replaceTest() {
    }

    @Test
    void createTest() {
    }

    @Test
    void deleteTest() {
    }

}