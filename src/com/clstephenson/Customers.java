package com.clstephenson;

import com.clstephenson.dataaccess.CustomerRepository;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class Customers {

    private SimpleListProperty<Customer> customers;

    public Customers() throws SQLException {
        customers = new SimpleListProperty<>(FXCollections.observableArrayList());
        updateCustomersFromRepository();
    }

    private void updateCustomersFromRepository() throws SQLException {
        setCustomers(FXCollections.observableArrayList(new CustomerRepository().findAll()));
    }

    public ObservableList<Customer> getCustomers() throws SQLException {
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
