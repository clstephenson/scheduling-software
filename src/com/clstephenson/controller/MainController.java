package com.clstephenson.controller;

import com.clstephenson.*;
import com.clstephenson.Dialog;
import com.clstephenson.dataaccess.AppointmentRepository;
import com.clstephenson.datamodels.Appointment;
import com.clstephenson.datamodels.Customer;
import com.clstephenson.datamodels.User;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

public class MainController {

    @FXML
    private MenuItem menuItemNewAppointment;
    @FXML
    private MenuItem menuItemNewCustomer;
    @FXML
    private MenuItem menuItemLogout;
    @FXML
    private MenuItem menuItemExit;
    @FXML
    private MenuItem menuNumApptTypesByMonth;
    @FXML
    private MenuItem menuUserSchedule;
    @FXML
    private RadioMenuItem menuItemViewMonth;
    @FXML
    private RadioMenuItem menuItemViewWeek;
    @FXML
    private RadioMenuItem menuItemViewAll;
    @FXML
    private ToggleGroup viewToggleGroup;
    @FXML
    private DatePicker dateInput;
    @FXML
    private TextField startInput;
    @FXML
    private TextField endInput;
    @FXML
    private ComboBox<Customer> customerInput;
    @FXML
    private ChoiceBox<AppointmentLocation> locationInput;
    @FXML
    private ChoiceBox<AppointmentType> typeInput;
    @FXML
    private TextArea descriptionInput;
    @FXML
    private TextField urlInput;
    @FXML
    private TableView<Appointment> appointmentTable;
    @FXML
    private Label statusLabel;
    @FXML
    private Button revertButton;
    @FXML
    private Button saveButton;
    @FXML
    private Button newAppointmentButton;
    @FXML
    private Button deleteAppointmentButton;
    @FXML
    private Button newCustomerButton;
    @FXML
    private Button editCustomerButton;
    @FXML
    private Label changeStatusLabel;
    @FXML
    private Label dateTimeLabel;
    @FXML
    private Label viewLabel;
    @FXML
    private ProgressIndicator progressIndicator;

    private TableColumn<Appointment, Customer> customerColumn;
    private TableColumn<Appointment, AppointmentType> typeColumn;
    private TableColumn<Appointment, String> descriptionColumn;
    private TableColumn<Appointment, AppointmentLocation> locationColumn;
    private TableColumn<Appointment, String> urlColumn;
    private TableColumn<Appointment, LocalDateTime> dateColumn;
    private TableColumn<Appointment, LocalDateTime> startColumn;
    private TableColumn<Appointment, LocalDateTime> endColumn;

    private Customers customers;
    private boolean isNewAppointment = false;
    private boolean isDirtyAppointmentDetails = false;
    private boolean isNewSelection = false;
    private boolean isCustomerChanged = false;

    /**
     * public method is exposed to allow the CustomerController to specify whether the customer data
     * has been saved/changed.
     *
     * @param isCustomerChanged
     */
    public void setIsCustomerChanged(boolean isCustomerChanged) {
        this.isCustomerChanged = isCustomerChanged;
    }

    @FXML
    private void initialize() throws SQLException {
        customers = new Customers();
        setUpAppointmentsTableView();
        initializeDetailsFields();
        setUpEventHandlers();
        requestUserLogin();
    }

    private void setUpAppointmentsTableView() {
        initializeTableColumns();
        setupTableCellDataBindings();
        formatDateCell(dateColumn);
        formatTimeCell(startColumn);
        formatTimeCell(endColumn);
    }

    private void initializeTableColumns() {
        customerColumn = new TableColumn<>("Customer");
        typeColumn = new TableColumn<>("Type");
        descriptionColumn = new TableColumn<>("Description");
        locationColumn = new TableColumn<>("Location");
        urlColumn = new TableColumn<>("URL");
        dateColumn = new TableColumn<>("Date");
        startColumn = new TableColumn<>("Start");
        endColumn = new TableColumn<>("End");
        appointmentTable.getColumns().addAll(dateColumn, startColumn, endColumn, customerColumn, typeColumn,
                descriptionColumn, locationColumn, /*consultantColumn,*/ urlColumn);
    }

    private void setupTableCellDataBindings() {
        customerColumn.setCellValueFactory(cellData -> cellData.getValue().customerProperty());
        typeColumn.setCellValueFactory(cellData -> cellData.getValue().appointmentTypeProperty());
        descriptionColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
        locationColumn.setCellValueFactory(cellData -> cellData.getValue().appointmentLocationProperty());
        urlColumn.setCellValueFactory(cellData -> cellData.getValue().urlProperty());
        startColumn.setCellValueFactory(cellData -> cellData.getValue().startProperty());
        endColumn.setCellValueFactory(cellData -> cellData.getValue().endProperty());
        dateColumn.setCellValueFactory(cellData -> cellData.getValue().startProperty());
    }

    private void setUpEventHandlers() {
        menuItemExit.setOnAction(event -> FXHelper.exitApplication());
        menuItemLogout.setOnAction(event -> handleLogout());
        menuItemViewMonth.selectedProperty().addListener(observable -> setAppointmentView(menuItemViewMonth.getId()));
        menuItemViewWeek.selectedProperty().addListener(observable -> setAppointmentView(menuItemViewWeek.getId()));
        menuItemViewAll.selectedProperty().addListener(observable -> setAppointmentView(menuItemViewAll.getId()));
        appointmentTable.getSelectionModel().selectedItemProperty().addListener(
                ((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        isNewSelection = true; //keeps listeners from firing when details form populates on new selection
                        populateDetailsForm(newValue);
                        isNewAppointment = false;
                        isNewSelection = false;
                    }
                })
        );
        revertButton.setOnAction(event -> populateDetailsForm(getSelectedAppointment()));
        saveButton.setOnAction(event -> handleSaveAppointment());
        deleteAppointmentButton.setOnAction(event -> deleteAppointment());
        menuNumApptTypesByMonth.setOnAction(event -> ReportNumApptTypesByMonth.showReport());
        menuUserSchedule.setOnAction(event -> new ReportUserSchedule(LoginSessionHelper.getCurrentUser()));
        menuItemNewAppointment.setOnAction(event -> createNewAppointment());
        newAppointmentButton.setOnAction(event -> createNewAppointment());
        menuItemNewCustomer.setOnAction(event -> requestCustomerDetails(true));
        newCustomerButton.setOnAction(event -> requestCustomerDetails(true));
        editCustomerButton.setOnAction(event -> requestCustomerDetails(false));
        addDetailsFormListeners();
    }

    private void handleSaveAppointment() {
        if (validateAppointmentFields() && saveAppointment()) {
            reloadAppointmentAndCustomerData();
        }
    }

    private void createNewAppointment() {
        initializeDetailsFields();
        setIsAppointmentChanged(true);
        isNewAppointment = true;
    }

    private void deleteAppointment() {
        Dialog dialog = new Dialog(Alert.AlertType.CONFIRMATION);
        dialog.setTitle("Delete Confirmation");
        dialog.setHeaderText("ARE YOU SURE?");
        dialog.setMessage("This will delete the currently selected appointment.  " +
                "Are you sure you want to do this?");
        Optional<ButtonType> optResult = dialog.showDialog(true);
        if (optResult.get() == ButtonType.OK) {
            if (getSelectedAppointment().remove()) {
                reloadAppointmentAndCustomerData();
            } else {
                //todo customer could not be deleted
            }
        }
    }

    private boolean saveAppointment() {
        boolean isSuccessful = false;
        Appointment appointment;
        if (isNewAppointment) {
            appointment = new Appointment();
            appointment.setConsultant(LoginSessionHelper.getUsername());
        } else {
            appointment = getSelectedAppointment();
        }
        appointment.setCustomer(customerInput.getValue());
        appointment.setDescription(descriptionInput.getText());
        appointment.setUrl(urlInput.getText());
        appointment.setAppointmentType(typeInput.getValue());
        appointment.setAppointmentLocation(locationInput.getValue());
        appointment.setStart(LocalDateTime.of(dateInput.getValue(), LocalTime.parse(startInput.getText(), DateTimeFormatter.ofPattern("HH:mm"))));
        appointment.setEnd(LocalDateTime.of(dateInput.getValue(), LocalTime.parse(endInput.getText(), DateTimeFormatter.ofPattern("HH:mm"))));
        try {
            AppointmentRepository appointmentRepository = new AppointmentRepository();
            if (isNewAppointment) {
                appointment.setId(appointmentRepository.add(appointment, LoginSessionHelper.getSession()));
                if (appointment.getId() > 0) isSuccessful = true;
                isNewAppointment = false;
            } else {
                if (appointmentRepository.update(appointment, LoginSessionHelper.getSession())) isSuccessful = true;
                setIsAppointmentChanged(false);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // todo do something with this exception
        }
        return isSuccessful;
    }

    private boolean validateAppointmentFields() {
        boolean requiredFieldsFilled = Validation.validateNotEmptyOrNull(descriptionInput.getText(), descriptionInput)
                & Validation.validateNotEmptyOrNull(urlInput.getText(), urlInput)
                & Validation.validateNotEmptyOrNull(startInput.getText(), startInput)
                & Validation.validateNotEmptyOrNull(endInput.getText(), endInput);

        boolean dataFormatsOk = true; /*Validation.validateAlphaNumericString(nameInput.getText(), nameInput)
                & Validation.validateAddress(address1Input.getText(), address1Input)
                & Validation.validateAddress(address2Input.getText(), address2Input)
                & Validation.validateAlphaNumericString(cityInput.getText(), cityInput)
                & Validation.validateZipCode(postalCodeInput.getText(), postalCodeInput)
                & Validation.validateAlphaNumericString(countryInput.getText(), countryInput)
                & Validation.validatePhoneNumber(phoneInput.getText(), phoneInput);*/

        if (!requiredFieldsFilled) {
            Dialog.showValidationError(null);
        }
        return requiredFieldsFilled && dataFormatsOk;
    }

    private void addDetailsFormListeners() {
        customers.customersProperty().addListener((observable, oldValue, newValue) -> customerInput.setItems(newValue)); //todo check if this works
        customerInput.valueProperty().addListener(observable -> setIsAppointmentChanged(true));
        descriptionInput.textProperty().addListener(observable -> setIsAppointmentChanged(true));
        descriptionInput.textProperty().addListener((observable, oldValue, newValue) -> Validation.validateNotEmptyOrNull(newValue, descriptionInput));
        typeInput.valueProperty().addListener(observable -> setIsAppointmentChanged(true));
        locationInput.valueProperty().addListener(observable -> setIsAppointmentChanged(true));
        urlInput.textProperty().addListener(observable -> setIsAppointmentChanged(true));
        urlInput.textProperty().addListener((observable, oldValue, newValue) -> Validation.validateNotEmptyOrNull(newValue, urlInput));
        dateInput.valueProperty().addListener(observable -> setIsAppointmentChanged(true));
        startInput.textProperty().addListener(observable -> setIsAppointmentChanged(true));
        startInput.textProperty().addListener((observable, oldValue, newValue) -> Validation.validateNotEmptyOrNull(newValue, startInput));
        endInput.textProperty().addListener(observable -> setIsAppointmentChanged(true));
        endInput.textProperty().addListener((observable, oldValue, newValue) -> Validation.validateNotEmptyOrNull(newValue, endInput));
//        endInput.setTextFormatter(new TextFormatter<>(change -> {
//            if(change.getControlText()change.getCaretPosition() == 2) {
//                change.setText(change.getText() + ":");
//                change.setCaretPosition(3);
//            }
//            return change;
//        }));
    }

    private void setIsAppointmentChanged(boolean isChanged) {
        if (!isChanged) {
            // no changes to save
            isDirtyAppointmentDetails = false;
            showNeedsSavingMessage(false);
            revertButton.setDisable(true);
            saveButton.setDisable(true);
        } else if (!isNewSelection && isChanged && !isDirtyAppointmentDetails) {
            // new changes need saving
            isDirtyAppointmentDetails = true;
            showNeedsSavingMessage(true);
            revertButton.setDisable(false);
            saveButton.setDisable(false);
        }
    }

    private void showNeedsSavingMessage(boolean show) {
        if (show) {
            FadeTransition ft = new FadeTransition(Duration.millis(500), changeStatusLabel);
            ft.setFromValue(0.0);
            ft.setToValue(1.0);
            ft.play();
        } else {
            changeStatusLabel.setOpacity(0.0);
        }
    }

    private void initializeDetailsFields() {
        reloadCustomersList();
        locationInput.setItems(FXCollections.observableArrayList(AppointmentLocation.values()));
        typeInput.setItems(FXCollections.observableArrayList(AppointmentType.values()));
        customerInput.getSelectionModel().selectFirst();
        descriptionInput.clear();
        urlInput.clear();
        typeInput.getSelectionModel().selectFirst();
        locationInput.getSelectionModel().selectFirst();
        dateInput.setValue(LocalDate.now());
        startInput.clear();
        endInput.clear();
        setIsAppointmentChanged(false);
    }

    private void reloadCustomersList() {
        try {
            customerInput.setItems(customers.getCustomers().sorted((c1, c2) -> c1.getName().compareToIgnoreCase(c2.getName())));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void populateDetailsForm(Appointment appt) {
        customerInput.setValue(appt.getCustomer());
        descriptionInput.setText(appt.getDescription());
        urlInput.setText(appt.getUrl());
        typeInput.setValue(appt.getAppointmentType());
        locationInput.setValue(appt.getAppointmentLocation());
        dateInput.setValue(appt.getStart().toLocalDate());
        startInput.setText(appt.getStart().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm")));
        endInput.setText(appt.getEnd().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm")));
        setIsAppointmentChanged(false);
    }

    private void requestUserLogin() {
        FXHelper.showUserLogin();
        updateStatusLabel(LoginSessionHelper.getUsername());
        enableLogoutMenuItem();
        showUserAppointmentsDialog();  // shows appointment for the current user starting soon (i.e. within 15 minutes)
        setAppointmentView("menuItemViewAll");
    }

    private void requestCustomerDetails(boolean isNewCustomer) {
        if (isNewCustomer) {
            FXHelper.showCustomerDetails(this, null);
        } else {
            Customer customer = customerInput.getValue(); // getSelectedAppointment().getCustomer();
            FXHelper.showCustomerDetails(this, customer);
        }
        if (isCustomerChanged) {
            reloadAppointmentAndCustomerData();
            isCustomerChanged = false;
        }
    }

    private Appointment getSelectedAppointment() {
        return appointmentTable.getSelectionModel().getSelectedItem();
    }

    private void updateStatusLabel(String username) {
        if (username.length() > 0) {
            statusLabel.setText(String.format("Logged in as:  %s", username));
        } else {
            statusLabel.setText("Not logged in");
        }
    }

    private void setAppointmentView(String selectedView) { //View view) {
        if (selectedView.equals("menuItemViewMonth")) {
            viewLabel.setText("This Month's Appointments");
        } else if (selectedView.equals("menuItemViewWeek")) {
            viewLabel.setText("This Weeks's Appointments");
        } else {
            viewLabel.setText("All Appointments");
        }
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                reloadAppointmentAndCustomerData();
            }
        });
    }

    private void reloadAppointmentAndCustomerData() {
        populateAppointments(getAppointmentsByView());
        reloadCustomersList();
        populateDetailsForm(getSelectedAppointment());
    }

    private void populateAppointments(ObservableList<Appointment> appointments) {
        //ObservableList<Appointment> allUserAppointments = LoginSessionHelper.getCurrentUser().getUserAppointments();
        if (appointments.isEmpty()) {
            //todo message about no appointments for user
            System.out.println("appointments is empty...");
        } else {
            appointmentTable.setItems(appointments);
            appointmentTable.getSelectionModel().select(0);
        }
    }

    private ObservableList<Appointment> getAppointmentsByView() {
        User user = LoginSessionHelper.getCurrentUser();
        ObservableList<Appointment> appointments;
        LocalDate now = LocalDate.now();
        if (menuItemViewWeek.isSelected()) {
            appointments = user.getUserAppointments(a ->
                    DateTimeUtil.getWeekOfYear(a.getStart().toLocalDate()) == DateTimeUtil.getWeekOfYear(now));
        } else if (menuItemViewMonth.isSelected()) {
            appointments = user.getUserAppointments(a ->
                    a.getStart().getYear() == now.getYear() && a.getStart().getMonthValue() == now.getMonthValue());
        } else {
            appointments = user.getUserAppointments();
        }
        return appointments;
    }

    private void handleLogout() {
        try {
            LoginSessionHelper.logout();
        } catch (Exception e) {
            e.printStackTrace();
        }
        clearData();
        disableLogoutMenuItem();
        updateStatusLabel("");
        requestUserLogin();
    }

    private void enableLogoutMenuItem() {
        menuItemLogout.setDisable(false);
    }

    private void disableLogoutMenuItem() {
        menuItemLogout.setDisable(true);
    }

    private void showUserAppointmentsDialog() {
        String title = Localization.getString("ui.dialog.upcomingappointments");
        StringBuilder message = new StringBuilder();
        for (Appointment appt :
                LoginSessionHelper.getCurrentUser().getUserFutureAppointments(ChronoUnit.MINUTES, 15)) {
            message.append(appt.toString());
        }
        String header;
        if (message.length() == 0) {
            header = Localization.getString("ui.dialog.upcomingappointmentsnone");
        } else {
            header = Localization.getString("ui.dialog.upcomingappointmentsmessage");
        }
        new Dialog(Alert.AlertType.INFORMATION, title, header, message.toString()).showDialog(true);
    }

    private void clearData() {
        appointmentTable.setItems(FXCollections.observableArrayList());
        initializeDetailsFields();
    }

    private void formatDateCell(TableColumn column) {
        column.setCellFactory(col -> new TableCell<Appointment, LocalDateTime>() {
            @Override
            protected void updateItem(LocalDateTime item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(item.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)));
                }
            }
        });
    }

    private void formatTimeCell(TableColumn column) {
        column.setCellFactory(col -> new TableCell<Appointment, LocalDateTime>() {
            @Override
            protected void updateItem(LocalDateTime item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(item.format(DateTimeFormatter.ofPattern("HH:mm")));
                }
            }
        });
    }

}
