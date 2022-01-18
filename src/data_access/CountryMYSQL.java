package data_access;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Country;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CountryMYSQL {
    public static ObservableList<Country> allCountries = FXCollections.observableArrayList();

    public static ObservableList<Country> findAll() throws SQLException {
        allCountries.clear();
        String selectStatement = "SELECT * FROM countries";
        Connection connection = JDBC.connection;
        DBQuery.setPreparedStatement(connection, selectStatement);
        PreparedStatement preparedStatement = DBQuery.getPreparedStatement();
        preparedStatement.execute();
        ResultSet resultSet = preparedStatement.getResultSet();

        while (resultSet.next()) {
            allCountries.add(new Country(resultSet.getInt("Country_ID"), resultSet.getString("Country")));
        }
        return allCountries;

    }


    public static Country findById(int id) throws SQLException {
        ObservableList<Country>  tempList = findAll();
        for (Country c:tempList) {
            if(id == c.getCountryId()){
                return c;
            }
        }
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
