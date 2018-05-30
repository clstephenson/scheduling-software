package com.clstephenson.controller;

import com.clstephenson.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.security.AccessControlException;

public class CustomerController {

    @FXML private Button saveButton;
    @FXML private Button cancelButton;
    @FXML private TextField nameInput;
    @FXML private TextField address1Input;
    @FXML private TextField address2Input;
    @FXML private TextField cityInput;
    @FXML private TextField postalCodeInput;
    @FXML private TextField countryInput;
    @FXML private TextField phoneInput;
    @FXML private CheckBox activeInput;

    private Customer customer;

    @FXML
    private void initialize() {
        this.customer = new Customer();
        setUpEventHandlers();
    }

    public void initData(Customer customer) {
        this.customer = customer;
        nameInput.setText(customer.getName());
        address1Input.setText(customer.getAddress().getAddressLine1());
        address2Input.setText(customer.getAddress().getAddressLine2());
        cityInput.setText(customer.getAddress().getCity().getName());
        postalCodeInput.setText(customer.getAddress().getZipCode());
        countryInput.setText(customer.getAddress().getCity().getCountry().getName());
        phoneInput.setText(customer.getAddress().getPhoneNumber());
        activeInput.setSelected(customer.isActive());
    }

    private void setUpEventHandlers() {
        cancelButton.setOnAction(event -> FXHelper.getStageFromNode(cancelButton).close());
        saveButton.setOnAction(event -> saveCustomer());
    }

    private void saveCustomer() {

    }




}
