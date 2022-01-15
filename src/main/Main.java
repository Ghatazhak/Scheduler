package main;

import dao.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

public class Main extends Application {
    public static void main(String[] args) {
        ResourceBundle rb = ResourceBundle.getBundle("language_files/rb",Locale.getDefault());
        if(Locale.getDefault().getLanguage().equals(rb)){
            System.out.println(rb.getString("login" + " "+ rb.getString("exit")));
        }
        JDBC.openConnection();
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
