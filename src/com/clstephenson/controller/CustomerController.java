package com.clstephenson.controller;

import com.clstephenson.*;
import com.clstephenson.Dialog;
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
        nameInput.textProperty().addListener((observable, oldValue, newValue) -> Validation.validateNotEmptyOrNull(newValue, nameInput));
        address1Input.textProperty().addListener((observable, oldValue, newValue) -> Validation.validateNotEmptyOrNull(newValue, address1Input));
        address2Input.textProperty().addListener((observable, oldValue, newValue) -> Validation.validateNotEmptyOrNull(newValue, address2Input));
        cityInput.textProperty().addListener((observable, oldValue, newValue) -> Validation.validateNotEmptyOrNull(newValue, cityInput));
        postalCodeInput.textProperty().addListener((observable, oldValue, newValue) -> Validation.validateNotEmptyOrNull(newValue, postalCodeInput));
        countryInput.textProperty().addListener((observable, oldValue, newValue) -> Validation.validateNotEmptyOrNull(newValue, countryInput));
        phoneInput.textProperty().addListener((observable, oldValue, newValue) -> Validation.validateNotEmptyOrNull(newValue, phoneInput));
        cancelButton.setOnAction(event -> close());
        saveButton.setOnAction(event -> saveCustomer());
    }

    private void close() {
        FXHelper.getStageFromNode(cancelButton).close();
    }

    private void saveCustomer() {

        if(validateCustomer() && customer.save()) {
            mainController.setIsCustomerChanged(true);
            close();
        }
    }



    private boolean validateCustomer() {
        boolean requiredFieldsFilled = Validation.validateNotEmptyOrNull(nameInput.getText(), nameInput)
                & Validation.validateNotEmptyOrNull(address1Input.getText(), address1Input)
                & Validation.validateNotEmptyOrNull(address2Input.getText(), address2Input)
                & Validation.validateNotEmptyOrNull(cityInput.getText(), cityInput)
                & Validation.validateNotEmptyOrNull(postalCodeInput.getText(), postalCodeInput)
                & Validation.validateNotEmptyOrNull(countryInput.getText(), countryInput)
                & Validation.validateNotEmptyOrNull(phoneInput.getText(), phoneInput);

        boolean dataFormatsOk = true; /*Validation.validateAlphaNumericString(nameInput.getText(), nameInput)
                & Validation.validateAddress(address1Input.getText(), address1Input)
                & Validation.validateAddress(address2Input.getText(), address2Input)
                & Validation.validateAlphaNumericString(cityInput.getText(), cityInput)
                & Validation.validateZipCode(postalCodeInput.getText(), postalCodeInput)
                & Validation.validateAlphaNumericString(countryInput.getText(), countryInput)
                & Validation.validatePhoneNumber(phoneInput.getText(), phoneInput);*/

        if(!requiredFieldsFilled) {
            String title = "Data Validation Error";
            String header = "Please fill in required fields.";
            String message = "All fields are required.";
            Dialog dialog = new Dialog(Alert.AlertType.ERROR, title, header, message);
            dialog.showDialog(true);
        }
        return requiredFieldsFilled && dataFormatsOk;
    }
}
