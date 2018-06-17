package com.clstephenson;

import com.clstephenson.dataaccess.CustomerRepository;
import com.clstephenson.datamodels.Customer;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class Customers {

    private SimpleListProperty<Customer> customers;

    public Customers() {
        customers = new SimpleListProperty<>(FXCollections.observableArrayList());
        updateCustomersFromRepository();
    }

    private void updateCustomersFromRepository() {
        setCustomers(FXCollections.observableArrayList(new CustomerRepository().findAll()));
    }

    public ObservableList<Customer> getCustomers() {
        updateCustomersFromRepository();
        return customers.get();
    }

    public SimpleListProperty<Customer> customersProperty() {
        return customers;
    }

    private void setCustomers(ObservableList<Customer> customers) {
        this.customers.set(customers);
    }
}
