package com.clstephenson;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements Repository<User> {

    private String dbFields = "userId, userName, password, active";
    private Connection dbConnection;

    public UserRepository() throws SQLException, IOException {
        dbConnection = new DBManager().getConnection();
    }

    @Override
    public void add(User user) {

    }

    @Override
    public boolean remove(User user) {
//        String prepareString = "DELETE from user WHERE userId = ?";
//        try (PreparedStatement statement = dbConnection.prepareStatement(prepareString)) {
//            statement.setInt(1, user.getId());
//            statement.execute();
//        } catch (SQLException ex) {
//            // todo handle exception
//        }
        return false;
    }

    @Override
    public List<User> findAll() {
        String query = "SELECT " + dbFields + " FROM user";
        ArrayList<User> users = new ArrayList<>();
        try (Statement statement = dbConnection.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                users.add(mapResultSetToObject(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // todo handle exception
        }
        return users;
    }

    private User mapResultSetToObject(ResultSet rs) throws SQLException {
        int userId = rs.getInt("userId");
        String userName = rs.getString("userName");
        String password = rs.getString("password");
        boolean isActive = rs.getInt("active") > 0;
        return new User(userId, userName, password, isActive);
    }

}
