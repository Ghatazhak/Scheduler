package dao;

import javafx.collections.ObservableList;
import model.User;

public interface UserDAO {
    ObservableList<User> findAll();
    User findById();
    Boolean create();
    Boolean update();
    Boolean delete();
}
