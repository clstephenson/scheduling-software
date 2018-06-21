package com.clstephenson.controller;

import com.clstephenson.*;
import com.clstephenson.Dialog;
import com.clstephenson.datamodels.Appointment;
import com.clstephenson.datamodels.Customer;
import com.clstephenson.datamodels.User;
import com.clstephenson.validation.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

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
    private MenuItem menuExportCustomers;
    @FXML
    private RadioMenuItem menuItemViewMonth;
    @FXML
    private RadioMenuItem menuItemViewWeek;
    @FXML
    private RadioMenuItem menuItemViewAll;
    @FXML
    private ToggleGroup viewToggleGroup;
    @FXML
    private ToggleButton viewUpcomingButton;
    @FXML
    private ToggleButton viewAllButton;
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
    private GridPane detailsFormGridPane;
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
    private AtomicBoolean isNewAppointment = new AtomicBoolean(false);
    private AtomicBoolean isDirtyAppointmentDetails = new AtomicBoolean(false);
    private AtomicBoolean isNewSelection = new AtomicBoolean(false);
    private AtomicBoolean isCustomerChanged = new AtomicBoolean(false);
    private String validationErrorCssClass;
    private final String TIME_DISPLAY_FORMAT = "HHmm";

    /**
     * public method is exposed to allow the CustomerController to specify whether the customer data
     * has been saved/changed.
     *
     * @param isCustomerChanged
     */
    public void setIsCustomerChanged(boolean isCustomerChanged) {
        this.isCustomerChanged.set(isCustomerChanged);
    }

    @FXML
    private void initialize() {
        validationErrorCssClass = AppConfiguration.getConfigurationProperty("form.validation.error.css");
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
        //create the columns
        customerColumn = new TableColumn<>("Customer");
        typeColumn = new TableColumn<>("Type");
        descriptionColumn = new TableColumn<>("Description");
        locationColumn = new TableColumn<>("Location");
        urlColumn = new TableColumn<>("URL");
        dateColumn = new TableColumn<>("Date");
        startColumn = new TableColumn<>("Start");
        endColumn = new TableColumn<>("End");

        //give each column an id (if needed for css styling)
        customerColumn.setId("customer-column");
        typeColumn.setId("type-column");
        descriptionColumn.setId("description-column");
        locationColumn.setId("location-column");
        urlColumn.setId("url-column");
        dateColumn.setId("date-column");
        startColumn.setId("start-column");
        endColumn.setId("end-column");

        appointmentTable.getColumns().addAll(dateColumn, startColumn, endColumn, customerColumn, typeColumn,
                locationColumn, descriptionColumn);
    }

    private void setupTableCellDataBindings() {
        customerColumn.setCellValueFactory(cellData -> cellData.getValue().customerProperty());
        typeColumn.setCellValueFactory(cellData -> cellData.getValue().appointmentTypeProperty());
        descriptionColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
        locationColumn.setCellValueFactory(cellData -> cellData.getValue().appointmentLocationProperty());
        urlColumn.setCellValueFactory(cellData -> cellData.getValue().urlProperty());
        dateColumn.setCellValueFactory(cellData -> cellData.getValue().startProperty());
        startColumn.setCellValueFactory(cellData -> cellData.getValue().startProperty());
        endColumn.setCellValueFactory(cellData -> cellData.getValue().endProperty());
    }

    private void setUpEventHandlers() {
        menuItemExit.setOnAction(event -> FXHelper.exitApplication());
        menuItemLogout.setOnAction(event -> handleLogout());
        menuItemViewMonth.selectedProperty().addListener(observable -> setAppointmentView(menuItemViewMonth.getId()));
        menuItemViewWeek.selectedProperty().addListener(observable -> setAppointmentView(menuItemViewWeek.getId()));
        menuItemViewAll.selectedProperty().addListener(observable -> setAppointmentView(menuItemViewAll.getId()));
        viewUpcomingButton.selectedProperty().addListener(observable -> reloadAppointmentAndCustomerData());
        viewAllButton.selectedProperty().addListener(observable -> reloadAppointmentAndCustomerData());
        appointmentTable.getSelectionModel().selectedItemProperty().addListener(
                ((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        isNewSelection.set(true); //keeps listeners from firing when details form populates on new selection
                        populateDetailsForm(newValue);
                        isNewAppointment.set(false);
                        isNewSelection.set(false);
                    }
                })
        );
        revertButton.setOnAction(event -> populateDetailsForm(getSelectedAppointment()));
        saveButton.setOnAction(event -> handleSaveAppointment());
        deleteAppointmentButton.setOnAction(event -> deleteAppointment());
        menuNumApptTypesByMonth.setOnAction(event -> ReportNumApptTypesByMonth.showReport());
        menuUserSchedule.setOnAction(event -> new ReportUserSchedule(LoginSessionHelper.getCurrentUser()));
        menuExportCustomers.setOnAction(event -> new ReportCustomers());
        menuItemNewAppointment.setOnAction(event -> createNewAppointment());
        newAppointmentButton.setOnAction(event -> createNewAppointment());
        menuItemNewCustomer.setOnAction(event -> requestCustomerDetails(true));
        newCustomerButton.setOnAction(event -> requestCustomerDetails(true));
        editCustomerButton.setOnAction(event -> requestCustomerDetails(false));
        addDetailsFormListeners();
    }

    private void handleSaveAppointment() {
        if (validateAppointmentFields()) {
            saveAppointment();
        }
    }

    private void createNewAppointment() {
        initializeDetailsFields();
        setIsAppointmentChanged(true);
        isNewAppointment.set(true);
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

    private void setIsAppointmentChanged(boolean isChanged) {
        if (!isChanged) {
            // no changes to save
            isDirtyAppointmentDetails.set(false);
            showNeedsSavingMessage(false);
            revertButton.setDisable(true);
            saveButton.setDisable(true);
        } else if (!isNewSelection.get() && isChanged && !isDirtyAppointmentDetails.get()) {
            // new changes need saving
            isDirtyAppointmentDetails.set(true);
            showNeedsSavingMessage(true);
            revertButton.setDisable(false);
            saveButton.setDisable(false);
        }
    }

    private LocalDateTime getLocalDateTimeFromDetails(String formattedTime) {
        return LocalDateTime.of(dateInput.getValue(), LocalTime.parse(formattedTime, DateTimeFormatter.ofPattern(TIME_DISPLAY_FORMAT)));
    }

    private void addDetailsFormListeners() {
        customers.customersProperty().addListener((observable, oldValue, newValue) -> customerInput.setItems(newValue));
        customerInput.valueProperty().addListener(observable -> setIsAppointmentChanged(true));
        descriptionInput.textProperty().addListener(observable -> setIsAppointmentChanged(true));
        typeInput.valueProperty().addListener(observable -> setIsAppointmentChanged(true));
        locationInput.valueProperty().addListener(observable -> setIsAppointmentChanged(true));
        urlInput.textProperty().addListener(observable -> setIsAppointmentChanged(true));
        dateInput.valueProperty().addListener(observable -> setIsAppointmentChanged(true));
        startInput.textProperty().addListener(observable -> setIsAppointmentChanged(true));
        endInput.textProperty().addListener(observable -> setIsAppointmentChanged(true));
    }

    private void showNeedsSavingMessage(boolean show) {
        changeStatusLabel.setVisible(show);
    }

    private void requestCustomerDetails(boolean isNewCustomer) {
        if (isNewCustomer) {
            FXHelper.showCustomerDetails(this, null);
        } else {
            Customer customer = customerInput.getValue();
            FXHelper.showCustomerDetails(this, customer);
        }
        if (isCustomerChanged.get()) {
            reloadAppointmentAndCustomerData();
            isCustomerChanged.set(false);
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
    }

    private void reloadCustomersList() {
        customerInput.setItems(customers.getCustomers());
    }

    private void populateDetailsForm(Appointment appt) {
        if (appt != null) {
            customerInput.setValue(appt.getCustomer());
            descriptionInput.setText(appt.getDescription());
            urlInput.setText(appt.getUrl());
            typeInput.setValue(appt.getAppointmentType());
            locationInput.setValue(appt.getAppointmentLocation());
            dateInput.setValue(appt.getStart().toLocalDate());
            startInput.setText(appt.getStart().toLocalTime().format(DateTimeFormatter.ofPattern(TIME_DISPLAY_FORMAT)));
            endInput.setText(appt.getEnd().toLocalTime().format(DateTimeFormatter.ofPattern(TIME_DISPLAY_FORMAT)));
            clearValidationErrors();
            setIsAppointmentChanged(false);
        }
    }

    private void clearValidationErrors() {
        for (Node hbox : detailsFormGridPane.getChildren()) {
            for (Node control : ((HBox) hbox).getChildren()) {
                if (control.getStyleClass().contains(validationErrorCssClass)) {
                    control.getStyleClass().remove(validationErrorCssClass);
                }
            }
        }
    }

    private void requestUserLogin() {
        FXHelper.showUserLogin();
        updateStatusLabel(LoginSessionHelper.getUsername());
        enableLogoutMenuItem();
        showUserAppointmentsDialog();  // shows appointment for the current user starting soon (i.e. within 15 minutes)
        setAppointmentView("menuItemViewAll");
    }

    private void setAppointmentView(String selectedView) {
        if (selectedView.equals("menuItemViewMonth")) {
            viewLabel.setText("This Month's Appointments");
        } else if (selectedView.equals("menuItemViewWeek")) {
            viewLabel.setText("This Weeks's Appointments");
        } else {
            viewLabel.setText("All Appointments");
        }

        //so the UI/mouse doesn't freeze during the update.
        Platform.runLater(this::reloadAppointmentAndCustomerData);
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

    private void saveAppointment() {
        Appointment appointment;
        if (isNewAppointment.get()) {
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
        appointment.setStart(getLocalDateTimeFromDetails(startInput.getText()));
        appointment.setEnd(getLocalDateTimeFromDetails(endInput.getText()));

        if (appointment.save()) {
            reloadAppointmentAndCustomerData(appointment);
        }
    }

    private void reloadAppointmentAndCustomerData() {
        populateAppointments(getAppointmentsByView());
        reloadCustomersList();
        populateDetailsForm(getSelectedAppointment());
    }

    private void reloadAppointmentAndCustomerData(Appointment appointmentToSelect) {
        populateAppointments(getAppointmentsByView());
        int indexToSelect = appointmentTable.getItems().indexOf(
                appointmentTable.getItems()
                        .stream()
                        .filter(a -> a.getId() == appointmentToSelect.getId())
                        .findFirst().get()
        );
        appointmentTable.getSelectionModel().clearAndSelect(indexToSelect);
        appointmentTable.scrollTo(indexToSelect);
        reloadCustomersList();
        populateDetailsForm(getSelectedAppointment());
    }

    private void populateAppointments(SortedList<Appointment> appointments) {
        if (appointments.isEmpty()) {
            //todo message about no appointments for user
            System.out.println("appointments is empty...");
        } else {
            appointments.comparatorProperty().bind(appointmentTable.comparatorProperty());
            appointmentTable.setItems(appointments);
            appointmentTable.getSelectionModel().select(0);
        }
    }

    /**
     * @return Sorted list of appointments filtered according to the selected view options.
     */
    private SortedList<Appointment> getAppointmentsByView() {
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

        if (viewUpcomingButton.isSelected()) {
            return new SortedList<Appointment>(appointments.filtered(a -> a.getEnd().isAfter(LocalDateTime.now())));
        } else {
            return new SortedList<>(appointments);
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

        List<Appointment> list =
                LoginSessionHelper.getCurrentUser().getUserFutureAppointments(ChronoUnit.MINUTES, 15);

        for (Appointment appt : list) {
            String msg = String.format(
                    "You have a %s with %s today from %s to %s.",
                    appt.getAppointmentType().toString(),
                    appt.getCustomer().getName(),
                    appt.getStart().format(DateTimeFormatter.ofPattern("h:mm a")),
                    appt.getEnd().format(DateTimeFormatter.ofPattern("h:mm a")));
            message.append(msg);
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

    private boolean validateAppointmentFields() {
        Validator v = new Validator();
        v.getValidations().add(new TimeValidation(startInput, "Start Time", validationErrorCssClass));
        v.getValidations().add(new TimeValidation(endInput, "End Time", validationErrorCssClass));
        v.getValidations().add(new StartTimeBeforeEndTimeValidation(startInput, endInput, validationErrorCssClass));
        v.getValidations().add(new TextLengthValidation(urlInput, "URL", validationErrorCssClass, 0, 255));
        v.getValidations().add(new UrlNotRequiredValidation(urlInput, "URL", validationErrorCssClass));
        v.getValidations().add(new TextLengthValidation(descriptionInput, "Description", validationErrorCssClass, 1, 500));

        boolean isValid = false;
        if (v.validateAll().isPresent() && (v.getMessage().length() > 0)) {
            Dialog.showValidationError(v.getMessage());
        } else {
            //valid so far... now check if appt times follow business rules
            isValid = validateAppointmentBusinessRules();
        }
        return isValid;
    }

    private boolean validateAppointmentBusinessRules() {
        boolean isValid = false;
        LocalDateTime start = getLocalDateTimeFromDetails(startInput.getText());
        LocalDateTime end = getLocalDateTimeFromDetails(endInput.getText());
        User currentUser = LoginSessionHelper.getCurrentUser();
        try {
            int id = isNewAppointment.get() ? 0 : getSelectedAppointment().getId();
            if (ScheduleValidator.isAppointmentNotOverlapping(currentUser, start, end, id)) {
                if (ScheduleValidator.isAppointmentWithinBusinessHours(start, end, locationInput.getValue())) {
                    isValid = true;
                }
            }
        } catch (AppointmentOutsideBusinessHoursException e) {
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("Your appointment from %s to %s falls outside the standard business hours.",
                    DateTimeUtil.getPrettyTime(e.getAppointmentStartTime()),
                    DateTimeUtil.getPrettyTime(e.getAppointmentEndTime())
            ));
            sb.append(System.lineSeparator());
            sb.append(System.lineSeparator());
            sb.append(String.format("Business hours are from %s to %s, seven days per week.",
                    DateTimeUtil.getPrettyTime(e.getBusinessHoursStart()),
                    DateTimeUtil.getPrettyTime(e.getBusinessHoursEnd())));
            Dialog.showValidationError(sb.toString());
        } catch (OverlappingAppointmentException e) {
            Appointment a = e.getOverlappedAppointment();
            StringBuilder sb = new StringBuilder();
            sb.append("The appointment overlaps with another scheduled appointment:");
            sb.append(System.lineSeparator());
            sb.append(System.lineSeparator());
            sb.append(String.format("An appointment with %s is scheduled from %s to %s",
                    a.getCustomer().getName(),
                    DateTimeUtil.getPrettyTime(a.getStart().toLocalTime()),
                    DateTimeUtil.getPrettyTime(a.getEnd().toLocalTime())
            ));
            Dialog.showValidationError(sb.toString());
        }
        return isValid;
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
                    setText(item.format(DateTimeFormatter.ofPattern("h:mm a")));
                }
            }
        });
    }

}
