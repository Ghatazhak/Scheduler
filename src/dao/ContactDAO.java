package dao;

import javafx.collections.ObservableList;
import model.Contact;

import java.sql.SQLException;

public interface ContactDAO {
    ObservableList<Contact> findAll() throws SQLException;
    Contact findById(int id);
    Boolean create();
    Boolean update();
    Boolean delete();
}
