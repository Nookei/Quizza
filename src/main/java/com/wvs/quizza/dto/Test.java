package com.wvs.quizza.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

/**
 * @author Martin Beyer
 * DTO welches den Namen eines Tests und Referenzen auf alle zugeordneten Fragen speichert
 */
@Entity()
@Table(name = "tbl_Test")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ElementCollection
    private List<Long> fragen;
    private int anzFragen;
    private String name;

    public Test() {
    }

    public Test(Long id, List<Long> fragen, String name) {
        this.id = id;
        this.fragen = fragen;
        this.name = name;
    }

    public void removeFromTest(Long id) {
        fragen.remove(id);
    }

    public void addToTest(Long id) {
        if (!fragen.contains(id)) {
            fragen.add(id);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Long> getFragen() {
        return fragen;
    }

    public void setFragen(List<Long> fragen) {
        this.fragen = fragen;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAnzFragen() {
        return fragen.size();
    }

    public void setAnzFragen(int anzFragen) {
        this.anzFragen = anzFragen;
    }
}
