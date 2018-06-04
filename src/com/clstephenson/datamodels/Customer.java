package com.clstephenson.datamodels;

import com.clstephenson.LoginSessionHelper;
import com.clstephenson.dataaccess.CustomerRepository;
import javafx.beans.property.*;

import java.sql.SQLException;

public class Customer {
    private IntegerProperty id = new SimpleIntegerProperty(this, "id");
    private StringProperty name = new SimpleStringProperty(this, "name");
    private ObjectProperty<Address> address = new SimpleObjectProperty<>(this, "address");
    private BooleanProperty isActive = new SimpleBooleanProperty(this, "isActive");

    public Customer() {
        this.address.set(new Address());
    }

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

    public IntegerProperty idProperty() {
        return id;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public ObjectProperty<Address> addressProperty() {
        return address;
    }

    public BooleanProperty isActiveProperty() {
        return isActive;
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

    public boolean save() {
        boolean result = false;
        int resultId = 0;
        try {
            CustomerRepository repository = new CustomerRepository();
            if(this.id.get() > 0) {
                result = repository.update(this, LoginSessionHelper.getSession());
            } else {
                resultId = repository.add(this, LoginSessionHelper.getSession());
                if(resultId > 0) this.id.set(resultId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            //todo do something with exception
        }
        return result || resultId > 0;
    }

    public boolean remove() {
        boolean result = false;
        if(this.id.get() > 0) {
            try {
                CustomerRepository repository = new CustomerRepository();
                if(repository.remove(this)) {
                    this.id.set(0);
                    result = true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                //todo do something with exception
            }
        }
        return result;
    }
}
