package com.clstephenson.dataaccess;

import com.clstephenson.Address;
import com.clstephenson.Appointment;
import com.clstephenson.LoginSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AppointmentRepository implements Repository<Appointment> {

    private Connection dbConnection;

    public AppointmentRepository() throws IOException, SQLException {
        dbConnection = new DBManager().getConnection();
    }

    @Override
    public int add(Appointment entity, LoginSession session) throws SQLException, IOException {
        return 0;
    }

    @Override
    public boolean remove(Appointment entity) throws SQLException {
        return false;
    }

    @Override
    public List<Appointment> findAll() throws SQLException {
        return null;
    }

    private Address mapResultSetToObject(ResultSet rs) throws SQLException {
        return new Address();
    }

}
