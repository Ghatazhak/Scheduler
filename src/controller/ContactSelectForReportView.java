package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import main.Main;
import model.Contact;
import view.FXMLLoaderInterface;

import java.io.IOException;
import java.util.Objects;

public class ContactSelectForReportView {
    public ComboBox<Contact> contactCB;

    FXMLLoaderInterface loaderLambda = s -> {
        Parent root = FXMLLoader.load((Objects.requireNonNull(getClass().getResource(s))));
        return root;
    };

    public void generateButtonClicked(ActionEvent actionEvent) throws IOException {
        Parent root = loaderLambda.getRoot("/view/ContactReportView.fxml");
        Stage stage = (Stage) contactCB.getScene().getWindow();
        Scene scene = new Scene(root, 782, 410);
        stage.setTitle("Contact Report");
        stage.setScene(scene);
        stage.show();
    }

    public void cancelButtonClicked(ActionEvent actionEvent) throws IOException {
        Parent root = loaderLambda.getRoot("/view/appointmentView.fxml");
        Stage stage = (Stage) contactCB.getScene().getWindow();
        Scene scene = new Scene(root, 1020, 475);
        stage.setTitle("Scheduler v1.0 Appointments:   " + Main.currentUser.getUsername());
        stage.setScene(scene);
        stage.show();
    }
}
