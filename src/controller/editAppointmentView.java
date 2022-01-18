package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Appointment;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class editAppointmentView implements Initializable {
    @FXML
    public TextField appointmentIdTextField;
    @FXML
    public TextField titleTextField;
    @FXML
    public TextField descriptionTextField;
    @FXML
    public TextField locationTextField;
    @FXML
    public ComboBox<String> contactComboBox;
    @FXML
    public ComboBox<String> typeComboBox;
    @FXML
    public TextField customerIdTextField;
    @FXML
    public TextField userIdTextField;
    @FXML
    public DatePicker startDateDP;
    @FXML
    public DatePicker endDateDP;
    @FXML
    public TextField startTimeTextField;
    @FXML
    public TextField endTimeTextField;
    public DatePicker startDateDp;
    public DatePicker endDateDp;
    public ComboBox startHourCB;
    public ComboBox endMinuteCB;
    public ComboBox endHourCB;
    public ComboBox startMinuteCB;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Appointment appointment = appointmentView.tempAppointment;
        appointmentIdTextField.setText(String.valueOf(appointment.getAppointmentId()));
        titleTextField.setText(appointment.getTitle());
        descriptionTextField.setText(appointment.getDescription());
        locationTextField.setText(appointment.getLocation());
        //contactComboBox
        //typeComboBox
        startDateDP.setValue(appointment.getStartDateTime().toLocalDate());
        endDateDP.setValue(appointment.getStartDateTime().toLocalDate());
        startTimeTextField.setText(appointment.getStartDateTime().toLocalTime().toString());
        endTimeTextField.setText(appointment.getStartDateTime().toLocalTime().toString());
        customerIdTextField.setText(String.valueOf(appointment.getCustomerId()));
        userIdTextField.setText(String.valueOf(appointment.getUserId()));
    }

    public void returnToHomeView() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/appoinmentView.fxml")));
        // Couldn't get stage from menu item. Had to pick something else on the screen. I picked the table view.
        Stage stage = (Stage) appointmentIdTextField.getScene().getWindow();
        Scene scene = new Scene(root, 1020, 475);
        stage.setTitle("Scheduler v1.0");
        stage.setScene(scene);
        stage.show();
    }
/** This is the event handler for the save button. */
    public void saveButtonClicked(ActionEvent actionEvent) throws IOException {
        returnToHomeView();
    }
/** This is the event handler for the cancel button. */
    public void cancelButtonClicked(ActionEvent actionEvent) throws IOException {
        returnToHomeView();
    }
}
