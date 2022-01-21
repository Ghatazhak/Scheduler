package main;

import data_access.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.User;
import view.FXMLLoaderInterface;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Objects;

public class Main extends Application {
    public static User currentUser;
    FXMLLoaderInterface loaderLambda = s -> {
        Parent root = FXMLLoader.load((Objects.requireNonNull(getClass().getResource(s))));
        return root;
    };


    public static void main(String[] args) throws SQLException {
        Calendar c = Calendar.getInstance();
        LocalDateTime ldt = LocalDateTime.now();
        LocalDateTime ldt2 = LocalDateTime.of(2022,1,15,00,00);
        System.out.println(ldt.getDayOfYear()/7);
        System.out.println(ldt2.getDayOfYear()/7);
        JDBC.openConnection();
        launch(args);
        JDBC.closeConnection();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = loaderLambda.getRoot("/view/loginView.fxml");
        //Parent root = FXMLLoader.load((Objects.requireNonNull(getClass().getResource("/view/loginView.fxml"))));
        stage.setTitle("Scheduler v1");
        stage.setScene(new Scene(root,500,300));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initStyle(StageStyle.DECORATED);
        stage.show();
    }
}
