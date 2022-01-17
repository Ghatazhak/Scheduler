package dao;

import Util.DBQuery;
import Util.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Division;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DivisionDAOImpl implements DivisionDAO {
    ObservableList<Division> allDivisions = FXCollections.observableArrayList();
    @Override
    public ObservableList<Division> findAll() throws SQLException {
        String selectStatement = "SELECT * FROM first_level_divisions";
        Connection connection = JDBC.connection;
        DBQuery.setPreparedStatement(connection, selectStatement);
        PreparedStatement preparedStatement = DBQuery.getPreparedStatement();
        preparedStatement.execute();
        ResultSet resultSet = preparedStatement.getResultSet();

        while (resultSet.next()) {
            allDivisions.add(new Division(resultSet.getInt("Division_ID"), resultSet.getString("Division"), resultSet.getInt("Country_ID")));
        }
        return allDivisions;
    }

    @Override
    public Division findById(int id) {
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
