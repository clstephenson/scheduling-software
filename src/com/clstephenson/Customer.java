package com.clstephenson;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class Customer {
    private SimpleIntegerProperty id = new SimpleIntegerProperty(this, "id");
    private SimpleStringProperty name = new SimpleStringProperty(this, "name");
    private SimpleObjectProperty<Address> address = new SimpleObjectProperty<>(this, "address");
    private SimpleBooleanProperty isActive = new SimpleBooleanProperty(this, "isActive");

    public Customer() {}

    public Customer(String name, Address address, boolean isActive) {
        this.name.set(name);
        this.address.set(address);
        this.isActive.set(isActive);
    }

    public Customer(int id, String name, Address address, boolean isActive) {
        this.id.set(id);
        this.name.set(name);
        this.address.set(address);
        this.isActive.set(isActive);
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

    public Address getAddress() {
        return address.get();
    }

    public void setAddress(Address address) {
        this.address.set(address);
    }

    public boolean isActive() {
        return isActive.get();
    }

    public void setActive(boolean active) {
        isActive.set(active);
    }

    public boolean hasId() {
        return this.id.get() > 0;
    }

    public String toString() {
        return getName();
    }
}
