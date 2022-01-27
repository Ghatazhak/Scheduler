package controller;

import data_access.CountryMYSQL;
import data_access.CustomerMSQL;
import data_access.DivisionMYSQL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Country;
import model.Customer;
import model.Division;
import view.FXMLLoaderInterface;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * The controller for add customer view.
 */
public class addCustomerView implements Initializable {
    ObservableList<Country> allCountries = FXCollections.observableArrayList();
    ObservableList<Division> allDivisions = FXCollections.observableArrayList();
    ObservableList<Division> filteredBySelectedCountryDivisions = FXCollections.observableArrayList();
    FXMLLoaderInterface loaderLambda = s -> FXMLLoader.load((Objects.requireNonNull(getClass().getResource(s))));

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
    public ComboBox<Country> countryCB;
    @FXML
    public ComboBox<Division> divisionCB;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            allCountries = CountryMYSQL.findAll();
            countryCB.setItems(allCountries);
    }
    /** A method that can return you to the Customer management view. Lambda #2 A Lambda that keeps the compiler from complaining about duplicate code. It Loads the fxml file into root. */
    public void returnToCustomerManagementView()  {
        Parent root = null;
        try {
            root = loaderLambda.getRoot("/view/customerView.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) customerIdTextField.getScene().getWindow();
        Scene scene = new Scene(root, 540, 400);
        stage.setTitle("Customer Management");
        stage.setScene(scene);
        stage.show();
    }

    /** This is the event handler for save button on add customer. */
    public void saveButtonClicked(ActionEvent actionEvent) throws IOException {

        if(nameTextField.getText().isEmpty() || addressTextField.getText().isEmpty() || postalCodeTextField.getText().isEmpty() || phoneNumberTextField.getText().isEmpty() || countryCB.getValue() == null || divisionCB.getValue() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Missing Data!");
            alert.setContentText("Missing Data in one or more fields");
            alert.setGraphic(null);
            alert.showAndWait();
        } else {
            Customer customer = new Customer(0,nameTextField.getText(), addressTextField.getText(),phoneNumberTextField.getText(), postalCodeTextField.getText(), divisionCB.getValue().getDivision());
            CustomerMSQL.create(customer);
            returnToCustomerManagementView();
        }
    }
    /** This is the even handler for cancel button on add customer. */
    public void CancelButtonClicked(ActionEvent actionEvent) throws IOException {
        returnToCustomerManagementView();
    }
    /** This is the event handler for when you select a country. It filters the first level divisions. */
    public void countryCBClicked(ActionEvent actionEvent) {

        Country country = countryCB.getSelectionModel().getSelectedItem();

        if(country == null){
            return;
        }

        allDivisions = DivisionMYSQL.findAll();
        filteredBySelectedCountryDivisions.clear();

                for(Division d: allDivisions){
                    if(d.getCountryId() == country.getCountryId())
                    filteredBySelectedCountryDivisions.add(d);
                }
                divisionCB.setItems(filteredBySelectedCountryDivisions);
                divisionCB.setDisable(false);
    }
}