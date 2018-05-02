package com.clstephenson.dataaccess;

import com.clstephenson.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddressRepository implements Repository<Address> {

    private Connection dbConnection;

    public AddressRepository() throws IOException, SQLException {
        dbConnection = new DBManager().getConnection();
    }

    @Override
    public int add(Address address, LoginSession session) throws SQLException, IOException {
        CityRepository cityRepository = new CityRepository();
        City city = cityRepository.findSingle(c -> c.getId() == address.getCity().getId());
        int cityId;
        if(city == null) {
            cityId = cityRepository.add(address.getCity(), session);
            address.getCity().setId(cityId); //add the newly generated ID to the address's city
        } else {
            cityId = city.getId();
        }
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
                throw new SQLException(Localization.getString("error.db.addingaddress"), e);
            }
        } else {
            // error - city could not be added, therefore address could not be added
            throw new SQLException(Localization.getString("error.db.addingaddress"));
        }
    }

    @Override
    public boolean remove(Address address) throws SQLException {
        throw new NotImplementedException();
        //todo implement Address::remove
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

    @Override
    public Address findById(int id) throws SQLException{
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
