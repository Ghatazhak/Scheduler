package dao;

import Util.DBQuery;
import Util.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class AppointmentDAOImpl implements AppointmentDAO {
   private ObservableList<Appointment> appointmentsList = FXCollections.observableArrayList();
    @Override
    public ObservableList<Appointment> findAll() throws SQLException {


        String selectStatement = "SELECT * FROM appointments";
        Connection connection = JDBC.connection;
        DBQuery.setPreparedStatement(connection,selectStatement);
        PreparedStatement preparedStatement = DBQuery.getPreparedStatement();
        preparedStatement.execute();
        ResultSet resultSet = preparedStatement.getResultSet();

        while(resultSet.next()){
            appointmentsList.add(new Appointment(resultSet.getInt("Appointment_ID"),resultSet.getString("Title"),resultSet.getString("Description"),resultSet.getString("Location"),resultSet.getString("Type"),(resultSet.getTimestamp("Start").toLocalDateTime()),(resultSet.getTimestamp("End").toLocalDateTime()),resultSet.getInt("Customer_ID"),resultSet.getInt("User_ID"),resultSet.getInt("Contact_ID")));
        }
        return appointmentsList;
    }

    @Override
    public Appointment findById(int id) {
        return null;
    }

    @Override
    public Boolean create() {
        return null;
    }

    @Override
    public Boolean update() {
        return null;
    }

    @Override
    public Boolean delete() {
        return null;
    }
}
