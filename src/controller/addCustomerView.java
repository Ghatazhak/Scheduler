package controller;

import data_access.CountryMYSQL;
import data_access.DivisionMYSQL;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class addCustomerView implements Initializable {
    @FXML
    public TextField customerIdTextField;
    @FXML
    public TextField nameTextField;
    @FXML
    public TextField addressTextField;
    @FXML
    public TextField postalCodeTextField;
    @FXML
    public TextField phoneNumberTextField;
    @FXML
    public ComboBox<String> countryComboBox;
    @FXML
    public ComboBox<String> stateProvinceComboBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            ObservableList allCountries = CountryMYSQL.findAll();
            countryComboBox.setItems(allCountries);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            ObservableList allDivisions = DivisionMYSQL.findAll();
            stateProvinceComboBox.setItems(allDivisions);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void returnToCustomerManagementView() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/customerView.fxml")));
        Stage stage = (Stage) customerIdTextField.getScene().getWindow();
        Scene scene = new Scene(root, 540, 400);
        stage.setTitle("Customer Management");
        stage.setScene(scene);
        stage.show();
    }

  /** This is the event handler for country combo box */
    public void countryComboBoxClicked(ActionEvent actionEvent) {
    }
    /**  This is the event handler for State/Province combo box */
    public void stateProvinceComboBoxClicked(ActionEvent actionEvent) {
    }
    /** This is the event handler for save button on add customer. */
    public void saveButtonClicked(ActionEvent actionEvent) throws IOException {
        returnToCustomerManagementView();
    }
    /** This is the even handler for cancel button on add customer. */
    public void CancelButtonClicked(ActionEvent actionEvent) throws IOException {
        returnToCustomerManagementView();
    }
}
