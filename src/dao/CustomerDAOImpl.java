package dao;

import Util.DBQuery;
import Util.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAOImpl implements CustomerDAO {
    ObservableList<Customer> allCustomerList = FXCollections.observableArrayList();

    @Override
    public ObservableList<Customer> findAll() throws SQLException {

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
    }



    @Override
    public Customer findById(int id) {
        return null;
    }

    @Override
    public Boolean create() {
        return null;
    }

    @Override
    public Boolean update() {
        return null;
    }

    @Override
    public Boolean delete() {
        return null;
    }
}
