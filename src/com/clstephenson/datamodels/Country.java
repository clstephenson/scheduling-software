package com.clstephenson.datamodels;

import com.clstephenson.LoginSessionHelper;
import com.clstephenson.dataaccess.CountryRepository;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Country {
    private IntegerProperty id = new SimpleIntegerProperty(this, "id");
    private StringProperty name = new SimpleStringProperty(this, "name");

    public static Country getCountryById(int id) {
        return new CountryRepository().findById(id);
    }

    public Country() {
        this.name.set("");
    }

    public Country(String name) {
        this.name.set(name);
    }

    public Country(int id, String name) {
        this.id.set(id);
        this.name.set(name);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public StringProperty nameProperty() {
        return name;
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

    public boolean save() {
        boolean result = false;
        int resultId = 0;
        CountryRepository repository = new CountryRepository();
        if (this.id.get() > 0) {
            result = repository.update(this, LoginSessionHelper.getSession());
        } else {
            resultId = repository.add(this, LoginSessionHelper.getSession());
            if (resultId > 0) this.id.set(resultId);
        }
        return result || resultId > 0;
    }

    public boolean remove() {
        boolean result = false;
        if (this.id.get() > 0) {
            CountryRepository repository = new CountryRepository();
            if (repository.remove(this)) {
                this.id.set(0);
                result = true;
            }
        }
        return result;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Country country = (Country) o;

        return id.equals(country.id);
    }

    @Override
    public String toString() {
        return String.format("[%d, %s]", this.id.get(), this.name.get());
    }
}
