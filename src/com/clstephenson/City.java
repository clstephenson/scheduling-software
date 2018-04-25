package com.clstephenson;

public class City {
    private int id;
    private String name;
    private Country country;

    public City() {}

    public City(String name, Country country) {
        this.name = name;
        this.country = country;
    }

    public City(int id, String name, Country country) {
        this.id = id;
        this.name = name;
        this.country = country;
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

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String toString() {
        return String.format("[%d, %s, %s]", this.id, this.name, this.country);
    }
}
