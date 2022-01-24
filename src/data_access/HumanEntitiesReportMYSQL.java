package data_access;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HumanEntitiesReportMYSQL {
    private static ObservableList<String> allUserNameStrings = FXCollections.observableArrayList();

    public static ObservableList<String> getUsers()  {
        allUserNameStrings.clear();
        try {
            String users = "SELECT distinct users.User_Name as User_Name from users";;
            Connection connection = JDBC.connection;
            DBQuery.setPreparedStatement(connection, users);
            PreparedStatement preparedStatement = DBQuery.getPreparedStatement();
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();

            while (resultSet.next()) {
                allUserNameStrings.add(resultSet.getString("User_Name"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return allUserNameStrings;
    }

    public static ObservableList<String> getCustomers()  {
        allUserNameStrings.clear();
        try {
            String customers = "SELECT distinct customers.Customer_Name as Customer_Name from customers";
            Connection connection = JDBC.connection;
            DBQuery.setPreparedStatement(connection, customers);
            PreparedStatement preparedStatement = DBQuery.getPreparedStatement();
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();

            while (resultSet.next()) {
                allUserNameStrings.add(resultSet.getString("Customer_Name"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return allUserNameStrings;
    }

    public static ObservableList<String> getContacts()  {
        allUserNameStrings.clear();
        try {
            String contacts ="SELECT distinct contacts.Contact_Name as Contact_Name from contacts";
            Connection connection = JDBC.connection;
            DBQuery.setPreparedStatement(connection, contacts);
            PreparedStatement preparedStatement = DBQuery.getPreparedStatement();
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();

            while (resultSet.next()) {
                allUserNameStrings.add(resultSet.getString("Contact_Name"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return allUserNameStrings;
    }





}
