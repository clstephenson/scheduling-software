package com.clstephenson.dataaccess;

import com.clstephenson.AppointmentType;
import com.clstephenson.Localization;
import com.clstephenson.LoginSession;
import com.clstephenson.NumApptType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportData {

    private Connection dbConnection;

    public ReportData() throws SQLException {
        dbConnection = DBManager.getConnection();
    }

    public ObservableList<NumApptType> getNumApptTypesByMonth(int year, LoginSession session) {
        try (CallableStatement statement = dbConnection.prepareCall("{CALL reportNumApptTypesByMonth(?, ?)}")) {
            statement.setString(1, session.getLoggedInUser().getUserName());
            statement.setInt(2, year);
            ResultSet rs = statement.executeQuery();
            ObservableList<NumApptType> list = FXCollections.observableArrayList();
            while (rs.next()) {
                int month = rs.getInt(1);
                AppointmentType type = AppointmentType.valueOf(rs.getString(2));
                int numAppts = rs.getInt(3);
                list.add(new NumApptType(month, type, numAppts));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
            //todo handle exception
            //String message = Localization.getString("error.db.removingcustomer") + " = " + id;
            //throw new SQLException(message, e);
        }
    }
}
