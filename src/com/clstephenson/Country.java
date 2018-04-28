package com.clstephenson;

public class Country {
    private int id;
    private String name;

    public Country() {}

    public Country(String name) {
        this.name = name;
    }

    public Country(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean hasId() {
        return this.id > 0;
    }

    public String toString() {
        return String.format("[%d, %s]", this.id, this.name);
    }
}