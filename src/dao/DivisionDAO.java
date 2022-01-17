package dao;

import javafx.collections.ObservableList;
import model.Division;

import java.sql.SQLException;

public interface DivisionDAO {
    ObservableList<Division> findAll() throws SQLException;
    Division findById(int id);
    Boolean create();
    Boolean update();
    Boolean delete();
}
