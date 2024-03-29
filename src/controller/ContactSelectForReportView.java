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
import model.Contact;
import view.FXMLLoaderInterface;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * The controller for contact select for report view.
 */
public class ContactSelectForReportView implements Initializable {
    public static Contact tempContact;
    private ObservableList<Contact> allContacts = FXCollections.observableArrayList();
    public ComboBox<Contact> contactCB;
    FXMLLoaderInterface loaderLambda = s -> FXMLLoader.load((Objects.requireNonNull(getClass().getResource(s))));

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        allContacts = ContactMYSQL.findAll();
        contactCB.setItems(allContacts);
    }
    /** An event handler that creates the report view. (loaderLambda) A Lambda that keeps the compiler from complaining about duplicate code. It Loads the fxml file into root.*/
    public void generateButtonClicked(ActionEvent actionEvent)  {
        tempContact = contactCB.getSelectionModel().getSelectedItem();
        Parent root = null;
        try {
            root = loaderLambda.getRoot("/view/ContactReportView.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage stage = (Stage) contactCB.getScene().getWindow();
        Scene scene = new Scene(root, 782, 410);
        stage.setTitle("Scheduler v1.0");
        stage.setScene(scene);
        stage.show();
    }
    /** An event handler that returns the user to appointment view. (loaderLambda) A Lambda that keeps the compiler from complaining about duplicate code. It Loads the fxml file into root.*/
    public void cancelButtonClicked(ActionEvent actionEvent)  {
        Parent root = null;
        try {
            root = loaderLambda.getRoot("/view/appointmentView.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
//
        Stage stage = (Stage) contactCB.getScene().getWindow();
        stage.close();


    }
}
