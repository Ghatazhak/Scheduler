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
import view.FXMLLoaderInterface;

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
    ObservableList<Contact> allContacts = FXCollections.observableArrayList();
    boolean isAppointmentConflict = false;
    LocalTime openingBusinessTime = LocalTime.of(8,0,0);
    LocalTime closingBusinessTime = LocalTime.of(22,0,0);

    FXMLLoaderInterface loaderLambda = s -> {
        Parent root = FXMLLoader.load((Objects.requireNonNull(getClass().getResource(s))));
        return root;
    };

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
    public TextField typeTextField;
    @FXML
    public ComboBox<Customer> customerIdCB;
    @FXML
    public TextField userIdTextField;
    @FXML
    public DatePicker datePicker;
    @FXML
    public ComboBox<String> startHourCB;
    @FXML
    public ComboBox<String> startMinuteCB;
    @FXML
    public ComboBox<String> endHourCB;
    @FXML
    public ComboBox<String> endMinuteCB;

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
        allCustomers = CustomerMSQL.findAll();
        customerIdCB.setItems(allCustomers);
        contactComboBox.setItems(allContacts = ContactMYSQL.findAll());
        userIdTextField.setText(String.valueOf(Main.currentUser.getUserId()) + " (" + Main.currentUser.getUsername() + ")");
    }

   public void returnToHomeView() throws IOException {
       Parent root = loaderLambda.getRoot("/view/appointmentView.fxml");
       Stage stage = (Stage) appointmentIdTextField.getScene().getWindow();
       Scene scene = new Scene(root, 1020, 475);
       stage.setTitle("Scheduler v1.0");
       stage.setScene(scene);
       stage.show();
   }

/** This is the event handler for the save button. */
    public void saveButtonClicked(ActionEvent actionEvent) throws IOException, SQLException {

        if(titleTextField.getText().isEmpty() || descriptionTextField.getText().isEmpty() || locationTextField.getText().isEmpty() || contactComboBox.getValue() == null || typeTextField.getText().isEmpty()|| startHourCB.getValue() == null || startMinuteCB.getValue() == null || endHourCB.getValue() == null || endMinuteCB.getValue() == null || datePicker.getValue() == null || contactComboBox.getValue() == null || customerIdCB.getValue()== null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Missing Data!");
            alert.setContentText("Missing Data in one or more fields");
            alert.setGraphic(null);
            alert.showAndWait();
            return;
        }
                LocalDate datePickerValue = datePicker.getValue();
                String startHour = startHourCB.getValue();
                String startMinute = startMinuteCB.getValue();
                LocalDateTime startLocalDateTime = LocalDateTime.of(datePickerValue.getYear(), datePickerValue.getMonthValue(), datePickerValue.getDayOfMonth(), Integer.parseInt(startHour), Integer.parseInt(startMinute));

                String endHour = endHourCB.getValue();
                String endMinute = endMinuteCB.getValue();
                LocalDateTime endLocalDateTime = LocalDateTime.of(datePickerValue.getYear(), datePickerValue.getMonthValue(), datePickerValue.getDayOfMonth(), Integer.parseInt(endHour), Integer.parseInt(endMinute));

                ZonedDateTime startzdt = startLocalDateTime.atZone(ZoneId.systemDefault());
                ZonedDateTime  startzdtE = startzdt.withZoneSameInstant(ZoneId.of("US/Eastern"));
                LocalTime startEasternTime = startzdtE.toLocalTime();

                ZonedDateTime zdt = endLocalDateTime.atZone(ZoneId.systemDefault());
                ZonedDateTime  zdtE = zdt.withZoneSameInstant(ZoneId.of("US/Eastern"));
                LocalTime easternTime = zdtE.toLocalTime();



                if(startEasternTime.isBefore(openingBusinessTime) || startEasternTime.isAfter(closingBusinessTime) || (easternTime.isBefore(openingBusinessTime) || easternTime.isAfter(closingBusinessTime))) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Scheduling Error");
                    alert.setHeaderText("Business Hours");
                    alert.setContentText("Sorry our Business hours are 8 AM til 10 PM. Please change appointment");
                    alert.setGraphic(null);
                    alert.showAndWait();
                    return;
                }

                if(startLocalDateTime.isAfter(endLocalDateTime) || startLocalDateTime.isEqual(endLocalDateTime)){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Date Time Mismatch");
                    alert.setContentText("The start time cannot be greater than or equal to the end date/time.");
                    alert.setGraphic(null);
                    alert.showAndWait();
                    return;
                }

                for(Appointment a: AppointmentMSQL.findAll()) {
                    int start = Integer.parseInt(startLocalDateTime.toString());
                    System.out.println(start);
                    int end = Integer.parseInt(endLocalDateTime.toLocalTime().toString());
                    System.out.println(end);
                    int startOfOldApp = Integer.parseInt(a.getStartDateTime().toLocalTime().toString());
                    System.out.println(startOfOldApp);
                    int endOfOldApp = Integer.parseInt(a.getEndDateTime().toLocalTime().toString());
                    System.out.println(endOfOldApp);



                    if(start >= startOfOldApp && start < endOfOldApp){
                        isAppointmentConflict=true;
                    }




                    if (isAppointmentConflict) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Appointment Time Conflict");
                        alert.setHeaderText("Sorry Unavailable Date/Time.");
                        alert.setContentText("Those dates and times are not available.");
                        alert.setGraphic(null);
                        alert.showAndWait();
                        return;
                    }
                }

                Appointment newAppointment = new Appointment(1, titleTextField.getText(), descriptionTextField.getText(), locationTextField.getText(), typeTextField.getText(), startLocalDateTime, endLocalDateTime, customerIdCB.getValue().getCustomerId(), Main.currentUser.getUserId(), contactComboBox.getValue().getContactId());
                AppointmentMSQL.create(newAppointment);
                returnToHomeView();
        }

/** This is the event handler for the cancel button. */
    public void cancelButtonClicked(ActionEvent actionEvent) throws IOException {
        returnToHomeView();
    }
}
