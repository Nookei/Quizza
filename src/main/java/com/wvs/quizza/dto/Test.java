package com.wvs.quizza.dto;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

/**
 * @author Martin Beyer
 * DTO welches den Namen eines Tests und Referenzen auf alle zugeordneten Fragen speichert
 */
@Entity()
@Table(name = "tbl_Test")
public class Test {
    @Id
    private Long id;
    @ElementCollection
    private List<Long> fragen;
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
}
