package com.clstephenson;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AddressRepository implements Repository<Address> {

    private Connection dbConnection;

    public AddressRepository() throws IOException, SQLException {
        dbConnection = new DBManager().getConnection();
    }

    @Override
    public void add(Address address) throws SQLException {

    }

    @Override
    public boolean remove(Address address) throws SQLException {
        return false;
    }

    @Override
    public List<Address> findAll() throws SQLException {
        String query = "SELECT * FROM address_view";
        ArrayList<Address> addresses = new ArrayList<>();
        try (Statement statement = dbConnection.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                addresses.add(mapResultSetToObject(rs));
            }
            return addresses;
        } catch (SQLException e) {
            String message = Localization.getString("error.db.addressquery");
            throw new SQLException(message, e);
        }
    }

    private Address mapResultSetToObject(ResultSet rs) throws SQLException {
        int addressId = rs.getInt("addressid");
        String address = rs.getString("address");
        String address2 = rs.getString("address2");
        String postalCode = rs.getString("postalCode");
        String phone = rs.getString("phone");
        Country country = new Country(rs.getInt("countryid"), rs.getString("country"));
        City city = new City(rs.getInt("cityid"), rs.getString("city"), country);

        return new Address(addressId, address, address2, city, postalCode, phone);
    }
}
