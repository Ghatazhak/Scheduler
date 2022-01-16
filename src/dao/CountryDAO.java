package dao;

import javafx.collections.ObservableList;
import model.Country;

public interface CountryDAO {
    ObservableList<Country> findAll();
    Country findById();
    Boolean create();
    Boolean update();
    Boolean delete();
}
