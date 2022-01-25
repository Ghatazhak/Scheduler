package controller;

import data_access.HumanEntitiesReportMYSQL;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import view.FXMLLoaderInterface;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


/**
 * The controller for human entities view.
 */
public class humanentitiesView implements Initializable {
    ObservableList<String> allEntityNames = FXCollections.observableArrayList();
    public TableView<String> humanEntitiesTableView;
    public TableColumn<String, String> NameCol;

    FXMLLoaderInterface loaderLambda = s -> FXMLLoader.load((Objects.requireNonNull(getClass().getResource(s))));

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        allEntityNames.addAll(HumanEntitiesReportMYSQL.getUsers());
        allEntityNames.addAll(HumanEntitiesReportMYSQL.getCustomers());
        allEntityNames.addAll(HumanEntitiesReportMYSQL.getContacts());
        humanEntitiesTableView.setItems(allEntityNames);
        NameCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
    }
    /** Event handler for returning to appointment view. (loaderLambda) A Lambda that keeps the compiler from complaining about duplicate code. It Loads the fxml file into root.*/
    public void returnButtonClicked(ActionEvent actionEvent)  {
        Parent root = null;
        try {
            root = loaderLambda.getRoot("/view/appointmentView.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) humanEntitiesTableView.getScene().getWindow();
        stage.close();
    }
}
