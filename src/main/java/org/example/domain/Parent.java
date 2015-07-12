package org.example.domain;

import com.avaje.ebean.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type")
public abstract class Parent {

    @Id
    private Long id;

    private Integer number;

    @ManyToMany(cascade = CascadeType.PERSIST)
    public List<Data> data = new ArrayList<>();

    //public static final Finder<Long,Parent> FIND = new Finder<>(Long.class,Parent.class);

    protected Parent(Integer number) {
        this.number = number;
    }

    public abstract String getName();

    public Long getId() {
        return id;
    }

    public Integer getNumber() {
        return number;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> datas) {
        this.data = datas;
    }

}
