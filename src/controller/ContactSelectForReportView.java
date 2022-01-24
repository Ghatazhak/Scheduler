package controller;

import data_access.ContactMYSQL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import main.Main;
import model.Contact;
import view.FXMLLoaderInterface;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ContactSelectForReportView implements Initializable {
    public static Contact tempContact;
    private ObservableList<Contact> allContacts = FXCollections.observableArrayList();
    public ComboBox<Contact> contactCB;


    FXMLLoaderInterface loaderLambda = s -> {
        Parent root = FXMLLoader.load((Objects.requireNonNull(getClass().getResource(s))));
        return root;
    };

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        allContacts = ContactMYSQL.findAll();
        contactCB.setItems(allContacts);
    }

    public void generateButtonClicked(ActionEvent actionEvent) throws IOException {
        tempContact = contactCB.getSelectionModel().getSelectedItem();

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
