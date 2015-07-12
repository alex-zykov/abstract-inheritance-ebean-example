package org.example.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Data {

    @Id
    private Long id;

    private Integer number;

    @ManyToMany(mappedBy = "data")
    public List<Parent> parents = new ArrayList<>();

    public Data(int number) {
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public Integer getNumber() {
        return number;
    }

}
