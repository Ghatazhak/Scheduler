package dao;

import javafx.collections.ObservableList;
import model.User;

public interface UserDAO {
    ObservableList<User> findAll();
    User findByUsername();
    Boolean create();
    Boolean update();
    Boolean delete();
}
