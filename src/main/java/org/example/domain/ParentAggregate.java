package org.example.domain;

import com.avaje.ebean.annotation.Sql;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@Sql
public class ParentAggregate {

    @OneToOne
    public Parent parent;

}
