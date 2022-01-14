package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class addCustomerView {
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
    public ComboBox countryComboBox;
    @FXML
    public ComboBox stateProvinceComboBox;

  /** This is the event handler for country combo box */
    public void countryComboBoxClicked(ActionEvent actionEvent) {
    }
/**  This is the event handler for State/Province combo box */
    public void stateProvinceComboBoxClicked(ActionEvent actionEvent) {
    }
/** This is the event handler for save button on add customer. */
    public void saveButtonClicked(ActionEvent actionEvent) {
    }
/** This is the even handler for cancel button on add customer. */
    public void CancelButtonClicked(ActionEvent actionEvent) {
    }
}
