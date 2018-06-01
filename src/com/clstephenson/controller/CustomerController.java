package com.clstephenson.controller;

import com.clstephenson.*;
import com.clstephenson.datamodels.Customer;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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
    private static MainController mainController;

    public static void injectMainController(MainController controller) {
        mainController = controller;
    }

    @FXML
    private void initialize() {
        if(customer == null) {
            customer = new Customer();
        }
        setFieldBindings();
        setUpEventHandlers();
    }

    public void initData(Customer customer) {
        this.customer = customer;
        setFieldBindings();
    }

    private void setFieldBindings() {
        nameInput.textProperty().bindBidirectional(customer.nameProperty());
        address1Input.textProperty().bindBidirectional(customer.getAddress().addressLine1Property());
        address2Input.textProperty().bindBidirectional(customer.getAddress().addressLine2Property());
        cityInput.textProperty().bindBidirectional(customer.getAddress().getCity().nameProperty());
        postalCodeInput.textProperty().bindBidirectional(customer.getAddress().zipCodeProperty());
        countryInput.textProperty().bindBidirectional(customer.getAddress().getCity().getCountry().nameProperty());
        phoneInput.textProperty().bindBidirectional(customer.getAddress().phoneNumberProperty());
        activeInput.selectedProperty().bindBidirectional(customer.isActiveProperty());
    }

    private void setUpEventHandlers() {
        cancelButton.setOnAction(event -> close());
        saveButton.setOnAction(event -> saveCustomer());
    }

    private void close() {
        FXHelper.getStageFromNode(cancelButton).close();
    }

    private void saveCustomer() {

        if(customer.save()) {
            mainController.setIsCustomerChanged(true);
            close();
        }
    }
}
