package data_access;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.ContactReport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class for static members to access mysql database using DOA abstraction.
 */
public class ContactReportMYSQL {
    private static ObservableList<ContactReport> contactReport = FXCollections.observableArrayList();
    /** This method returns a report of Contact by month/type amount.
     * @return  list of contact reports*/
    public static ObservableList<ContactReport> getContactReport()  {
        contactReport.clear();
        try {
            String selectStatement = "SELECT MONTHNAME(start) as Month, type, COUNT(*) as Amount FROM appointments GROUP BY MONTH(start), type";
            Connection connection = JDBC.connection;
            DBQuery.setPreparedStatement(connection, selectStatement);
            PreparedStatement preparedStatement = DBQuery.getPreparedStatement();
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();

            while (resultSet.next()) {
                contactReport.add(new ContactReport(resultSet.getString("Month"), resultSet.getString("type"), resultSet.getInt("Amount")));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return contactReport;
    }
}
