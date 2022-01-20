package data_access;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;

import java.sql.*;

public class AppointmentMSQL {

        private static ObservableList<Appointment> appointmentsList = FXCollections.observableArrayList();

        public static ObservableList<Appointment> findAll()  {
            appointmentsList.clear();
            try {
                String selectStatement = "SELECT * FROM appointments";
                Connection connection = JDBC.connection;
                DBQuery.setPreparedStatement(connection, selectStatement);
                PreparedStatement preparedStatement = DBQuery.getPreparedStatement();
                preparedStatement.execute();
                ResultSet resultSet = preparedStatement.getResultSet();

                while (resultSet.next()) {
                    appointmentsList.add(new Appointment(resultSet.getInt("Appointment_ID"), resultSet.getString("Title"), resultSet.getString("Description"), resultSet.getString("Location"), resultSet.getString("Type"), (resultSet.getTimestamp("Start").toLocalDateTime()), (resultSet.getTimestamp("End").toLocalDateTime()), resultSet.getInt("Customer_ID"), resultSet.getInt("User_ID"), resultSet.getInt("Contact_ID")));
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
            return appointmentsList;
        }

        public static Appointment findById(int id) {
            return null;
        }


        public static void create(Appointment appointment)  {

            try{
                String sqlStatement = "INSERT INTO appointments (Title,Description,Location,Type,Start,End,Customer_ID,User_ID,Contact_ID) VALUES(?,?,?,?,?,?,?,?,?)";
                Connection connection = JDBC.connection;
                DBQuery.setPreparedStatement(connection,sqlStatement);
                PreparedStatement preparedStatement = DBQuery.getPreparedStatement();
                preparedStatement.setString(1,appointment.getTitle());
                preparedStatement.setString(2,appointment.getDescription());
                preparedStatement.setString(3,appointment.getLocation());
                preparedStatement.setString(4,appointment.getType());
                preparedStatement.setTimestamp(5, Timestamp.valueOf(appointment.getStartDateTime()));
                preparedStatement.setTimestamp(6, Timestamp.valueOf(appointment.getEndDateTime()));
                preparedStatement.setInt(7, appointment.getCustomerId());
                preparedStatement.setInt(8,appointment.getUserId());
                preparedStatement.setInt(9, appointment.getContactId());
                preparedStatement.execute();
            } catch (SQLException e){
                e.printStackTrace();
            }

            //ResultSet resultSet = preparedStatement.getResultSet();

//            while(resultSet.next()){
//                appointmentsList.add(new Appointment(resultSet.getInt("Appointment_ID"),resultSet.getString("Title"),resultSet.getString("Description"),resultSet.getString("Location"),resultSet.getString("Type"),(resultSet.getTimestamp("Start").toLocalDateTime()),(resultSet.getTimestamp("End").toLocalDateTime()),resultSet.getInt("Customer_ID"),resultSet.getInt("User_ID"),resultSet.getInt("Contact_ID")));
//            }
//            return appointmentsList;
        }

        public static Boolean update(Appointment appointment) {

            try{
                String sqlStatement = "UPDATE appointments SET Title = ?, Description = ?, Location = ?, Type = ?, Customer_ID = ?, User_ID = ?,Contact_ID = ? WHERE Appointment_ID = ?";
                Connection connection = JDBC.connection;
                DBQuery.setPreparedStatement(connection,sqlStatement);
                PreparedStatement preparedStatement = DBQuery.getPreparedStatement();
                preparedStatement.setString(1,appointment.getTitle());
                preparedStatement.setString(2,appointment.getDescription());
                preparedStatement.setString(3,appointment.getLocation());
                preparedStatement.setString(4,appointment.getType());
                preparedStatement.setInt(5,appointment.getCustomerId());
                preparedStatement.setInt(6,appointment.getUserId());
                preparedStatement.setInt(7,appointment.getContactId());
                preparedStatement.setInt(8,appointment.getAppointmentId());
                preparedStatement.execute();
            } catch (SQLException e){
                e.printStackTrace();
                return false;
            }
            return true;
        }


        public static String delete(Appointment appointment) {
            String sqlStatement = "DELETE FROM appointments WHERE Appointment_ID = (?) ;";
            Connection connection = JDBC.connection;
            try {
                DBQuery.setPreparedStatement(connection,sqlStatement);
                PreparedStatement preparedStatement = DBQuery.getPreparedStatement();
                preparedStatement.setInt(1,appointment.getAppointmentId());
                preparedStatement.execute();

                if(preparedStatement.getUpdateCount() > 0){
                    String result = "Appointment ID: "+appointment.getAppointmentId()+ " Type: " +appointment.getType()+ " deleted.";
                    return result;
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }
    }


