package data_access;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contact;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class for static members to access mysql database using DOA abstraction.
 */
public class ContactMYSQL {
    private static ObservableList<Contact> allContacts = FXCollections.observableArrayList();
/** This method returns all Contacts
 * @return  Contact*/
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

    /** This method finds a contact by its id and returns it.
     * @return  Contact*/
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
}
