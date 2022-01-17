package dao;

import Util.DBQuery;
import Util.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contact;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactDAOImpl implements ContactDAO {
    ObservableList<Contact> allContacts = FXCollections.observableArrayList();

    @Override
    public ObservableList<Contact> findAll() throws SQLException {
        String selectStatement = "SELECT * FROM contacts";
        Connection connection = JDBC.connection;
        DBQuery.setPreparedStatement(connection, selectStatement);
        PreparedStatement preparedStatement = DBQuery.getPreparedStatement();
        preparedStatement.execute();
        ResultSet resultSet = preparedStatement.getResultSet();

        while (resultSet.next()) {
            allContacts.add(new Contact(resultSet.getInt("Contact_ID"), resultSet.getString("Contact_Name")));
        }
        return allContacts;
    }

    @Override
    public Contact findById(int id) {
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
