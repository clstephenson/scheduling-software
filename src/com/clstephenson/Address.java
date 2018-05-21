package com.clstephenson;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class Address {
    private SimpleIntegerProperty id = new SimpleIntegerProperty(this, "id");
    private SimpleStringProperty addressLine1 = new SimpleStringProperty(this, "addressLine1");
    private SimpleStringProperty addressLine2 = new SimpleStringProperty(this, "addressLine2");
    private SimpleObjectProperty<City> city = new SimpleObjectProperty<>(this, "city");
    private SimpleStringProperty zipCode = new SimpleStringProperty(this, "zipCode");
    private SimpleStringProperty phoneNumber = new SimpleStringProperty(this, "phoneNumber");

    public Address() {}

    public Address(String addressLine1, String addressLine2, City city, String zipCode, String phoneNumber) {

        this.addressLine1.set(addressLine1);
        this.addressLine2.set(addressLine2);
        this.city.set(city);
        this.zipCode.set(zipCode);
        this.phoneNumber.set(phoneNumber);
    }

    public Address(int id, String addressLine1, String addressLine2, City city, String zipCode, String phoneNumber) {

        this.id.set(id);
        this.addressLine1.set(addressLine1);
        this.addressLine2.set(addressLine2);
        this.city.set(city);
        this.zipCode.set(zipCode);
        this.phoneNumber.set(phoneNumber);
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getAddressLine1() {
        return addressLine1.get();
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1.set(addressLine1);
    }

    public String getAddressLine2() {
        return addressLine2.get();
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2.set(addressLine2);
    }

    public City getCity() {
        return city.get();
    }

    public void setCity(City city) {
        this.city.set(city);
    }

    public String getZipCode() {
        return zipCode.get();
    }

    public void setZipCode(String zipCode) {
        this.zipCode.set(zipCode);
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    }

    public boolean hasId() {
        return this.id.get() > 0;
    }

    public String toString() {
        return String.format("[%d, %s, %s, %s, %s, %s]", this.id.get(), this.addressLine1.get(), this.addressLine2.get(), this.city.get(), this.zipCode.get(), this.phoneNumber.get());
    }
}
