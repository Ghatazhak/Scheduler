package data_access;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;
import model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerMSQL {
    private static ObservableList<Customer> allCustomerList = FXCollections.observableArrayList();
    private static ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();





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
                allCustomerList.add(new Customer(resultSet.getInt("Customer_ID"), resultSet.getString("Customer_Name"), resultSet.getString("Address"), resultSet.getString("Phone"), resultSet.getString("Postal_Code"), resultSet.getInt("")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allCustomerList;
    }


    public static Customer findById(int id) {
        ObservableList<Customer> customers;
        customers = CustomerMSQL.findAll();
        for(Customer c: customers){
            if(c.getCustomerId() == id){
                return c;
            }
        }

        return null;
    }

    public static void create(Customer customer) {

        try{
            String sqlStatement = "INSERT INTO customers (Customer_Name,Address,Postal_Code,Phone,Division_ID) VALUES(?,?,?,?,?)";
            Connection connection = JDBC.connection;
            DBQuery.setPreparedStatement(connection,sqlStatement);
            PreparedStatement preparedStatement = DBQuery.getPreparedStatement();
            preparedStatement.setString(1,customer.getCustomerName());
            preparedStatement.setString(2,customer.getAddress());
            preparedStatement.setString(3,customer.getPostalCode());
            preparedStatement.setString(4,customer.getPhone());
            preparedStatement.setInt(5, customer.getDivisionId());
            preparedStatement.execute();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static Boolean update(Customer customer) {

        try{
            String sqlStatement = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Division_ID = ? WHERE Customer_ID = ?";
            Connection connection = JDBC.connection;
            DBQuery.setPreparedStatement(connection,sqlStatement);
            PreparedStatement preparedStatement = DBQuery.getPreparedStatement();
            preparedStatement.setString(1,customer.getCustomerName());
            preparedStatement.setString(2,customer.getAddress());
            preparedStatement.setString(3,customer.getPostalCode());
            preparedStatement.setString(4,customer.getPhone());
            preparedStatement.setInt(5,customer.getDivisionId());
            preparedStatement.setInt(6,customer.getCustomerId());
            preparedStatement.execute();
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public static Boolean delete(Customer customer) {
        String sqlStatement = "DELETE FROM customers WHERE Customer_ID = (?) ;";
        Connection connection = JDBC.connection;
        try {
            allAppointments.clear();
            allAppointments = AppointmentMSQL.findAll();
            for(Appointment a: allAppointments){
                if(a.getCustomerId() == customer.getCustomerId()){
                    AppointmentMSQL.delete(a);
                }
            }

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

