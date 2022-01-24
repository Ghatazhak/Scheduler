package data_access;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Class for static members to access mysql database using DOA abstraction.
 */
public class UserMYSQL {
    private static ObservableList<User> allUsers = FXCollections.observableArrayList();

    /** This method returns all Users.*/
    public static ObservableList<User> findAll() throws SQLException {
        allUsers.clear();
        String selectStatement = "SELECT * FROM users";
        Connection connection = JDBC.connection;
        DBQuery.setPreparedStatement(connection, selectStatement);
        PreparedStatement preparedStatement = DBQuery.getPreparedStatement();
        preparedStatement.execute();
        ResultSet resultSet = preparedStatement.getResultSet();

        while (resultSet.next()) {
            allUsers.add(new User(resultSet.getInt("User_ID"), resultSet.getString("User_Name"), resultSet.getString("Password")));
        }
        return allUsers;
    }

    /** This method finds a user by his username. */
    public static User findByUsername(String username) throws SQLException {

        String selectStatement = "SELECT * FROM users WHERE User_Name = ?";
        Connection connection = JDBC.connection;
        DBQuery.setPreparedStatement(connection,selectStatement);
        PreparedStatement preparedStatement = DBQuery.getPreparedStatement();
        preparedStatement.setString(1,username);
        preparedStatement.execute();
        ResultSet resultSet = preparedStatement.getResultSet();

        while(resultSet.next()){
            User user = new User(resultSet.getInt("User_ID"),resultSet.getString("User_Name"),resultSet.getString("Password"));
            return user;
        }
        return null;
    }

    /** This method finds a user by his id.
     * @param userId
     * @return  user*/
    public static User findById(int userId) throws SQLException {
        allUsers.clear();
        String sqlStatement = "SELECT * FROM users";
        Connection connection = JDBC.connection;
        DBQuery.setPreparedStatement(connection, sqlStatement);
        PreparedStatement preparedStatement = DBQuery.getPreparedStatement();
        preparedStatement.execute();
        ResultSet rs = preparedStatement.getResultSet();

        while (rs.next()) {
            User user = new User(rs.getInt("User_ID"), rs.getString("User_Name"), rs.getString("Password"));
            allUsers.add(user);
        }

        for (User u : allUsers) {
            if (u.getUserId() == userId) {
                return u;
            }
        }
        return null;
    }
}
