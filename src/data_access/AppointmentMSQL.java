package data_access;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AppointmentMSQL {

        private static ObservableList<Appointment> appointmentsList = FXCollections.observableArrayList();

        public static ObservableList<Appointment> findAll() throws SQLException {
            appointmentsList.clear();
            //String selectStatement = SELECT appointments.Appointment_ID, appointments.Title, appointments.Description, appointments.Location, appointments.Type, appointments.Start, appointments.End, customers.Customer_Name FROM appointments INNER JOIN customers ON appointments.Customer_ID = customers.Customer_ID;
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


        public static Appointment findById(int id) {
            return null;
        }


        public static Boolean create() {
            return null;
        }


        public static Boolean update() {
            return null;
        }


        public static Boolean delete() {
            return null;
        }
    }


