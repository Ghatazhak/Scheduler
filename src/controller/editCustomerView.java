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
 * The controller for edit customer view.
 */
public class editCustomerView implements Initializable {
    ObservableList<Country> allCountries = FXCollections.observableArrayList();
    ObservableList<Division> allDivisions = FXCollections.observableArrayList();
    ObservableList<Division> filteredBySelectedCountryDivisions = FXCollections.observableArrayList();
    Country country;

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

        Customer customer = customerView.tempCustomer;
        customerView.tempCustomer = null;
        customerIdTextField.setText(String.valueOf(customer.getCustomerId()));
        nameTextField.setText(customer.getCustomerName());
        addressTextField.setText(customer.getAddress());
        postalCodeTextField.setText(customer.getPostalCode());
        phoneNumberTextField.setText(String.valueOf(customer.getPhone()));

        allCountries = CountryMYSQL.findAll();
        countryCB.setItems(allCountries);

        allDivisions = DivisionMYSQL.findAll();
        divisionCB.setItems(allDivisions);
        for(Division d: allDivisions){
          if(Objects.equals(d.getDivision(), customer.getDivision()))
              divisionCB.setValue(DivisionMYSQL.findById(DivisionMYSQL.findByName(d.getDivision())));
        }

        for(Country c :allCountries ){
            if(c.getCountryId() == divisionCB.getValue().getCountryId()){

                countryCB.setValue(c);
                divisionCB.setDisable(false);
            }
        }

    }
    /** An event handler to return to customer management. (loaderLambda) A Lambda that keeps the compiler from complaining about duplicate code. It Loads the fxml file into root.*/
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

    /** This is the event handler for save button on add customer. It validates input and calls returnToCustomerManagement */
    public void saveButtonClicked(ActionEvent actionEvent)  {
        if(nameTextField.getText().isEmpty() || addressTextField.getText().isEmpty() || postalCodeTextField.getText().isEmpty() || phoneNumberTextField.getText().isEmpty() || countryCB.getValue() == null || divisionCB.getValue() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Missing Data!");
            alert.setContentText("Missing Data in one or more fields");
            alert.setGraphic(null);
            Stage stage = (Stage) customerIdTextField.getScene().getWindow();
            //alert.initOwner(stage);
            alert.showAndWait();
        } else {
            Customer customer = new Customer(Integer.parseInt(customerIdTextField.getText()),nameTextField.getText(), addressTextField.getText(),phoneNumberTextField.getText(), postalCodeTextField.getText(), divisionCB.getValue().getDivision());
            CustomerMSQL.update(customer);
            returnToCustomerManagementView();
        }
    }

    /** This is the even handler for cancel button on add customer. It returns you to customer management. */
    public void CancelButtonClicked(ActionEvent actionEvent) throws IOException {
        returnToCustomerManagementView();
    }
    /** An event handler for the country combo box. It filters the first level divisions when clicked. */
    public void countryCBClicked(ActionEvent actionEvent) {

        divisionCB.setValue(null);
        country =  countryCB.getValue();
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
