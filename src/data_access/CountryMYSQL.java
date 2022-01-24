package data_access;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Country;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class for static members to access mysql database using DOA abstraction.
 */
public class CountryMYSQL {
    public static ObservableList<Country> allCountries = FXCollections.observableArrayList();
    /** This method returns all Countries.
     * @return  list of countries*/
    public static ObservableList<Country> findAll()  {
        allCountries.clear();
        try {
            String selectStatement = "SELECT * FROM countries";
            Connection connection = JDBC.connection;
            DBQuery.setPreparedStatement(connection, selectStatement);
            PreparedStatement preparedStatement = DBQuery.getPreparedStatement();
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();

            while (resultSet.next()) {
                allCountries.add(new Country(resultSet.getInt("Country_ID"), resultSet.getString("Country")));
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return allCountries;
    }

    /** This method returns Countries by their id.
     * @param id
     * @return country*/
    public static Country findById(int id) throws SQLException {
        ObservableList<Country>  tempList = findAll();
        for (Country c:tempList) {
            if(id == c.getCountryId()){
                return c;
            }
        }
        return null;
    }
}
