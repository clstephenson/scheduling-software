package com.clstephenson.datamodels;

import com.clstephenson.DataRepositoryException;
import com.clstephenson.Dialog;
import com.clstephenson.LoginSessionHelper;
import com.clstephenson.dataaccess.CityRepository;
import javafx.beans.property.*;

public class City {
    private IntegerProperty id = new SimpleIntegerProperty(this, "id");
    private StringProperty name = new SimpleStringProperty(this, "name");
    private ObjectProperty<Country> country = new SimpleObjectProperty<>(this, "country");

    public static City getCityById(int id) {
        City city = new City();
        try {
            city = new CityRepository().findById(id);
        } catch (DataRepositoryException e) {
            Dialog.showDBError(e.getMessage());
        } finally {
            return city;
        }
    }
    
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

    public boolean save() {
        boolean result = false;
        int resultId = 0;
        try {
            CityRepository repository = new CityRepository();
            if (this.id.get() > 0) {
                result = repository.update(this, LoginSessionHelper.getSession());
            } else {
                resultId = repository.add(this, LoginSessionHelper.getSession());
                if (resultId > 0) this.id.set(resultId);
            }
        } catch (DataRepositoryException e) {
            Dialog.showDBError(e.getMessage());
        }
        return result || resultId > 0;
    }

    public boolean remove() {
        boolean result = false;
        if (this.id.get() > 0) {
            try {
                CityRepository repository = new CityRepository();
                if (repository.remove(this)) {
                    this.id.set(0);
                    result = true;
                }
            } catch (DataRepositoryException e) {
                Dialog.showDBError(e.getMessage());
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

        City city = (City) o;

        return id.equals(city.id);
    }

    @Override
    public String toString() {
        return String.format("[%d, %s, %s]", this.id.get(), this.name.get(), this.country.get());
    }
}
