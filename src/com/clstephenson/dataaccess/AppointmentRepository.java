package com.clstephenson.dataaccess;

import com.clstephenson.*;
import com.clstephenson.datamodels.Appointment;
import com.clstephenson.datamodels.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentRepository implements Repository<Appointment> {

    private Connection dbConnection;

    public AppointmentRepository() {
        try {
            dbConnection = DBManager.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
            //todo fix exception
        }
    }

    @Override
    public int add(Appointment appointment, LoginSession session) {
        Customer customer = Customer.getCustomerById(appointment.getCustomer().getId());
        if(customer == null) {
            //add the customer to the DB
            appointment.getCustomer().save();
        }
        int customerId = appointment.getCustomer().getId();
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
                statement.setObject(7, DateTimeUtil.getTimestampFromLocalDateTime(appointment.getStart()));
                statement.setObject(8, DateTimeUtil.getTimestampFromLocalDateTime(appointment.getEnd()));
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
                throw new RuntimeException(Localization.getString("error.db.addingappointment"), e);
            }
        } else {
            // error - customer could not be added, therefore appointment could not be added
            throw new RuntimeException(Localization.getString("error.db.addingappointment"));
            // TODO: change to return 0 instead.
        }
    }

    @Override
    public boolean update(Appointment appointment, LoginSession session) {
        String sql = "UPDATE appointment set customerId=?, title=?, description=?, location=?, contact=?, url=?," +
                "start=?, end=?, lastUpdateBy=? WHERE appointmentid=?";
        try(PreparedStatement statement = dbConnection.prepareStatement(sql)) {
            statement.setInt(1, appointment.getCustomer().getId());
            statement.setString(2, appointment.getAppointmentType().name());
            statement.setString(3, appointment.getDescription());
            statement.setString(4, appointment.getAppointmentLocation().name());
            statement.setString(5, appointment.getConsultant());
            statement.setString(6, appointment.getUrl());
            statement.setObject(7, DateTimeUtil.getTimestampFromLocalDateTime(appointment.getStart()));
            statement.setObject(8, DateTimeUtil.getTimestampFromLocalDateTime(appointment.getEnd()));
            statement.setString(9, session.getLoggedInUser().getUserName());
            statement.setInt(10, appointment.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(Localization.getString("error.db.updatingappointment"), e);
            //todo fix exceptions
        }
    }

    @Override
    public boolean removeById(int id) {
        String sql = "DELETE FROM appointment WHERE appointmentid=?";
        try (PreparedStatement statement = dbConnection.prepareStatement(sql)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            String message = Localization.getString("error.db.removingappointment") + " = " + id;
            throw new RuntimeException(message, e); //todo fix exception handling
        }
    }

    @Override
    public boolean remove(Appointment appointment) {
        return removeById(appointment.getId());
    }

    @Override
    public List<Appointment> findAll() {
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
            throw new RuntimeException(message, e); //todo fix exception handling
        }
    }

    @Override
    public Appointment findById(int id) {
        return findSingle(appointment -> appointment.getId() == id);
    }

    private Appointment mapResultSetToObject(ResultSet rs) throws SQLException {
        Appointment appointment = new Appointment();
        appointment.setId(rs.getInt("appointmentid"));
        //appointment.setCustomer(new CustomerRepository().findById(rs.getInt("customerId")));
        appointment.setCustomer(Customer.getCustomerById(rs.getInt("customerId")));
        appointment.setAppointmentType(AppointmentType.valueOf(rs.getString("title")));
        appointment.setDescription(rs.getString("description"));
        appointment.setAppointmentLocation(AppointmentLocation.valueOf(rs.getString("location")));
        appointment.setConsultant(rs.getString("contact"));
        appointment.setUrl(rs.getString("url"));
        appointment.setStart(DateTimeUtil.getLocalDateTimeFromTimestamp(rs.getTimestamp("start")));
        appointment.setEnd(DateTimeUtil.getLocalDateTimeFromTimestamp(rs.getTimestamp("end")));
        return appointment;
    }

}
