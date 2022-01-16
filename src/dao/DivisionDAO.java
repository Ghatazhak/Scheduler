package dao;

import javafx.collections.ObservableList;
import model.Division;

public interface DivisionDAO {
    ObservableList<Division> findAll();
    Division findById();
    Boolean create();
    Boolean update();
    Boolean delete();
}
