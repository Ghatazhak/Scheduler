package dao;

import javafx.collections.ObservableList;
import model.Country;

import java.sql.SQLException;

public interface CountryDAO {
    ObservableList<Country> findAll() throws SQLException;
    Country findById(int id) throws SQLException;
    Boolean create();
    Boolean update();
    Boolean delete();
}
