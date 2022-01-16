package dao;

import javafx.collections.ObservableList;
import model.Customer;

import java.sql.SQLException;

public interface CustomerDAO {
   ObservableList<Customer> findAll() throws SQLException;
   Customer findById(int id);
   Boolean create();
   Boolean update();
   Boolean delete();
}
