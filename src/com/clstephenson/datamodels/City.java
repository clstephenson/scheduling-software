package com.clstephenson.datamodels;

import javafx.beans.property.*;

public class City {
    private IntegerProperty id = new SimpleIntegerProperty(this, "id");
    private StringProperty name = new SimpleStringProperty(this, "name");
    private ObjectProperty<Country> country = new SimpleObjectProperty<>(this, "country");

    public City() {
        this.name.set("");
        this.country.set(new Country());
    }

    public City(String name, Country country) {
        this.name.set(name);
        this.country.set(country);
    }

    public City(int id, String name, Country country) {
        this.id.set(id);
        this.name.set(name);
        this.country.set(country);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public ObjectProperty<Country> countryProperty() {
        return country;
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

    public Country getCountry() {
        return country.get();
    }

    public void setCountry(Country country) {
        this.country.set(country);
    }

    public boolean hasId() {
        return this.id.get() > 0;
    }

    public String toString() {
        return String.format("[%d, %s, %s]", this.id.get(), this.name.get(), this.country.get());
    }
}
