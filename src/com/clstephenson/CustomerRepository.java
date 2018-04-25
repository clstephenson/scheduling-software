package com.clstephenson;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository implements Repository<Customer> {

    private Connection dbConnection;

    public CustomerRepository() throws IOException, SQLException {
        dbConnection = new DBManager().getConnection();
    }

    @Override
    public void add(Customer customer) throws SQLException, IOException {
        dbConnection.setAutoCommit(false);
        AddressRepository addressRepository = new AddressRepository();
        Address address = addressRepository.findSingle(addr -> addr.getId() == customer.getAddress().getId());
        if(address == null) {
            // todo insert address data into db and get autoinc ID
        } else {
            // todo use address id as field in customer record
        }
        // todo insert customer record into db
    }

    @Override
    public boolean remove(Customer customer) throws SQLException {
        try (CallableStatement statement = dbConnection.prepareCall("{CALL remove_customer(?)}")) {
            statement.setInt(1, customer.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            String message = Localization.getString("error.db.removingcustomer") + " = " + customer.getId();
            throw new SQLException(message, e);
        }
    }

    @Override
    public List<Customer> findAll() throws SQLException{
        String query = "SELECT * FROM customer_view";
        ArrayList<Customer> customers = new ArrayList<>();
        try (Statement statement = dbConnection.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                customers.add(mapResultSetToObject(rs));
            }
            return customers;
        } catch (SQLException e) {
            String message = Localization.getString("error.db.customerquery");
            throw new SQLException(message, e);
        }
    }

    private Customer mapResultSetToObject(ResultSet rs) throws SQLException {
        int customerId = rs.getInt("customerid");
        String customerName = rs.getString("customerName");
        boolean isActive = rs.getInt("active") > 0;
        Country country = new Country(rs.getInt("countryid"), rs.getString("country"));
        City city = new City(rs.getInt("cityid"), rs.getString("city"), country);
        Address address = new Address(
                rs.getInt("addressid"),
                rs.getString("address"),
                rs.getString("address2"),
                city,
                rs.getString("postalCode"),
                rs.getString("phone"));
        return new Customer(customerId, customerName, address, isActive);
    }

}
