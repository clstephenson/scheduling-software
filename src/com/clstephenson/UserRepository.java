package com.clstephenson;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements Repository<User> {

    private Connection dbConnection;

    public UserRepository() throws SQLException, IOException {
        dbConnection = new DBManager().getConnection();
    }

    @Override
    public int add(User user, LoginSession session) throws SQLException, IOException{
        String currentUserName = session.getLoggedInUser().getUserName();
        String sql = "INSERT INTO user (userName, password, active, createDate, createBy, lastUpdatedBy) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try(PreparedStatement statement = dbConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getPassword());
            statement.setInt(3, user.isActive() ? 1 : 0);
            statement.setObject(4, DateTimeUtil.getCurrentDateTimeForSQL());
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
            throw new SQLException(Localization.getString("error.db.addinguser"), e);
        }
    }

    @Override
    public boolean remove(User user) {
        throw new NotImplementedException();
        //todo implement User::remove
    }

    @Override
    public List<User> findAll() throws SQLException {
        String query = "SELECT userId, userName, password, active FROM user";
        ArrayList<User> users = new ArrayList<>();
        try (Statement statement = dbConnection.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                users.add(mapResultSetToObject(rs));
            }
            return users;
        } catch (SQLException e) {
            String message = Localization.getString("error.db.userquery");
            throw new SQLException(message, e);
        }
    }

    private User mapResultSetToObject(ResultSet rs) throws SQLException {
        int userId = rs.getInt("userId");
        String userName = rs.getString("userName");
        String password = rs.getString("password");
        boolean isActive = rs.getInt("active") > 0;
        return new User(userId, userName, password, isActive);
    }

}
