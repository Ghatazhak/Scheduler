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
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Appointment;
import model.Contact;
import model.Customer;
import model.User;
import view.FXMLLoaderInterface;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.ResourceBundle;
/** A controller for adding appointments. */
public class addAppointmentView implements Initializable {

    ObservableList<String> startHours = FXCollections.observableArrayList();
    ObservableList<String> startMinutes = FXCollections.observableArrayList();
    ObservableList<String> endHours = FXCollections.observableArrayList();
    ObservableList<String> endMinutes = FXCollections.observableArrayList();
    ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
    ObservableList<Contact> allContacts = FXCollections.observableArrayList();
    ObservableList<User> allUsers = FXCollections.observableArrayList();
    ObservableList<Appointment> appointmentsConflicts = FXCollections.observableArrayList();
    LocalTime openingBusinessTime = LocalTime.of(8, 0, 0);
    LocalTime closingBusinessTime = LocalTime.of(22, 0, 0);

    FXMLLoaderInterface loaderLambda = s -> FXMLLoader.load((Objects.requireNonNull(getClass().getResource(s))));

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
    public ComboBox<User> userIDComboBox;
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
    @FXML
    public Label eCurrentTime;
    @FXML
    public Label lCurrentTime;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lCurrentTime.setText(String.valueOf(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"))));
        eCurrentTime.setText(String.valueOf(LocalTime.now(ZoneId.of("US/Eastern")).format(DateTimeFormatter.ofPattern("HH:mm"))));


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

        try {
            allUsers = UserMYSQL.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        userIDComboBox.setItems(allUsers);
    }

    /**
     * A method that can return you to the Appointment View. Lambda #2 A Lambda that keeps the compiler from complaining about duplicate code. It Loads the fxml file into root.
     */
    public void returnToHomeView() {
        Parent root = null;
        try {
            root = loaderLambda.getRoot("/view/appointmentView.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) startHourCB.getScene().getWindow();
        stage.close();
    }

    /**
     * This is the event handler for the save button. It validates the data and saves it.
     */
    public void saveButtonClicked(ActionEvent actionEvent) throws IOException, SQLException {
        appointmentsConflicts.clear();
        if (titleTextField.getText().isEmpty() || descriptionTextField.getText().isEmpty() || locationTextField.getText().isEmpty() || contactComboBox.getValue() == null || typeTextField.getText().isEmpty() || startHourCB.getValue() == null || startMinuteCB.getValue() == null || endHourCB.getValue() == null || endMinuteCB.getValue() == null || datePicker.getValue() == null || contactComboBox.getValue() == null || customerIdCB.getValue() == null || userIDComboBox.getValue() == null) {
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
        ZonedDateTime startzdtE = startzdt.withZoneSameInstant(ZoneId.of("US/Eastern"));
        LocalTime startEasternTime = startzdtE.toLocalTime();

        ZonedDateTime zdt = endLocalDateTime.atZone(ZoneId.systemDefault());
        ZonedDateTime zdtE = zdt.withZoneSameInstant(ZoneId.of("US/Eastern"));
        LocalTime easternTime = zdtE.toLocalTime();


        if (startEasternTime.isBefore(openingBusinessTime) || startEasternTime.isAfter(closingBusinessTime) || (easternTime.isBefore(openingBusinessTime) || easternTime.isAfter(closingBusinessTime))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Scheduling Error");
            alert.setHeaderText("Business Hours");
            alert.setContentText("Sorry our Business hours are 8 AM til 10 PM. Please change appointment");
            alert.setGraphic(null);
            alert.showAndWait();
            return;
        }

        if (startLocalDateTime.isAfter(endLocalDateTime) || startLocalDateTime.isEqual(endLocalDateTime)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Date Time Mismatch");
            alert.setContentText("The start time cannot be greater than or equal to the end date/time.");
            alert.setGraphic(null);
            alert.showAndWait();
            return;
        }

        for (Appointment a : AppointmentMSQL.findAll()) {
            if (customerIdCB.getValue().getCustomerId() == a.getCustomerId()) {

                if (startLocalDateTime.isAfter(a.getStartDateTime()) && startLocalDateTime.isBefore(a.getEndDateTime())) {
                    appointmentsConflicts.add(a);

                }

                if (startLocalDateTime.isEqual(a.getStartDateTime())) {
                    appointmentsConflicts.add(a);

                }

                if (!appointmentsConflicts.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Appointment Time Conflict");
                    alert.setHeaderText("Sorry Unavailable Date/Time.");
                    alert.setContentText("Those dates and times are not available.");
                    alert.setGraphic(null);
                    alert.showAndWait();
                    return;
                }
            }
        }

            System.out.println(" app addded");
            Appointment newAppointment = new Appointment(1, titleTextField.getText(), descriptionTextField.getText(), locationTextField.getText(), typeTextField.getText(), startLocalDateTime, endLocalDateTime, customerIdCB.getValue().getCustomerId(), userIDComboBox.getValue().getUserId(), contactComboBox.getValue().getContactId());
            AppointmentMSQL.create(newAppointment);
            returnToHomeView();

        }

/** This is the event handler for the cancel button. */
    public void cancelButtonClicked(ActionEvent actionEvent) throws IOException {
        returnToHomeView();
    }
}
