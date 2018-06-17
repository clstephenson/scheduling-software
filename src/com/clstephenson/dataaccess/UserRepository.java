package com.clstephenson.dataaccess;

import com.clstephenson.DateTimeUtil;
import com.clstephenson.Localization;
import com.clstephenson.LoginSession;
import com.clstephenson.datamodels.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements Repository<User> {

    private Connection dbConnection;

    public UserRepository() {
        try {
            dbConnection = DBManager.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
            //todo fix exception
        }
    }

    @Override
    public int add(User user, LoginSession session) {
        String currentUserName = session.getLoggedInUser().getUserName();
        String sql = "INSERT INTO user (userName, password, active, createDate, createBy, lastUpdatedBy) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try(PreparedStatement statement = dbConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getPassword());
            statement.setInt(3, user.isActive() ? 1 : 0);
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
            throw new RuntimeException(Localization.getString("error.db.addinguser"), e);
            //todo: fix exceptions
        }
    }

    @Override
    public boolean update(User entity, LoginSession session) {
        //todo: implement this method if needed
        return false;
    }

    @Override
    public boolean removeById(int id) {
        String sql = "DELETE FROM user WHERE userid=?";
        try (PreparedStatement statement = dbConnection.prepareStatement(sql)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            String message = Localization.getString("error.db.removinguser") + " = " + id;
            throw new RuntimeException(message, e); //todo fix exception handling
        }
    }

    @Override
    public boolean remove(User user) {
        return removeById(user.getId());
    }

    @Override
    public User findById(int id) {
        return findSingle(user -> user.getId() == id);
    }

    @Override
    public List<User> findAll() {
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
            throw new RuntimeException(message, e); //todo fix exception handling
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
