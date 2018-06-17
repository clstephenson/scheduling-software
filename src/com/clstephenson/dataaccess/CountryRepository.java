package com.clstephenson.dataaccess;

import com.clstephenson.DateTimeUtil;
import com.clstephenson.Localization;
import com.clstephenson.LoginSession;
import com.clstephenson.datamodels.Country;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CountryRepository implements Repository<Country> {

    private Connection dbConnection;

    public CountryRepository() {
        try {
            dbConnection = DBManager.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
            //todo fix exception
        }
    }

    @Override
    public int add(Country country, LoginSession session) {
        String currentUserName = session.getLoggedInUser().getUserName();
        String sql = "INSERT INTO country (country, createDate, createdBy, lastUpdateBy) VALUES (?, ?, ?, ?)";
        try(PreparedStatement statement = dbConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, country.getName());
            statement.setObject(2, DateTimeUtil.getDateTimeForSQL());
            statement.setString(3, currentUserName);
            statement.setString(4, currentUserName);
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if(rs.next()) {
                return rs.getInt(1);
            } else {
                throw new SQLException();
            }
        } catch (SQLException e) {
            throw new RuntimeException(Localization.getString("error.db.addingcountry"), e);
            //todo fix exceptions
        }
    }

    @Override
    public boolean update(Country country, LoginSession session) {
        String sql = "UPDATE country set country=?, lastUpdateBy=? WHERE countryid=?";
        try(PreparedStatement statement = dbConnection.prepareStatement(sql)) {
            statement.setString(1, country.getName());
            statement.setString(2, session.getLoggedInUser().getUserName());
            statement.setInt(3, country.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(Localization.getString("error.db.updatingcountry"), e);
            //todo fix exceptions
        }
    }

    @Override
    public boolean removeById(int id) {
        String sql = "DELETE FROM country WHERE countryid=?";
        try (PreparedStatement statement = dbConnection.prepareStatement(sql)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            String message = Localization.getString("error.db.removingcountry") + " = " + id;
            throw new RuntimeException(message, e); //todo fix exception handling
        }
    }

    @Override
    public boolean remove(Country country) {
        return removeById(country.getId());
    }

    @Override
    public List<Country> findAll() {
        String query = "SELECT countryid, country FROM country";
        ArrayList<Country> countries = new ArrayList<>();
        try (Statement statement = dbConnection.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                countries.add(mapResultSetToObject(rs));
            }
            return countries;
        } catch (SQLException e) {
            String message = Localization.getString("error.db.countryquery");
            throw new RuntimeException(message, e); //todo fix exception handling
        }
    }

    @Override
    public Country findById(int id) {
        return findSingle(country -> country.getId() == id);
    }

    private Country mapResultSetToObject(ResultSet rs) throws SQLException {
        return new Country(rs.getInt("countryid"), rs.getString("country"));
    }
}
