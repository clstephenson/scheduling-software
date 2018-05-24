package com.clstephenson.controller;

import com.clstephenson.*;
import com.clstephenson.Dialog;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

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
    private MenuItem menuItemCurrentMonth;

    @FXML
    private MenuItem menuItemCurrentWeek;

    @FXML
    private TableView<Appointment> monthTableView;

    @FXML
    private Label statusLabel;

    private TableColumn<Appointment, Customer> customerColumn;
    private TableColumn<Appointment, AppointmentType> typeColumn;
    private TableColumn<Appointment, String> descriptionColumn;
    private TableColumn<Appointment, AppointmentLocation> locationColumn;
    //private TableColumn<Appointment, String> consultantColumn;
    private TableColumn<Appointment, String> urlColumn;
    private TableColumn<Appointment, LocalDateTime> dateColumn;
    private TableColumn<Appointment, LocalDateTime> startColumn;
    private TableColumn<Appointment, LocalDateTime> endColumn;

    @FXML
    private void initialize() throws SQLException {
        setUpAppointmentsTableView();
        setUpEventHandlers();
        requestUserLogin();
//        try {
//            Main.testSchedulingNewAppointment();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
        showUserAppointmentsDialog();  // shows appointment for the current user starting soon (i.e. within 15 minutes)
        populateAppointments();
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
        monthTableView.getColumns().addAll(dateColumn, startColumn, endColumn, customerColumn, typeColumn, descriptionColumn,
                locationColumn, /*consultantColumn,*/ urlColumn);
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
                    setText(item.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM)));
                }
            }
        });
    }

    private void setUpEventHandlers() {
        menuItemExit.setOnAction(event -> FXHelper.exitApplication());
        menuItemLogout.setOnAction(event -> {throw new NotImplementedException();});
    }

    private void populateAppointments() throws SQLException {
        ObservableList<Appointment> allUserAppointments = LoginSessionHelper.getCurrentUser().getUserAppointments();
        if(allUserAppointments.isEmpty()) {
            //todo message about no appointments for user
            System.out.println("allUserAppointments is empty...");
        } else {
            monthTableView.setItems(allUserAppointments);
        }
    }

    private void requestUserLogin() {
        Parent root = getLoginParent();
        Stage loginStage = getLoginStage(root);
        while(Main.session == null || !Main.session.isLoggedIn()) {
            loginStage.showAndWait();
        }
        updateStatusLabel(LoginSessionHelper.getUsername());
    }

    private void updateStatusLabel(String username) {
        if(username.length() > 0) {
            statusLabel.setText(String.format("Logged in as:  %s", username));
        } else {
            statusLabel.setText("Not logged in");
        }
    }

    private Parent getLoginParent() {
        Parent root;
        try {
            String fxmlPath = AppConfiguration.getConfigurationProperty("fxml.path") + "Login.fxml";
            FXMLLoader loader = new FXMLLoader(Paths.get(fxmlPath).toUri().toURL());
            loader.setResources(Localization.getResourceBundle());
            root = loader.load();
        } catch (Exception e) {
            throw new RuntimeException("Could not load Login.fxml", e);
            //todo fix this exception
        }
        return root;
    }

    private Stage getLoginStage(Parent root) {
        Scene scene = new Scene(root, 300, 300);
        Stage loginStage = new Stage();
        loginStage.setTitle(Localization.getString("ui.application.title"));
        loginStage.setResizable(false);
        loginStage.setScene(scene);
        loginStage.setOnCloseRequest(event -> FXHelper.exitApplication());
        return loginStage;
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
}
