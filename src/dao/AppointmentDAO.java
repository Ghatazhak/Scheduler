package dao;

import javafx.collections.ObservableList;
import model.Appointment;

public interface AppointmentDAO {
    ObservableList<Appointment> findAll();
    Appointment findById(int id);
    Boolean create();
    Boolean update();
    Boolean delete();
}
