package controller;

import data_access.AppointmentMSQL;
import data_access.ContactMYSQL;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.Main;
import model.Appointment;
import model.Contact;
import model.Customer;
import model.User;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.ResourceBundle;

public class editAppointmentView implements Initializable {


    ObservableList<String> startHours = FXCollections.observableArrayList();
    ObservableList<String> startMinutes = FXCollections.observableArrayList();
    ObservableList<String> endHours = FXCollections.observableArrayList();
    ObservableList<String> endMinutes = FXCollections.observableArrayList();
    ObservableList<Contact> allContacts = FXCollections.observableArrayList();
    ObservableList<User> allUsers = FXCollections.observableArrayList();
    ObservableList<Customer> allCustomers = FXCollections.observableArrayList();


    @FXML
    public TextField appointmentIdTextField;
    @FXML
    public TextField titleTextField;
    @FXML
    public TextField descriptionTextField;
    @FXML
    public TextField locationTextField;
    @FXML
    public ComboBox<Contact> contactCB;
    @FXML
    public TextField typeTextField;
    @FXML
    public ComboBox<Customer> customerIdCB;
    @FXML
    public TextField userIdTextField;
    @FXML
    public DatePicker startDateDP;
    @FXML
    public DatePicker endDateDP;
    @FXML
    public ComboBox<String> startHourCB;
    @FXML
    public ComboBox<String> endMinuteCB;
    @FXML
    public ComboBox<String> endHourCB;
    @FXML
    public ComboBox<String> startMinuteCB;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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




        Appointment appointment = appointmentView.tempAppointment;
        appointmentIdTextField.setText(String.valueOf(appointment.getAppointmentId()));
        titleTextField.setText(appointment.getTitle());
        descriptionTextField.setText(appointment.getDescription());
        locationTextField.setText(appointment.getLocation());

        allContacts = ContactMYSQL.findAll();
        contactCB.setItems(allContacts);
        contactCB.setValue(ContactMYSQL.findById(appointment.getContactId()));

        typeTextField.setText(appointment.getType());
        startDateDP.setValue(appointment.getStartDateTime().toLocalDate());
        endDateDP.setValue(appointment.getStartDateTime().toLocalDate());
        startHourCB.setValue(String.valueOf(appointment.getStartDateTime().getHour()));
        endHourCB.setValue(String.valueOf(appointment.getEndDateTime().getHour()));
        startMinuteCB.setValue(String.valueOf(appointment.getStartDateTime().getHour()));
        endMinuteCB.setValue(String.valueOf(appointment.getEndDateTime().getHour()));
        allCustomers = CustomerMSQL.findAll();
        customerIdCB.setItems(allCustomers);
        customerIdCB.setValue(CustomerMSQL.findById(appointment.getCustomerId()));

        userIdTextField.setText(String.valueOf(Main.currentUser.getUserId()) + " (" + Main.currentUser.getUsername() + ")");
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
        if(titleTextField.getText().isEmpty() || descriptionTextField.getText().isEmpty() || locationTextField.getText().isEmpty() || contactCB.getValue() == null || typeTextField.getText().isEmpty()|| startHourCB.getValue() == null || startMinuteCB.getValue() == null || endHourCB.getValue() == null || endMinuteCB.getValue() == null || startDateDP.getValue() == null || endDateDP.getValue() == null || contactCB.getValue() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Missing Data!");
            alert.setContentText("Missing Data in one or more fields");
            alert.setGraphic(null);
            alert.showAndWait();
            return;
        }


        //allUsers.addAll(UserMYSQL.findAll());
//        if (UserMYSQL.findByUsername(userIdTextField.getText()) == null) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Error");
//            alert.setHeaderText("Invalid");
//            alert.setContentText("No Such User ID!");
//            alert.setGraphic(null);
//            alert.showAndWait();
//            return;
//        }

        LocalDate startDateDpValue = startDateDP.getValue();
        String startHour = startHourCB.getValue();
        String startMinute = startMinuteCB.getValue();
        LocalDateTime startLocalDateTime = LocalDateTime.of(startDateDpValue.getYear(), startDateDpValue.getMonthValue(), startDateDpValue.getDayOfMonth(), Integer.parseInt(startHour), Integer.parseInt(startMinute));
        ZonedDateTime startLocalZonedDateTime = ZonedDateTime.of(startLocalDateTime, ZoneId.systemDefault());
        //ZonedDateTime utcStartZonedDateTime = startLocalZonedDateTime.withZoneSameInstant(ZoneOffset.UTC);

        LocalDate endDateDpValue = endDateDP.getValue();
        String endHour = endHourCB.getValue();
        String endMinute = endMinuteCB.getValue();
        LocalDateTime endLocalDateTime = LocalDateTime.of(endDateDpValue.getYear(), endDateDpValue.getMonthValue(), endDateDpValue.getDayOfMonth(), Integer.parseInt(endHour), Integer.parseInt(endMinute));
        ZonedDateTime endLocalZonedDateTime = ZonedDateTime.of(endLocalDateTime, ZoneId.systemDefault());
        // ZonedDateTime utcEndZonedDateTime = endLocalZonedDateTime.withZoneSameInstant(ZoneOffset.UTC);

        if(startLocalDateTime.isAfter(endLocalDateTime) || startLocalDateTime.isEqual(endLocalDateTime)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Date Time Mismatch");
            alert.setContentText("The start time cannot be greater than or equal to the end date/time.");
            alert.setGraphic(null);
            alert.showAndWait();
            return;
        }
        Appointment newAppointment = new Appointment(Integer.parseInt(appointmentIdTextField.getText()), titleTextField.getText(), descriptionTextField.getText(), locationTextField.getText(), typeTextField.getText(), startLocalDateTime, endLocalDateTime, customerIdCB.getValue().getCustomerId(), Main.currentUser.getUserId(), contactCB.getValue().getContactId());
        boolean updateResult = AppointmentMSQL.update(newAppointment);
        if(updateResult){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Update");
            alert.setContentText("You updated the appointment successfully.");
            alert.setGraphic(null);
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Update");
            alert.setContentText("Your update failed.");
            alert.setGraphic(null);
            alert.showAndWait();
        }



        returnToHomeView();
    }
/** This is the event handler for the cancel button. */
    public void cancelButtonClicked(ActionEvent actionEvent) throws IOException {
        returnToHomeView();
    }
}
