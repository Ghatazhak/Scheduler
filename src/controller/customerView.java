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
import view.FXMLLoaderInterface;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * The controller for customer view.
 */
public class customerView implements Initializable {

    ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
    public static Customer tempCustomer;

    FXMLLoaderInterface loaderLambda = s -> FXMLLoader.load((Objects.requireNonNull(getClass().getResource(s))));

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
    public TableColumn<Customer, String> divisionCol;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        allCustomers = CustomerMSQL.findAll();
        allCustomersTableView.setItems(allCustomers);

        iDCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        postalCodeCol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        divisionCol.setCellValueFactory(new PropertyValueFactory<>("division"));

    }
    /** An event handler for adding customers. (loaderLambda) A Lambda that keeps the compiler from complaining about duplicate code. It Loads the fxml file into root.*/
    public void addButtonClicked(ActionEvent actionEvent) {
        Parent root = null;
        try {
            root = loaderLambda.getRoot("/view/addCustomerView.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) allCustomersTableView.getScene().getWindow();
        Scene scene = new Scene(root, 370, 390);
        stage.setTitle("Add New Customer");
        stage.setScene(scene);
        stage.show();
    }
    /** An event handler for editing customers. (loaderLambda) A Lambda that keeps the compiler from complaining about duplicate code. It Loads the fxml file into root.*/
    public void editButtonClicked(ActionEvent actionEvent)  {
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
        Parent root = null;
        try {
            root = loaderLambda.getRoot("/view/editCustomerView.fxml");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Cannot find edit customer view.");
        }
        Stage stage = (Stage) allCustomersTableView.getScene().getWindow();
        Scene scene = new Scene(root, 370, 390);
        stage.setTitle("Edit Customer");
        stage.setScene(scene);
        stage.show();
    }
    /** This event handler is for the delete button. It deletes the selected customer. */
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
            boolean isDeleted = CustomerMSQL.delete(tempCustomer);
            if(isDeleted){
                Alert alert2 = new Alert(Alert.AlertType.ERROR);
                alert2.setTitle("Information");
                alert2.setHeaderText("Success");
                alert2.setContentText("Customer " + tempCustomer.getCustomerName() + " and associated appointments where deleted.");
                alert2.setGraphic(null);
                alert2.showAndWait();
            }
            allCustomers = CustomerMSQL.findAll();
            allCustomersTableView.setItems(allCustomers);
        }
    }
    /** An event handler that returns the user to appointment view (loaderLambda) A Lambda that keeps the compiler from complaining about duplicate code. It Loads the fxml file into root.*/
    public void cancelButtonClicked(ActionEvent actionEvent)  {
        try {
            Parent root = loaderLambda.getRoot("/view/appointmentView.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) allCustomersTableView.getScene().getWindow();
        stage.close();
    }
}
