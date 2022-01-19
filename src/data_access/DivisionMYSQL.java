package data_access;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Division;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DivisionMYSQL {
    public static ObservableList<Division> allDivisions = FXCollections.observableArrayList();

    public static ObservableList<Division> findAll()  {
        allDivisions.clear();
        try {
            String selectStatement = "SELECT * FROM first_level_divisions";
            Connection connection = JDBC.connection;
            DBQuery.setPreparedStatement(connection, selectStatement);
            PreparedStatement preparedStatement = DBQuery.getPreparedStatement();
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();

            while (resultSet.next()) {

                allDivisions.add(new Division(resultSet.getInt("Division_ID"), resultSet.getString("Division"), resultSet.getInt("Country_ID")));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return allDivisions;
    }


    public static Division findById(int id) {
        try{
            String sqlStatement = "SELECT * FROM first_level_divisions  WHERE Division_ID =  (?)";
            Connection connection = JDBC.connection;
            DBQuery.setPreparedStatement(connection,sqlStatement);
            PreparedStatement preparedStatement = DBQuery.getPreparedStatement();
            preparedStatement.setInt(1,id);
            preparedStatement.execute();
            ResultSet rs = preparedStatement.getResultSet();

            while (rs.next()){
                Division division = new Division(rs.getInt("Division_ID"),rs.getString("Division"),rs.getInt("Country_ID"));
                return  division;
            }
        } catch (SQLException e){
            e.printStackTrace();
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
