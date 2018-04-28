package com.clstephenson;

public class User {
    private String userName;
    private int id;
    private String password;
    private boolean isActive;

    public User() {}

    public User(String userName, String password, boolean isActive) {
        this.userName = userName;
        this.password = password;
        this.isActive = isActive;
    }

    public User(int id, String userName, String password, boolean isActive) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.isActive = isActive;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        return this.userName;
    }
}