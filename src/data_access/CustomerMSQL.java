package data_access;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerMSQL {
    private static ObservableList<Customer> allCustomerList = FXCollections.observableArrayList();


    public static ObservableList<Customer> findAll() {
        allCustomerList.clear();

        try {
            String selectStatement = "SELECT * FROM customers";
            Connection connection = JDBC.connection;
            DBQuery.setPreparedStatement(connection, selectStatement);
            PreparedStatement preparedStatement = DBQuery.getPreparedStatement();
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();

            while (resultSet.next()) {
                allCustomerList.add(new Customer(resultSet.getInt("Customer_ID"), resultSet.getString("Customer_Name"), resultSet.getString("Address"), resultSet.getString("Phone"), resultSet.getString("Postal_Code"), resultSet.getInt("Division_ID")));
            }
            return allCustomerList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static Customer findById(int id) {
        return null;
    }


    public static Boolean create() {
        return null;
    }

    public static Boolean update() {
        return null;
    }


    public static Boolean delete(Customer customer) {
        String sqlStatement = "DELETE FROM customers WHERE Customer_ID = (?) ;";
        Connection connection = JDBC.connection;
        try {
            DBQuery.setPreparedStatement(connection,sqlStatement);
            PreparedStatement preparedStatement = DBQuery.getPreparedStatement();
            preparedStatement.setInt(1,customer.getCustomerId());
            preparedStatement.execute();

            if(preparedStatement.getUpdateCount() > 0){
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

