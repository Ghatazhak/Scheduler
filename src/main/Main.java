package main;

import Util.DBQuery;
import Util.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

public class Main extends Application {
    public static void main(String[] args) throws SQLException {
        JDBC.openConnection();
        //DBQuery.setStatement(JDBC.connection);
        //Statement statement = DBQuery.getStatement();

        launch(args);
        JDBC.closeConnection();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load((Objects.requireNonNull(getClass().getResource("/view/loginView.fxml"))));
        stage.setTitle("Scheduler v1");
        stage.setScene(new Scene(root,500,300));
        stage.show();
    }
}
