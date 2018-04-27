package com.clstephenson;

public class Customer {
    private int id;
    private String name;
    private Address address;
    private boolean isActive;

    public Customer() {}

    public Customer(String name, Address address, boolean isActive) {
        this.name = name;
        this.address = address;
        this.isActive = isActive;
    }

    public Customer(int id, String name, Address address, boolean isActive) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.isActive = isActive;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean hasId() {
        return this.id > 0;
    }

    public String toString() {
        return String.format("[%d, %s, %s]", this.id, this.name, this.address);
    }
}
