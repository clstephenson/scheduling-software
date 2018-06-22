package com.clstephenson.dataaccess;

import com.clstephenson.DataRepositoryException;
import com.clstephenson.DateTimeUtil;
import com.clstephenson.Localization;
import com.clstephenson.LoginSession;
import com.clstephenson.datamodels.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements Repository<User> {

    private Connection dbConnection;

    public UserRepository() throws DataRepositoryException {
        try {
            dbConnection = DBManager.getConnection();
        } catch (SQLException e) {
            String message = Localization.getString("error.db.connection");
            throw new DataRepositoryException(e, message);
        }
    }

    @Override
    public int add(User user, LoginSession session) throws DataRepositoryException {
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
            String message = Localization.getString("error.db.addinguser");
            throw new DataRepositoryException(e, message);
        }
    }

    @Override
    public boolean update(User entity, LoginSession session) {
        //todo: implement this method if/when needed
        return false;
    }

    @Override
    public boolean removeById(int id) throws DataRepositoryException {
        String sql = "DELETE FROM user WHERE userid=?";
        try (PreparedStatement statement = dbConnection.prepareStatement(sql)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            String message = Localization.getString("error.db.removinguser") + " = " + id;
            throw new DataRepositoryException(e, message);
        }
    }

    @Override
    public boolean remove(User user) throws DataRepositoryException {
        return removeById(user.getId());
    }

    @Override
    public User findById(int id) throws DataRepositoryException {
        return findSingle(user -> user.getId() == id);
    }

    @Override
    public List<User> findAll() throws DataRepositoryException {
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
            throw new DataRepositoryException(e, message);
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
