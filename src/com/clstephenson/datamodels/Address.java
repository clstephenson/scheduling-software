package com.clstephenson.datamodels;

import javafx.beans.property.*;

public class Address {
    private IntegerProperty id = new SimpleIntegerProperty(this, "id");
    private StringProperty addressLine1 = new SimpleStringProperty(this, "addressLine1");
    private StringProperty addressLine2 = new SimpleStringProperty(this, "addressLine2");
    private ObjectProperty<City> city = new SimpleObjectProperty<>(this, "city");
    private StringProperty zipCode = new SimpleStringProperty(this, "zipCode");
    private StringProperty phoneNumber = new SimpleStringProperty(this, "phoneNumber");

    public Address() {
        this.city.set(new City());
    }

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

    public IntegerProperty idProperty() {
        return id;
    }

    public StringProperty addressLine1Property() {
        return addressLine1;
    }

    public StringProperty addressLine2Property() {
        return addressLine2;
    }

    public ObjectProperty<City> cityProperty() {
        return city;
    }

    public StringProperty zipCodeProperty() {
        return zipCode;
    }

    public StringProperty phoneNumberProperty() {
        return phoneNumber;
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