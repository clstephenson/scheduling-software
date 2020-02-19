package com.clstephenson.dataaccess;

import com.clstephenson.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportData {

    private Connection dbConnection;

    public ReportData() throws DataRepositoryException {
        try {
            dbConnection = DBManager.getConnection();
        } catch (SQLException e) {
            String message = Localization.getString("error.db.connection");
            throw new DataRepositoryException(e, message);
        }
    }

    public ObservableList<NumApptType> getNumApptTypesByMonth(int year, LoginSession session) throws DataRepositoryException {
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
            String message = "Unable to get number of appointment types by month data.";
            throw new DataRepositoryException(e, message);
        }
    }
}
