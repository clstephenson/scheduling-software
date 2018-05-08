package com.clstephenson.dataaccess;

import com.clstephenson.*;

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
    public int add(Customer customer, LoginSession session) throws SQLException, IOException {
        AddressRepository addressRepository = new AddressRepository();
        Address address = addressRepository.findSingle(addr -> addr.getId() == customer.getAddress().getId());
        int addressId;
        if(address == null) {
            addressId = addressRepository.add(customer.getAddress(), session);
            customer.getAddress().setId(addressId); //add the newly generated ID to the customer address
        } else {
            addressId = address.getId();
        }
        if(addressId != 0) {
            String currentUserName = session.getLoggedInUser().getUserName();
            String sql = "INSERT INTO customer (customerName, addressid, active, createDate, createdBy, lastUpdateBy) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
            try(PreparedStatement statement = dbConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, customer.getName());
                statement.setInt(2, addressId);
                statement.setInt(3, customer.isActive() ? 1 : 0);
                statement.setObject(4, DateTimeUtil.getDateTimeForSQL());
                statement.setString(5, currentUserName);
                statement.setString(6, currentUserName);
                statement.executeUpdate();
                ResultSet rs = statement.getGeneratedKeys();
                if(rs.next()) {
                    return rs.getInt(1);
                } else {
                    throw new SQLException();
                }
            } catch (SQLException e) {
                throw new SQLException(Localization.getString("error.db.addingcustomer"), e);
            }
        } else {
            // error - address could not be added, therefore customer could not be added
            throw new SQLException(Localization.getString("error.db.addingcustomer"));
        }

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
    public List<Customer> findAll() throws SQLException, IOException{
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

    @Override
    public Customer findById(int id) throws SQLException, IOException{
        return findSingle(customer -> customer.getId() == id);
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
