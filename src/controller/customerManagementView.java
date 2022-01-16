package controller;

import dao.CustomerDAOImpl;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Customer;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class customerManagementView implements Initializable {
    @FXML
    public TableView<Customer> allCustomersTableView;
    @FXML
    public TableColumn<Customer, Integer> iDCol;
    @FXML
    public TableColumn<Customer, String> nameCol;
    @FXML
    public TableColumn<Customer, String> addressCol;
    @FXML
    public TableColumn<Customer, String> postalCodeCol;
    @FXML
    public TableColumn<Customer, String> firstLevelDivisionCol;
    @FXML
    public TableColumn<Customer, String> phoneNumberCol;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try{
            CustomerDAOImpl customerDAO = new CustomerDAOImpl();
            ObservableList<Customer> list = customerDAO.findAll();
            allCustomersTableView.setItems(list);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }


        iDCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        postalCodeCol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        firstLevelDivisionCol.setCellValueFactory(new PropertyValueFactory<>("divisionId"));
        phoneNumberCol.setCellValueFactory(new PropertyValueFactory<>("phone"));


    }
    /** This event handler is for the add button. */
    public void addButtonClicked(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/addCustomerView.fxml")));
        Stage stage = (Stage) allCustomersTableView.getScene().getWindow();
        Scene scene = new Scene(root, 370, 390);
        stage.setAlwaysOnTop(true);
        stage.setTitle("Add New Customer");
        stage.setScene(scene);
        stage.show();
    }
    /** This event handler is for the edit button. */
    public void editButtonClicked(ActionEvent actionEvent) throws IOException {
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
