package dao;

import javafx.collections.ObservableList;
import model.Contact;

public interface ContactDAO {
    ObservableList<Contact> findAll();
    Contact findById(int id);
    Boolean create();
    Boolean update();
    Boolean delete();
}
