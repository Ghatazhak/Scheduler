package controller;

import data_access.AppointmentMSQL;
import data_access.ContactMYSQL;
import data_access.CustomerMSQL;
import data_access.UserMYSQL;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Appointment;
import model.Contact;
import model.Customer;
import model.User;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.*;
import java.util.Objects;
import java.util.ResourceBundle;

public class addAppointmentView implements Initializable {

    ObservableList<String> startHours = FXCollections.observableArrayList();
    ObservableList<String> startMinutes = FXCollections.observableArrayList();
    ObservableList<String> endHours = FXCollections.observableArrayList();
    ObservableList<String> endMinutes = FXCollections.observableArrayList();
    ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
    ObservableList<String> allTypes = FXCollections.observableArrayList();
    ObservableList<Contact> allContacts = FXCollections.observableArrayList();


    @FXML
    public TextField appointmentIdTextField;
    @FXML
    public TextField titleTextField;
    @FXML
    public TextField descriptionTextField;
    @FXML
    public TextField locationTextField;
    @FXML
    public ComboBox<Contact> contactComboBox;
    @FXML
    public ComboBox<String> typeComboBox;
    @FXML
    public ComboBox<Customer> customerIdCB;
    @FXML
    public TextField userIdTextField;
    @FXML
    public ComboBox<String> startHourCB;
    @FXML
    public ComboBox<String> startMinuteCB;
    @FXML
    public ComboBox<String> endHourCB;
    @FXML
    public ComboBox<String> endMinuteCB;
    @FXML
    public DatePicker endDateDp;
    @FXML
    public DatePicker startDateDp;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        allTypes.addAll("InPerson", "Remote");
        typeComboBox.setItems(allTypes);

        startHours.addAll("00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11",
                "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23");
        startMinutes.addAll("00", "15", "30", "45");
        startHourCB.setItems(startHours);
        startMinuteCB.setItems(startMinutes);

        endHours.addAll("00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11",
                "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23");
        endMinutes.addAll("00", "15", "30", "45");
        endHourCB.setItems(endHours);
        endMinuteCB.setItems(endMinutes);

        allCustomers = CustomerMSQL.findAll();

        customerIdCB.setItems(allCustomers);
//        for (Customer c: allCustomers){
//            allCustomerId.add(c.getCustomerId());
//        }
//        customerIdCB.setItems(allCustomerId);

        contactComboBox.setItems(allContacts = ContactMYSQL.findAll());
    }

   public void returnToHomeView() throws IOException {
       Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/appointmentView.fxml")));
       // Couldn't get stage from menu item. Had to pick something else on the screen. I picked the table view.
       Stage stage = (Stage) appointmentIdTextField.getScene().getWindow();
       Scene scene = new Scene(root, 1020, 475);
       stage.setTitle("Scheduler v1.0");
       stage.setScene(scene);
       stage.show();
   }

/** This is the event handler for the save button. */
    public void saveButtonClicked(ActionEvent actionEvent) throws IOException, SQLException {

        if(titleTextField.getText().isEmpty() || descriptionTextField.getText().isEmpty() || locationTextField.getText().isEmpty() || contactComboBox.getValue() == null || typeComboBox.getValue() == null || startHourCB.getValue() == null || startMinuteCB.getValue() == null || endHourCB.getValue() == null || endMinuteCB.getValue() == null || startDateDp.getValue() == null || endDateDp.getValue() == null || userIdTextField.getText().isEmpty() || contactComboBox.getValue() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Missing Data!");
            alert.setContentText("Missing Data in one or more fields");
            alert.setGraphic(null);
            alert.showAndWait();
        } else {
            ObservableList<User> allUsers = FXCollections.observableArrayList();
            allUsers.addAll(UserMYSQL.findAll());


            if (UserMYSQL.userExist(Integer.parseInt(userIdTextField.getText()))) {

                LocalDate startDateDpValue = startDateDp.getValue();
                String startHour = startHourCB.getValue();
                String startMinute = startMinuteCB.getValue();
                LocalDateTime startLocalDateTime = LocalDateTime.of(startDateDpValue.getYear(), startDateDpValue.getMonthValue(), startDateDpValue.getDayOfMonth(), Integer.parseInt(startHour), Integer.parseInt(startMinute));
                ZonedDateTime startLocalZonedDateTime = ZonedDateTime.of(startLocalDateTime, ZoneId.systemDefault());
                ZonedDateTime utcStartZonedDateTime = startLocalZonedDateTime.withZoneSameInstant(ZoneOffset.UTC);

                LocalDate endDateDpValue = startDateDp.getValue();
                String endHour = startHourCB.getValue();
                String endMinute = startMinuteCB.getValue();
                LocalDateTime endLocalDateTime = LocalDateTime.of(endDateDpValue.getYear(), endDateDpValue.getMonthValue(), endDateDpValue.getDayOfMonth(), Integer.parseInt(endHour), Integer.parseInt(endMinute));
                ZonedDateTime endLocalZonedDateTime = ZonedDateTime.of(endLocalDateTime, ZoneId.systemDefault());
                ZonedDateTime utcEndZonedDateTime = endLocalZonedDateTime.withZoneSameInstant(ZoneOffset.UTC);

                Appointment newAppointment = new Appointment(1, titleTextField.getText(), descriptionTextField.getText(), locationTextField.getText(), typeComboBox.getValue(), startLocalDateTime, endLocalDateTime, customerIdCB.getValue().getCustomerId(), Integer.parseInt(userIdTextField.getText()), contactComboBox.getValue().getContactId());
                AppointmentMSQL.create(newAppointment);
                returnToHomeView();

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid");
                alert.setContentText("No Such User ID!");
                alert.setGraphic(null);
                alert.showAndWait();
            }
        }






    }
/** This is the event handler for the cancel button. */
    public void cancelButtonClicked(ActionEvent actionEvent) throws IOException {
        returnToHomeView();
    }
}
