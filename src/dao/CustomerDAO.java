package dao;

import javafx.collections.ObservableList;
import model.Customer;

public interface CustomerDAO {
   ObservableList<Customer> findAll();
   Customer findById(int id);
   Boolean create();
   Boolean update();
   Boolean delete();
}
