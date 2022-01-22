package data_access;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

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

                    Timestamp timestamp = resultSet.getTimestamp("Start");
                    ZoneId systemDefault = ZoneId.systemDefault();
                    ZonedDateTime zdt = timestamp.toLocalDateTime().atZone(ZoneId.of("UTC"));
                    ZonedDateTime zldt = zdt.withZoneSameInstant(systemDefault);
                    Timestamp endTimeStamp = resultSet.getTimestamp("End");
                    ZonedDateTime endzdt = endTimeStamp.toLocalDateTime().atZone(ZoneId.of("UTC"));
                    ZonedDateTime endzldt = endzdt.withZoneSameInstant(systemDefault);


                    appointmentsList.add(new Appointment(resultSet.getInt("Appointment_ID"), resultSet.getString("Title"), resultSet.getString("Description"), resultSet.getString("Location"), resultSet.getString("Type"), zldt.toLocalDateTime(), endzldt.toLocalDateTime(), resultSet.getInt("Customer_ID"), resultSet.getInt("User_ID"), resultSet.getInt("Contact_ID")));
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

                LocalDateTime localDateTime = appointment.getStartDateTime();
                ZonedDateTime localDateTimeZoned = ZonedDateTime.of(localDateTime, ZoneId.systemDefault());
                ZonedDateTime UTClocalDateTimeZoned = localDateTimeZoned.withZoneSameInstant(ZoneOffset.UTC);

                LocalDateTime endLocalDateTime = appointment.getEndDateTime();
                ZonedDateTime endLocalDateTimeZoned = ZonedDateTime.of(endLocalDateTime, ZoneId.systemDefault());
                ZonedDateTime endUTClocalDateTimeZoned = endLocalDateTimeZoned.withZoneSameInstant(ZoneOffset.UTC);

                preparedStatement.setTimestamp(5, Timestamp.valueOf(UTClocalDateTimeZoned.toLocalDateTime()));
                preparedStatement.setTimestamp(6, Timestamp.valueOf(endUTClocalDateTimeZoned.toLocalDateTime()));

                preparedStatement.setInt(7, appointment.getCustomerId());
                preparedStatement.setInt(8,appointment.getUserId());
                preparedStatement.setInt(9, appointment.getContactId());
                preparedStatement.execute();
            } catch (SQLException e){
                e.printStackTrace();
            }


        }

        public static Boolean update(Appointment appointment) {

            try{
                String sqlStatement = "UPDATE appointments SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Customer_ID = ?, User_ID = ?,Contact_ID = ? WHERE Appointment_ID = ?";
                Connection connection = JDBC.connection;
                DBQuery.setPreparedStatement(connection,sqlStatement);
                PreparedStatement preparedStatement = DBQuery.getPreparedStatement();
                preparedStatement.setString(1,appointment.getTitle());
                preparedStatement.setString(2,appointment.getDescription());
                preparedStatement.setString(3,appointment.getLocation());
                preparedStatement.setString(4,appointment.getType());

                LocalDateTime localDateTime = appointment.getStartDateTime();
                ZonedDateTime localDateTimeZoned = ZonedDateTime.of(localDateTime, ZoneId.systemDefault());
                ZonedDateTime UTClocalDateTimeZoned = localDateTimeZoned.withZoneSameInstant(ZoneOffset.UTC);
                LocalDateTime endLocalDateTime = appointment.getEndDateTime();
                ZonedDateTime endLocalDateTimeZoned = ZonedDateTime.of(endLocalDateTime, ZoneId.systemDefault());
                ZonedDateTime endUTClocalDateTimeZoned = endLocalDateTimeZoned.withZoneSameInstant(ZoneOffset.UTC);


                preparedStatement.setTimestamp(5, Timestamp.valueOf(UTClocalDateTimeZoned.toLocalDateTime()));
                preparedStatement.setTimestamp(6, Timestamp.valueOf(endUTClocalDateTimeZoned.toLocalDateTime()));


                preparedStatement.setInt(7,appointment.getCustomerId());
                preparedStatement.setInt(8,appointment.getUserId());
                preparedStatement.setInt(9,appointment.getContactId());
                preparedStatement.setInt(10,appointment.getAppointmentId());
                preparedStatement.execute();
            } catch (SQLException e){
                e.printStackTrace();
                System.out.println("Error in AppointmentMSQL.java UPDATE Method.");
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


