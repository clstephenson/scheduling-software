package com.clstephenson.dataaccess;

import com.clstephenson.DataRepositoryException;
import com.clstephenson.DateTimeUtil;
import com.clstephenson.Localization;
import com.clstephenson.LoginSession;
import com.clstephenson.datamodels.Country;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CountryRepository implements Repository<Country> {

    private Connection dbConnection;

    public CountryRepository() throws DataRepositoryException {
        try {
            dbConnection = DBManager.getConnection();
        } catch (SQLException e) {
            String message = Localization.getString("error.db.connection");
            throw new DataRepositoryException(e, message);
        }
    }

    @Override
    public int add(Country country, LoginSession session) throws DataRepositoryException {
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
            String message = Localization.getString("error.db.addingcountry");
            throw new DataRepositoryException(e, message);
        }
    }

    @Override
    public boolean update(Country country, LoginSession session) throws DataRepositoryException {
        String sql = "UPDATE country set country=?, lastUpdateBy=? WHERE countryid=?";
        try(PreparedStatement statement = dbConnection.prepareStatement(sql)) {
            statement.setString(1, country.getName());
            statement.setString(2, session.getLoggedInUser().getUserName());
            statement.setInt(3, country.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            String message = Localization.getString("error.db.updatingcountry");
            throw new DataRepositoryException(e, message);
        }
    }

    @Override
    public boolean removeById(int id) throws DataRepositoryException {
        String sql = "DELETE FROM country WHERE countryid=?";
        try (PreparedStatement statement = dbConnection.prepareStatement(sql)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            String message = Localization.getString("error.db.removingcountry") + " = " + id;
            throw new DataRepositoryException(e, message);
        }
    }

    @Override
    public boolean remove(Country country) throws DataRepositoryException {
        return removeById(country.getId());
    }

    @Override
    public Country findById(int id) throws DataRepositoryException {
        return findSingle(country -> country.getId() == id);
    }

    @Override
    public List<Country> findAll() throws DataRepositoryException {
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
            throw new DataRepositoryException(e, message);
        }
    }

    private Country mapResultSetToObject(ResultSet rs) throws SQLException {
        return new Country(rs.getInt("countryid"), rs.getString("country"));
    }
}
