package com.clstephenson.dataaccess;

import com.clstephenson.*;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentRepository implements Repository<Appointment> {

    private Connection dbConnection;

    public AppointmentRepository() throws SQLException {
        dbConnection = new DBManager().getConnection();
    }

    @Override
    public int add(Appointment appointment, LoginSession session) throws SQLException {
        CustomerRepository customerRepository = new CustomerRepository();
        Customer customer = customerRepository.findSingle(cust -> cust.getId() == appointment.getCustomer().getId());
        int customerId;
        if(customer == null) {
            customerId = customerRepository.add(appointment.getCustomer(), session);
            appointment.getCustomer().setId(customerId); //add the newly generated ID to the appointment's customer
        } else {
            customerId = customer.getId();
        }
        if(customerId != 0) {
            String currentUserName = session.getLoggedInUser().getUserName();
            String sql = "INSERT INTO appointment (customerId, title, description, location, contact, " +
                    "url, start, end, createDate, createdBy, lastUpdateBy) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try(PreparedStatement statement = dbConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                statement.setInt(1, customerId);
                statement.setString(2, appointment.getAppointmentType().name());
                statement.setString(3, appointment.getDescription());
                statement.setString(4, appointment.getAppointmentLocation().name());
                statement.setString(5, appointment.getConsultant());
                statement.setString(6, appointment.getUrl());
                statement.setObject(7, DateTimeUtil.getDateTimeForSQL(appointment.getStart()));
                statement.setObject(8, DateTimeUtil.getDateTimeForSQL(appointment.getEnd()));
                statement.setObject(9, DateTimeUtil.getDateTimeForSQL());
                statement.setString(10, currentUserName);
                statement.setString(11, currentUserName);
                statement.executeUpdate();
                ResultSet rs = statement.getGeneratedKeys();
                if(rs.next()) {
                    return rs.getInt(1);
                } else {
                    throw new SQLException();
                }
            } catch (SQLException e) {
                throw new SQLException(Localization.getString("error.db.addingappointment"), e);
            }
        } else {
            // error - customer could not be added, therefore appointment could not be added
            throw new SQLException(Localization.getString("error.db.addingappointment"));
        }
    }

    @Override
    public boolean removeById(int id) throws SQLException {
        String sql = "DELETE FROM appointment WHERE appointmentid=?";
        try (PreparedStatement statement = dbConnection.prepareStatement(sql)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            String message = Localization.getString("error.db.removingappointment") + " = " + id;
            throw new SQLException(message, e);
        }
    }

    @Override
    public boolean remove(Appointment appointment) throws SQLException {
        return removeById(appointment.getId());
    }

    @Override
    public List<Appointment> findAll() throws SQLException {
        String query = "SELECT * FROM appointment_view";
        ArrayList<Appointment> appointments = new ArrayList<>();
        try (Statement statement = dbConnection.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                appointments.add(mapResultSetToObject(rs));
            }
            return appointments;
        } catch (SQLException e) {
            String message = Localization.getString("error.db.appointmentquery");
            throw new SQLException(message, e);
        }
    }

    @Override
    public Appointment findById(int id) throws SQLException {
        return findSingle(appointment -> appointment.getId() == id);
    }

    private Appointment mapResultSetToObject(ResultSet rs) throws SQLException {
        Appointment appointment = new Appointment();
        appointment.setId(rs.getInt("appointmentid"));
        appointment.setCustomer(new CustomerRepository().findById(rs.getInt("customerId")));
        appointment.setAppointmentType(AppointmentType.valueOf(rs.getString("title")));
        appointment.setDescription(rs.getString("description"));
        appointment.setAppointmentLocation(AppointmentLocation.valueOf(rs.getString("location")));
        appointment.setConsultant(rs.getString("contact"));
        appointment.setUrl(rs.getString("url"));
        appointment.setStart(DateTimeUtil.getZonedDateTimeFromSQLTimestamp(rs.getTimestamp("start")));
        appointment.setEnd(DateTimeUtil.getZonedDateTimeFromSQLTimestamp(rs.getTimestamp("end")));
        return appointment;
    }

}
