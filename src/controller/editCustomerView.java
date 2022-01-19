package controller;

import data_access.CountryMYSQL;
import data_access.DivisionMYSQL;
import javafx.collections.FXCollections;
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
import model.Country;
import model.Customer;
import model.Division;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class editCustomerView implements Initializable {
    ObservableList<Country> allCountries = FXCollections.observableArrayList();
    ObservableList<Division> allDivisions = FXCollections.observableArrayList();
    ObservableList<Division> filteredBySelectedCountryDivisions = FXCollections.observableArrayList();


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
        Customer customer = customerView.tempCustomer;
        customerIdTextField.setText(String.valueOf(customer.getCustomerId()));
        nameTextField.setText(customer.getCustomerName());
        addressTextField.setText(customer.getAddress());
        postalCodeTextField.setText(customer.getPostalCode());
        phoneNumberTextField.setText(String.valueOf(customer.getPhone()));


        ;
        allCountries = CountryMYSQL.findAll();
        countryCB.setItems(allCountries);

        allDivisions = DivisionMYSQL.findAll();
        divisionCB.setItems(allDivisions);
        divisionCB.setValue(DivisionMYSQL.findById(customer.getDivisionId()));


        for(Country c :allCountries ){
            if(c.getCountryId() == divisionCB.getValue().getCountryId()){
                countryCB.setValue(c);
            }
        }

        divisionCB.setDisable(false);



    }

    public void returnToCustomerManagementView() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/customerView.fxml")));
        Stage stage = (Stage) customerIdTextField.getScene().getWindow();
        Scene scene = new Scene(root, 540, 400);
        stage.setTitle("Customer Management");
        stage.setScene(scene);
        stage.show();
    }

    /** This is the event handler for save button on add customer. */
    public void saveButtonClicked(ActionEvent actionEvent) throws IOException {
        returnToCustomerManagementView();
    }
    /** This is the even handler for cancel button on add customer. */
    public void CancelButtonClicked(ActionEvent actionEvent) throws IOException {
        returnToCustomerManagementView();
    }

    public void countryCBClicked(ActionEvent actionEvent) {
        Country country =  countryCB.getSelectionModel().getSelectedItem();
       // allDivisions = DivisionMYSQL.findAll();
        filteredBySelectedCountryDivisions.clear();

        for(Division d: allDivisions){
            if(d.getCountryId() == country.getCountryId())
                filteredBySelectedCountryDivisions.add(d);
        }
        divisionCB.setItems(filteredBySelectedCountryDivisions);
        divisionCB.setDisable(false);
    }
}
