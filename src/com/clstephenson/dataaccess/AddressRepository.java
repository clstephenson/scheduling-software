package com.clstephenson.dataaccess;

import com.clstephenson.DateTimeUtil;
import com.clstephenson.Localization;
import com.clstephenson.LoginSession;
import com.clstephenson.datamodels.Address;
import com.clstephenson.datamodels.City;
import com.clstephenson.datamodels.Country;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddressRepository implements Repository<Address> {

    private Connection dbConnection;

    public AddressRepository() {
        try {
            dbConnection = DBManager.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
            //todo fix exception handling
        }
    }

    @Override
    public int add(Address address, LoginSession session) {
        City city = City.getCityById(address.getCity().getId());
        if(city == null) {
            //add the city to the DB
            address.getCity().save();
        }
        int cityId = address.getCity().getId();
        if(cityId != 0) {
            String currentUserName = session.getLoggedInUser().getUserName();
            String sql = "INSERT INTO address " +
                    "(address, address2, cityId, postalCode, phone, createDate, createdBy, lastUpdateBy) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try(PreparedStatement statement = dbConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, address.getAddressLine1());
                statement.setString(2, address.getAddressLine2());
                statement.setInt(3, cityId);
                statement.setString(4, address.getZipCode());
                statement.setString(5, address.getPhoneNumber());
                statement.setObject(6, DateTimeUtil.getDateTimeForSQL());
                statement.setString(7, currentUserName);
                statement.setString(8, currentUserName);
                statement.executeUpdate();
                ResultSet rs = statement.getGeneratedKeys();
                if(rs.next()) {
                    return rs.getInt(1);
                } else {
                    throw new SQLException();
                }
            } catch (SQLException e) {
                throw new RuntimeException(Localization.getString("error.db.addingaddress"), e);
            }
        } else {
            // error - city could not be added, therefore address could not be added
            throw new RuntimeException(Localization.getString("error.db.addingaddress"));
            //todo change this to return 0 instead of throwing exception
        }
    }

    @Override
    public boolean update(Address address, LoginSession session) {
        new CityRepository().update(address.getCity(), session);
        String sql = "UPDATE address set address=?, address2=?, cityId=?, postalCode=?, phone=?, lastUpdateBy=? " +
                "WHERE addressid=?";
        try(PreparedStatement statement = dbConnection.prepareStatement(sql)) {
            statement.setString(1, address.getAddressLine1());
            statement.setString(2, address.getAddressLine2());
            statement.setInt(3, address.getCity().getId());
            statement.setString(4, address.getZipCode());
            statement.setString(5, address.getPhoneNumber());
            statement.setString(6, session.getLoggedInUser().getUserName());
            statement.setInt(7, address.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(Localization.getString("error.db.updatingaddress"), e);
            //todo handle exceptions
        }
    }

    @Override
    public boolean removeById(int id) {
        String sql = "DELETE FROM address WHERE addressid=?";
        try (PreparedStatement statement = dbConnection.prepareStatement(sql)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            String message = Localization.getString("error.db.removingaddress") + " = " + id;
            throw new RuntimeException(message, e); //todo fix exception handling
        }
    }

    @Override
    public boolean remove(Address address) {
        return removeById(address.getId());
    }

    @Override
    public List<Address> findAll() {
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
            throw new RuntimeException(message, e); //todo fix exception handling
        }
    }

    @Override
    public Address findById(int id) {
        return findSingle(address -> address.getId() == id);
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
