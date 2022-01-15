package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class customerManagementHomeView implements Initializable {
    @FXML
    public TableView allCustomersTableView;
    @FXML
    public TableColumn iDCol;
    @FXML
    public TableColumn nameCol;
    @FXML
    public TableColumn addressCol;
    @FXML
    public TableColumn postalCodeCol;
    @FXML
    public TableColumn firstLevelDivisionCol;
    @FXML
    public TableColumn createdDateCol;
    @FXML
    public TableColumn createdByCol;
    @FXML
    public TableColumn phoneNumberCol;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    /** This event handler is for the add button. */
    public void addButtonClicked(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/addCustomerView.fxml")));
        // Couldn't get stage from menu item. Had to pick something else on the screen. I picked the table view.
        Stage stage = (Stage) allCustomersTableView.getScene().getWindow();
        Scene scene = new Scene(root, 370, 390);
        stage.setTitle("Scheduler v1.0 Add New Customer");
        stage.setScene(scene);
        stage.show();
    }
    /** This event handler is for the edit button. */
    public void editButtonClicked(ActionEvent actionEvent) {
    }
    /** This event handler is for the delete button. */
    public void deleteButtonClicked(ActionEvent actionEvent) {
    }
    /** This event handler is for the cancel button. */
    public void cancelButtonClicked(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/homeView.fxml")));
        Stage stage = (Stage) allCustomersTableView.getScene().getWindow();
        Scene scene = new Scene(root, 1020, 475);
        stage.setTitle("Scheduler v1.0");
        stage.setScene(scene);
        stage.show();
    }
}
