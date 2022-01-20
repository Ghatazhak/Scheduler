package data_access;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contact;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactMYSQL {
    private static ObservableList<Contact> allContacts = FXCollections.observableArrayList();

    public static ObservableList<Contact> findAll()  {
        allContacts.clear();
        String selectStatement = "SELECT * FROM contacts";
        Connection connection = JDBC.connection;

        try {
            DBQuery.setPreparedStatement(connection, selectStatement);
            PreparedStatement preparedStatement = DBQuery.getPreparedStatement();
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();

            while (resultSet.next()) {
                allContacts.add(new Contact(resultSet.getInt("Contact_ID"), resultSet.getString("Contact_Name")));
            }
            return allContacts;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }


    public static Contact findById(int id) {
        ObservableList<Contact> contacts;
        contacts = ContactMYSQL.findAll();
        for(Contact c: contacts){
            if(c.getContactId() == id){
                return c;
            }
        }

        return null;
    }


    public static Boolean create() {
        return null;
    }


    public static Boolean update() {
        return null;
    }


    public static Boolean delete() {
        return null;
    }

}
