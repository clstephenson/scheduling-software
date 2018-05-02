package com.clstephenson.dataaccess;

import com.clstephenson.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CityRepository implements Repository <City> {

    private Connection dbConnection;

    public CityRepository() throws IOException, SQLException {
        dbConnection = new DBManager().getConnection();
    }

    @Override
    public int add(City city, LoginSession session) throws SQLException, IOException {
        CountryRepository countryRepository = new CountryRepository();
        Country country = countryRepository.findSingle(c -> c.getId() == city.getCountry().getId());
        int countryId;
        if(country == null) {
            countryId = countryRepository.add(city.getCountry(), session);
            city.getCountry().setId(countryId); //add the newly generated ID to the city's country
        } else {
            countryId = country.getId();
        }
        if(countryId != 0) {
            String currentUserName = session.getLoggedInUser().getUserName();
            String sql = "INSERT INTO city (city, countryId, createDate, createdBy, lastUpdateBy) VALUES (?, ?, ?, ?, ?)";
            try(PreparedStatement statement = dbConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, city.getName());
                statement.setInt(2, countryId);
                statement.setObject(3, DateTimeUtil.getDateTimeForSQL());
                statement.setString(4, currentUserName);
                statement.setString(5, currentUserName);
                statement.executeUpdate();
                ResultSet rs = statement.getGeneratedKeys();
                if(rs.next()) {
                    return rs.getInt(1);
                } else {
                    throw new SQLException();
                }
            } catch (SQLException e) {
                throw new SQLException(Localization.getString("error.db.addingcity"), e);
            }
        } else {
            // error - country could not be added, therefore city could not be added
            throw new SQLException(Localization.getString("error.db.addingcity"));
        }
    }

    @Override
    public boolean remove(City entity) throws SQLException {
        throw new NotImplementedException();
        //todo implement City::remove
    }

    @Override
    public List<City> findAll() throws SQLException {
        String query = "SELECT cityid, city, countryid, country FROM city_view";
        ArrayList<City> cities = new ArrayList<>();
        try (Statement statement = dbConnection.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                cities.add(mapResultSetToObject(rs));
            }
            return cities;
        } catch (SQLException e) {
            String message = Localization.getString("error.db.cityquery");
            throw new SQLException(message, e);
        }
    }

    @Override
    public City findById(int id) throws SQLException{
        return findSingle(city -> city.getId() == id);
    }

    private City mapResultSetToObject(ResultSet rs) throws SQLException {
        Country country = new Country(rs.getInt("countryid"), rs.getString("country"));
        return new City(rs.getInt("cityid"), rs.getString("city"), country);
    }
}
