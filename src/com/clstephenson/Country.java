package com.clstephenson;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Country {
    private SimpleIntegerProperty id = new SimpleIntegerProperty(this, "id");
    private SimpleStringProperty name = new SimpleStringProperty(this, "name");

    public Country() {}

    public Country(String name) {
        this.name.set(name);
    }

    public Country(int id, String name) {
        this.id.set(id);
        this.name.set(name);
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public boolean hasId() {
        return this.id.get() > 0;
    }

    public String toString() {
        return String.format("[%d, %s]", this.id.get(), this.name.get());
    }
}
