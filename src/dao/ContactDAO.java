package dao;

import javafx.collections.ObservableList;
import model.Contact;

public interface ContactDAO {
    ObservableList<Contact> findAll();
    Contact findById();
    Boolean create();
    Boolean update();
    Boolean delete();
}
