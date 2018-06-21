package com.clstephenson.controller;

import com.clstephenson.AppConfiguration;
import com.clstephenson.Customers;
import com.clstephenson.Dialog;
import com.clstephenson.FXHelper;
import com.clstephenson.datamodels.Customer;
import com.clstephenson.validation.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Optional;

public class CustomerController {

    @FXML private Button saveButton;
    @FXML private Button deleteButton;
    @FXML private Button cancelButton;
    @FXML private TextField nameInput;
    @FXML private TextField address1Input;
    @FXML private TextField address2Input;
    @FXML private TextField cityInput;
    @FXML private TextField postalCodeInput;
    @FXML private TextField countryInput;
    @FXML private TextField phoneInput;
    @FXML private CheckBox activeInput;
    @FXML
    private ComboBox<Customer> customerComboBox;
    @FXML
    private Label customerLabel;
    @FXML
    private Label headerLabel;

    private Customer initialCustomer;
    private Customer customer;
    private static MainController mainController;
    private String validationErrorCssClass;

    public static void injectMainController(MainController controller) {
        mainController = controller;
    }

    @FXML
    private void initialize() {
        if (initialCustomer == null) {
            deleteButton.setVisible(false);
            showCustomerList(false);
        }
        customer = new Customer();
        validationErrorCssClass = AppConfiguration.getConfigurationProperty("form.validation.error.css");
        setUpEventHandlers();
    }

    private void showCustomerList(boolean showList) {
        customerComboBox.setVisible(showList);
        customerLabel.setVisible(showList);
        if (showList) {
            headerLabel.setText("Edit Customers");
        } else {
            headerLabel.setText("Add New Customer");
        }
    }

    public void initData(Customer customer) {
        this.initialCustomer = customer;
        deleteButton.setVisible(true);
        showCustomerList(true);
        ObservableList<Customer> customers = FXCollections.observableArrayList(new Customers().getCustomers());
        customerComboBox.setItems(customers);
        customerComboBox.getSelectionModel().selectedItemProperty().addListener(observable -> setFieldBindings());
        customerComboBox.getSelectionModel().select(initialCustomer);
    }

    private void setFieldBindings() {
        nameInput.textProperty().bindBidirectional(getSelectedCustomer().nameProperty());
        address1Input.textProperty().bindBidirectional(getSelectedCustomer().getAddress().addressLine1Property());
        address2Input.textProperty().bindBidirectional(getSelectedCustomer().getAddress().addressLine2Property());
        cityInput.textProperty().bindBidirectional(getSelectedCustomer().getAddress().getCity().nameProperty());
        postalCodeInput.textProperty().bindBidirectional(getSelectedCustomer().getAddress().zipCodeProperty());
        countryInput.textProperty().bindBidirectional(getSelectedCustomer().getAddress().getCity().getCountry().nameProperty());
        phoneInput.textProperty().bindBidirectional(getSelectedCustomer().getAddress().phoneNumberProperty());
        activeInput.selectedProperty().bindBidirectional(getSelectedCustomer().isActiveProperty());
    }

    private void setUpEventHandlers() {
        cancelButton.setOnAction(event -> close());
        deleteButton.setOnAction(event -> deleteCustomer());
        saveButton.setOnAction(event -> saveCustomer());
    }

    private void close() {
        FXHelper.getStageFromNode(cancelButton).close();
    }

    private Customer getSelectedCustomer() {
        return customerComboBox.getSelectionModel().getSelectedItem();
    }

    private void deleteCustomer() {
        Dialog dialog = new Dialog(Alert.AlertType.CONFIRMATION);
        dialog.setHeaderText("ARE YOU SURE?");
        dialog.setMessage("Deleting this customer will also delete all appointments associated with the customer.  " +
                "Are you sure you want to do this?");
        Optional<ButtonType> optResult = dialog.showDialog(true);
        if(optResult.get() == ButtonType.OK) {
            if (Customer.getCustomerById(getSelectedCustomer().getId()).remove()) {
                mainController.setIsCustomerChanged(true);
                close();
            } else {
                new Dialog(Alert.AlertType.ERROR, "We've encountered a problem...",
                        "The customer was unable to be deleted.").showDialog(true);
            }
        }
    }

    private boolean validateCustomer() {
        Validator v = new Validator();
        v.getValidations().add(new ZipCodeValidation(postalCodeInput, "Zip Code", validationErrorCssClass));
        v.getValidations().add(new PhoneNumberValidation(phoneInput, "Phone Number", validationErrorCssClass));
        v.getValidations().add(new AlphaNumericValidation(nameInput, "Name", validationErrorCssClass));
        v.getValidations().add(new TextLengthValidation(nameInput, "Name", validationErrorCssClass, 1, 45));
        v.getValidations().add(new TextLengthValidation(address1Input, "Address Line 1", validationErrorCssClass, 1, 50));
        v.getValidations().add(new StreetAddressValidation(address1Input, "Address Line 1", validationErrorCssClass));
        v.getValidations().add(new TextLengthValidation(address2Input, "Address Line 2", validationErrorCssClass, 0, 50));
        v.getValidations().add(new StreetAddressNotRequiredValidation(address2Input, "Address Line 2", validationErrorCssClass));
        v.getValidations().add(new TextLengthValidation(cityInput, "City", validationErrorCssClass, 1, 50));
        v.getValidations().add(new AlphaNumericValidation(cityInput, "City", validationErrorCssClass));
        v.getValidations().add(new TextLengthValidation(countryInput, "Country", validationErrorCssClass, 1, 50));
        v.getValidations().add(new AlphaNumericValidation(countryInput, "Country", validationErrorCssClass));

        if (v.validateAll().isPresent() && (v.getMessage().length() > 0)) {
            Dialog.showValidationError(v.getMessage());
            return false;
        }
        return true;
    }

    private void saveCustomer() {
        if (customerComboBox.isVisible()) {
            customer.setId(getSelectedCustomer().getId());
        }
        customer.setName(nameInput.getText());
        customer.getAddress().setAddressLine1(address1Input.getText());
        customer.getAddress().setAddressLine2(address2Input.getText());
        customer.getAddress().getCity().setName(cityInput.getText());
        customer.getAddress().setZipCode(postalCodeInput.getText());
        customer.getAddress().getCity().getCountry().setName(countryInput.getText());
        customer.getAddress().setPhoneNumber(phoneInput.getText());
        customer.setActive(activeInput.isSelected());

        if (validateCustomer() && customer.save()) {
            mainController.setIsCustomerChanged(true);
            close();
        }
    }
}
