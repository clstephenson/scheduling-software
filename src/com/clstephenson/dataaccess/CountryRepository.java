package com.clstephenson.dataaccess;

import com.clstephenson.Country;
import com.clstephenson.DateTimeUtil;
import com.clstephenson.Localization;
import com.clstephenson.LoginSession;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CountryRepository implements Repository<Country> {

    private Connection dbConnection;

    public CountryRepository() throws IOException, SQLException {
        dbConnection = new DBManager().getConnection();
    }

    @Override
    public int add(Country country, LoginSession session) throws SQLException, IOException {
        String currentUserName = session.getLoggedInUser().getUserName();
        String sql = "INSERT INTO country (country, createDate, createdBy, lastUpdateBy) VALUES (?, ?, ?, ?)";
        try(PreparedStatement statement = dbConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, country.getName());
            statement.setObject(2, DateTimeUtil.getCurrentDateTimeForSQL());
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
            throw new SQLException(Localization.getString("error.db.addingcountry"), e);
        }
    }

    @Override
    public boolean remove(Country country) throws SQLException {
        throw new NotImplementedException();
        //todo implement Country::remove
    }

    @Override
    public List<Country> findAll() throws SQLException {
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
            throw new SQLException(message, e);
        }
    }

    private Country mapResultSetToObject(ResultSet rs) throws SQLException {
        return new Country(rs.getInt("countryid"), rs.getString("country"));
    }
}
