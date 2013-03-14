package com.ace.salesail.domain;

import javax.persistence.Entity;

@Entity
public class Category extends Persistent {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override public String toString() {
        return "Name: "+name+"\ndateCreated:"+dateCreated+"\ndateUpdated:"+dateUpdated+"\n\n";
    }
}