package org.example.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("A")
public class ChildA extends Parent {

    public String getName() {
        return "A Name";
    }

    public ChildA(Integer number) {
        super(number);
    }

}
