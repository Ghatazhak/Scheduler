package data_access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 * Class for preparing prepared statements and connection to database.
 */
public class DBQuery {
    private static PreparedStatement preparedStatement;
    /** This method prepares the statement. */
    public static void setPreparedStatement(Connection conn, String sqlStatement) throws SQLException {
           preparedStatement = conn.prepareStatement(sqlStatement);
    }
    /** This method gets the prepared statement. */
    public static PreparedStatement getPreparedStatement(){
        return preparedStatement;
    }

}
