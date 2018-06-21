package com.clstephenson.dataaccess;

import com.clstephenson.DataRepositoryException;
import com.clstephenson.DateTimeUtil;
import com.clstephenson.Localization;
import com.clstephenson.LoginSession;
import com.clstephenson.datamodels.City;
import com.clstephenson.datamodels.Country;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CityRepository implements Repository <City> {

    private Connection dbConnection;

    public CityRepository() throws DataRepositoryException {
        try {
            dbConnection = DBManager.getConnection();
        } catch (SQLException e) {
            String message = Localization.getString("error.db.connection");
            throw new DataRepositoryException(e, message);
        }
    }

    @Override
    public int add(City city, LoginSession session) throws DataRepositoryException {
        Country country = Country.getCountryById(city.getCountry().getId());
        if(country == null) {
            //add the country to the DB
            city.getCountry().save();
        }
        int countryId = city.getCountry().getId();
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
                String message = Localization.getString("error.db.addingcity");
                throw new DataRepositoryException(e, message);
            }
        } else {
            // error - country could not be added, therefore city could not be added
            String message = Localization.getString("error.db.addingcity");
            throw new DataRepositoryException(new SQLException(), message);
            //todo change to return 0
        }
    }

    @Override
    public boolean update(City city, LoginSession session) throws DataRepositoryException {
        new CountryRepository().update(city.getCountry(), session);
        String sql = "UPDATE city set city=?, countryId=?, lastUpdateBy=? WHERE cityid=?";
        try(PreparedStatement statement = dbConnection.prepareStatement(sql)) {
            statement.setString(1, city.getName());
            statement.setInt(2, city.getCountry().getId());
            statement.setString(3, session.getLoggedInUser().getUserName());
            statement.setInt(4, city.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            String message = Localization.getString("error.db.updatingcity");
            throw new DataRepositoryException(e, message);
        }
    }

    @Override
    public boolean removeById(int id) throws DataRepositoryException {
        String sql = "DELETE FROM city WHERE cityid=?";
        try (PreparedStatement statement = dbConnection.prepareStatement(sql)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            String message = Localization.getString("error.db.removingcity") + " = " + id;
            throw new DataRepositoryException(e, message);
        }
    }

    @Override
    public boolean remove(City city) throws DataRepositoryException {
        return removeById(city.getId());
    }

    @Override
    public City findById(int id) throws DataRepositoryException {
        return findSingle(city -> city.getId() == id);
    }

    @Override
    public List<City> findAll() throws DataRepositoryException {
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
            throw new DataRepositoryException(e, message);
        }
    }

    private City mapResultSetToObject(ResultSet rs) throws SQLException {
        Country country = new Country(rs.getInt("countryid"), rs.getString("country"));
        return new City(rs.getInt("cityid"), rs.getString("city"), country);
    }
}
