package dao;

import javafx.collections.ObservableList;
import model.Appointment;

import java.sql.SQLException;

public interface AppointmentDAO {
    ObservableList<Appointment> findAll() throws SQLException;
    Appointment findById(int id);
    Boolean create();
    Boolean update();
    Boolean delete();
}
