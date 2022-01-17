package dao;

import javafx.collections.ObservableList;
import model.User;

import java.sql.SQLException;

public interface UserDAO {
    ObservableList<User> findAll() throws SQLException;
    User findByUsername(String username) throws SQLException;
    Boolean create();
    Boolean update();
    Boolean delete();
}
