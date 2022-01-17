package dao;

import Util.DBQuery;
import Util.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Country;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CountryDAOImpl implements CountryDAO {
    ObservableList<Country> allCountries = FXCollections.observableArrayList();
    @Override
    public ObservableList<Country> findAll() throws SQLException {
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

    @Override
    public Country findById(int id) throws SQLException {
        ObservableList<Country>  tempList = findAll();
        for (Country c:tempList) {
            if(id == c.getCountryId()){
                return c;
            }
        }
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
