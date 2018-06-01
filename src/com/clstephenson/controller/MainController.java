package com.clstephenson.controller;

import com.clstephenson.*;
import com.clstephenson.Dialog;
import com.clstephenson.dataaccess.AppointmentRepository;
import com.clstephenson.datamodels.Appointment;
import com.clstephenson.datamodels.Customer;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Duration;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class MainController {

    @FXML private MenuItem menuItemNewAppointment;
    @FXML private MenuItem menuItemNewCustomer;
    @FXML private MenuItem menuItemLogout;
    @FXML private MenuItem menuItemExit;
    @FXML private MenuItem menuItemCurrentMonth;
    @FXML private MenuItem menuItemCurrentWeek;
    @FXML private DatePicker dateInput;
    @FXML private TextField startInput;
    @FXML private TextField endInput;
    @FXML private ComboBox<Customer> customerInput;
    @FXML private ChoiceBox<AppointmentLocation> locationInput;
    @FXML private ChoiceBox<AppointmentType> typeInput;
    @FXML private TextArea descriptionInput;
    @FXML private TextField urlInput;
    @FXML private TableView<Appointment> appointmentTable;
    @FXML private Label statusLabel;
    @FXML private Button revertButton;
    @FXML private Button saveButton;
    @FXML private Button newAppointmentButton;
    @FXML private Button newCustomerButton;
    @FXML private Button editCustomerButton;
    @FXML private Label changeStatusLabel;

    private TableColumn<Appointment, Customer> customerColumn;
    private TableColumn<Appointment, AppointmentType> typeColumn;
    private TableColumn<Appointment, String> descriptionColumn;
    private TableColumn<Appointment, AppointmentLocation> locationColumn;
    //private TableColumn<Appointment, String> consultantColumn;
    private TableColumn<Appointment, String> urlColumn;
    private TableColumn<Appointment, LocalDateTime> dateColumn;
    private TableColumn<Appointment, LocalDateTime> startColumn;
    private TableColumn<Appointment, LocalDateTime> endColumn;

    private Customers customers;
    private boolean isDirtyAppointmentDetails = false;
    private boolean isNewSelection = false;
    private boolean isCustomerChanged = false;

    /**
     * public method is exposed to allow the CustomerController to specify whether the customer data
     * has been saved/changed.
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
        //consultantColumn = new TableColumn<>("Consultant");
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
        //consultantColumn.setCellValueFactory(cellData -> cellData.getValue().consultantProperty());
        urlColumn.setCellValueFactory(cellData -> cellData.getValue().urlProperty());
        startColumn.setCellValueFactory(cellData -> cellData.getValue().startProperty());
        endColumn.setCellValueFactory(cellData -> cellData.getValue().endProperty());
        dateColumn.setCellValueFactory(cellData -> cellData.getValue().startProperty());
    }

    private void setUpEventHandlers() {
        menuItemExit.setOnAction(event -> FXHelper.exitApplication());
        menuItemLogout.setOnAction(event -> handleLogout());
        appointmentTable.getSelectionModel().selectedItemProperty().addListener(
                ((observable, oldValue, newValue) -> {
                    if(newValue != null) {
                        isNewSelection = true; //keeps listeners from firing when details form populates on new selection
                        populateDetailsForm(newValue);
                        isNewSelection = false;
                    }
                })
        );
        revertButton.setOnAction(event -> populateDetailsForm(getSelectedAppointment()));
        saveButton.setOnAction(event -> saveAppointment(getSelectedAppointment()));
        newCustomerButton.setOnAction(event -> requestCustomerDetails(true));
        editCustomerButton.setOnAction(event -> requestCustomerDetails(false));
        addDetailsFormListeners();
    }

    private void addDetailsFormListeners() {
        customers.customersProperty().addListener((observable, oldValue, newValue) -> customerInput.setItems(newValue)); //todo check if this works
        customerInput.valueProperty().addListener(observable -> setIsDataChanged(true));
        descriptionInput.textProperty().addListener(observable -> setIsDataChanged(true));
        typeInput.valueProperty().addListener(observable -> setIsDataChanged(true));
        locationInput.valueProperty().addListener(observable -> setIsDataChanged(true));
        urlInput.textProperty().addListener(observable -> setIsDataChanged(true));
        dateInput.valueProperty().addListener(observable -> setIsDataChanged(true));
        startInput.textProperty().addListener(observable -> setIsDataChanged(true));
        endInput.textProperty().addListener(observable -> setIsDataChanged(true));
    }

    private void setIsDataChanged(boolean isChanged) {
        if(!isChanged) {
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

    private void saveAppointment(Appointment selectedAppointment) {
        selectedAppointment.setCustomer(customerInput.getValue());
        selectedAppointment.setDescription(descriptionInput.getText());
        selectedAppointment.setUrl(urlInput.getText());
        selectedAppointment.setAppointmentType(typeInput.getValue());
        selectedAppointment.setAppointmentLocation(locationInput.getValue());
        selectedAppointment.setStart(LocalDateTime.of(dateInput.getValue(), LocalTime.parse(startInput.getText(), DateTimeFormatter.ofPattern("HH:mm"))));
        selectedAppointment.setEnd(LocalDateTime.of(dateInput.getValue(), LocalTime.parse(endInput.getText(), DateTimeFormatter.ofPattern("HH:mm"))));
        try {
            AppointmentRepository appointmentRepository = new AppointmentRepository();
            if(appointmentRepository.update(selectedAppointment, LoginSessionHelper.getSession())) {
                setIsDataChanged(false);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // todo do something with this exception
        }
    }

    private void initializeDetailsFields() {
        reloadCustomersList();
        locationInput.setItems(FXCollections.observableArrayList(AppointmentLocation.values()));
        typeInput.setItems(FXCollections.observableArrayList(AppointmentType.values()));

        setIsDataChanged(false);
    }

    private void reloadCustomersList() {
        try {
            customerInput.setItems(customers.getCustomers());
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
        setIsDataChanged(false);
    }

    private void requestUserLogin() {
        FXHelper.showUserLogin();
        updateStatusLabel(LoginSessionHelper.getUsername());
        enableLogoutMenuItem();
        showUserAppointmentsDialog();  // shows appointment for the current user starting soon (i.e. within 15 minutes)
        populateAppointments();
    }

    private void requestCustomerDetails(boolean isNewCustomer) {
        if(isNewCustomer) {
            FXHelper.showCustomerDetails(this, null);
        } else {
            Customer customer = getSelectedAppointment().getCustomer();
            FXHelper.showCustomerDetails(this, customer);
        }
        if(isCustomerChanged) {
            populateAppointments();
            reloadCustomersList();
            populateDetailsForm(getSelectedAppointment());
            isCustomerChanged = false;
        }
    }

    private Appointment getSelectedAppointment() {
        return appointmentTable.getSelectionModel().getSelectedItem();
    }

    private void updateStatusLabel(String username) {
        if(username.length() > 0) {
            statusLabel.setText(String.format("Logged in as:  %s", username));
        } else {
            statusLabel.setText("Not logged in");
        }
    }

    private void populateAppointments() {
        ObservableList<Appointment> allUserAppointments = LoginSessionHelper.getCurrentUser().getUserAppointments();
        if(allUserAppointments.isEmpty()) {
            //todo message about no appointments for user
            System.out.println("allUserAppointments is empty...");
        } else {
            appointmentTable.setItems(allUserAppointments);
            appointmentTable.getSelectionModel().select(0);
        }
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
        for(Appointment appt : LoginSessionHelper.getCurrentUser().getAppointmentsNextFifteenMinutes()) {
            message.append(appt.toString());
        }
        String header;
        if(message.length() == 0) {
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
                if(empty) {
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
                if(empty) {
                    setText(null);
                } else {
                    setText(item.format(DateTimeFormatter.ofPattern("HH:mm")));
                }
            }
        });
    }

}
