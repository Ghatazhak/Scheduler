package main;

import data_access.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.*;
import java.util.Objects;

public class Main extends Application {
    public static void main(String[] args) throws SQLException {
        JDBC.openConnection();
        launch(args);
        JDBC.closeConnection();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load((Objects.requireNonNull(getClass().getResource("/view/loginView.fxml"))));
        stage.setTitle("Scheduler v1");
        stage.setScene(new Scene(root,500,300));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initStyle(StageStyle.DECORATED);
        stage.show();
    }
}
