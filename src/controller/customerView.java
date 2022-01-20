package controller;

import data_access.CustomerMSQL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Customer;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class customerView implements Initializable {

    ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
    public static Customer tempCustomer;

    @FXML
    public TableView<Customer> allCustomersTableView;
    @FXML
    public TableColumn<Customer, Integer> iDCol;
    @FXML
    public TableColumn<Customer, String> nameCol;
    @FXML
    public TableColumn<Customer, String> addressCol;
    @FXML
    public TableColumn<Customer, String> phoneCol;
    @FXML
    public TableColumn<Customer, String> postalCodeCol;
    @FXML
    public TableColumn divisionCol;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        allCustomers = CustomerMSQL.findAll();
        allCustomersTableView.setItems(allCustomers);

        iDCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        postalCodeCol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        divisionCol.setCellValueFactory(new PropertyValueFactory<>("divisionId"));

    }
    /** This event handler is for the add button. */
    public void addButtonClicked(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/addCustomerView.fxml")));
        Stage stage = (Stage) allCustomersTableView.getScene().getWindow();
        Scene scene = new Scene(root, 370, 390);
        stage.setTitle("Add New Customer");
        stage.setScene(scene);
        stage.show();
    }
    /** This event handler is for the edit button. */
    public void editButtonClicked(ActionEvent actionEvent) throws IOException {
        tempCustomer = allCustomersTableView.getSelectionModel().getSelectedItem();

        if (tempCustomer == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No Selection");
            alert.setContentText("You must have a customer selected to edit.");
            alert.setGraphic(null);
            alert.showAndWait();
            return;
        }

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/editCustomerView.fxml")));
        // Couldn't get stage from menu item. Had to pick something else on the screen. I picked the table view.
        Stage stage = (Stage) allCustomersTableView.getScene().getWindow();
        Scene scene = new Scene(root, 370, 390);
        stage.setTitle("Edit Customer");
        stage.setScene(scene);
        stage.show();
    }
    /** This event handler is for the delete button. */
    public void deleteButtonClicked(ActionEvent actionEvent) {

        tempCustomer = allCustomersTableView.getSelectionModel().getSelectedItem();

        if (tempCustomer == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No Selection");
            alert.setContentText("You must have a customer selected to delete.");
            alert.setGraphic(null);
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure you want to delete this customer? This will also delete any associated appointments.");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            CustomerMSQL.delete(tempCustomer);
            allCustomers = CustomerMSQL.findAll();
            allCustomersTableView.setItems(allCustomers);
        }


    }
    /** This event handler is for the cancel button. */
    public void cancelButtonClicked(ActionEvent actionEvent) throws IOException {
//        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/appointmentView.fxml")));
//        Stage stage = (Stage) allCustomersTableView.getScene().getWindow();
//        Scene scene = new Scene(root, 1020, 475);
//        stage.setTitle("Scheduler v1.0");
//        stage.setScene(scene);
//        stage.show();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/appointmentView.fxml")));
        Stage stage = (Stage) allCustomersTableView.getScene().getWindow();
        stage.close();
    }
}
