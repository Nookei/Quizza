package com.wvs.quizza.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

/**
 * @author Martin Beyer
 * DTO welches Fragentext und die Antwortmöglichkeiten speichert
 */

@Entity()
@Table(name = "tbl_Question")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String question;
    private String rAnswer;
    private String wAnswer1;
    private String wAnswer2;
    private String wAnswer3;

    public Question() {
    }

    public Question(Long id, String question, String rAnswer, String wAnswer1, String wAnswer2, String wAnswer3) {
        this.id = id;
        this.question = question;
        this.rAnswer = rAnswer;
        this.wAnswer1 = wAnswer1;
        this.wAnswer2 = wAnswer2;
        this.wAnswer3 = wAnswer3;
    }

    public Question(String question, String rAnswer, String wAnswer1, String wAnswer2, String wAnswer3) {
        this.question = question;
        this.rAnswer = rAnswer;
        this.wAnswer1 = wAnswer1;
        this.wAnswer2 = wAnswer2;
        this.wAnswer3 = wAnswer3;
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

    public String getrAnswer() {
        return rAnswer;
    }

    public void setrAnswer(String answer) {
        this.rAnswer = answer;
    }

    public String getwAnswer1() {
        return wAnswer1;
    }

    public void setwAnswer1(String wAnswer1) {
        this.wAnswer1 = wAnswer1;
    }

    public String getwAnswer2() {
        return wAnswer2;
    }

    public void setwAnswer2(String wAnswer2) {
        this.wAnswer2 = wAnswer2;
    }

    public String getwAnswer3() {
        return wAnswer3;
    }

    public void setwAnswer3(String wAnswer3) {
        this.wAnswer3 = wAnswer3;
    }

}
