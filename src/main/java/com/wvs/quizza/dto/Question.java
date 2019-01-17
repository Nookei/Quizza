package com.wvs.quizza.dto;

import java.util.Arrays;
import java.util.List;

/**
 * @author Martin Beyer
 * DTO welches Fragentext und die Antwortmöglichkeiten speichert
 */

public class Question {
    private Long id;
    private String question;
    private List<String> answers;

    public Question(Long id, String question, String rAnswer, String wAnswer1, String wAnswer2, String wAnswer3) {
        this.id = id;
        this.question = question;
        this.answers = Arrays.asList(rAnswer, wAnswer1, wAnswer2, wAnswer3); // Richtige Antwort wird immer an 1. Stelle des Arrays gespeichert
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getRightAnswer() {
        return answers.get(0);
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public List<String> getWrongAnswers() {
        return answers.subList(1, 3);
    }
}
