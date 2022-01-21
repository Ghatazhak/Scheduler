package controller;

import data_access.AppointmentMSQL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Appointment;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class appointmentView implements Initializable {
    public static Appointment tempAppointment;
    private ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
    private FilteredList<Appointment> filteredList;

    @FXML
    public TableView<Appointment> allAppointmentsTableView;
    @FXML
    public ToggleGroup timePeriodFilterRadioGroup;
    @FXML
    public TableColumn<Appointment, Integer> appointmentIdCol;
    @FXML
    public TableColumn<Appointment, String> titleCol;
    @FXML
    public TableColumn<Appointment, String> descriptionCol;
    @FXML
    public TableColumn<Appointment, String> locationCol;
    @FXML
    public TableColumn<Appointment, String> contactCol;
    @FXML
    public TableColumn<Appointment, String> typeCol;
    @FXML
    public TableColumn<Appointment, LocalDateTime> startDateTimeCol;
    @FXML
    public TableColumn<Appointment, LocalDateTime> endDateTimeCol;
    @FXML
    public TableColumn<Appointment, String> customerIDCol;
    @FXML
    public TableColumn<Appointment, String> userIdCol;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        allAppointments = AppointmentMSQL.findAll();
        Appointment appointment = allAppointments.get(0);
        System.out.println(appointment.getStartDateTime().getDayOfMonth());

        filteredList = new FilteredList<Appointment>(allAppointments);
        new Predicate<Appointment>() {
            @Override
            public boolean test(Appointment appointment) {
                return true;
            }
        };
        allAppointmentsTableView.setItems(filteredList);
        appointmentIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        startDateTimeCol.setCellValueFactory(new PropertyValueFactory<>("startDateTime"));
        endDateTimeCol.setCellValueFactory(new PropertyValueFactory<>("endDateTime"));
        customerIDCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        userIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("ContactId"));
    }

/** This is an event handler for log off. */
    public void logOffMenuClicked(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/loginView.fxml")));
        Stage stage = (Stage) allAppointmentsTableView.getScene().getWindow();
        Scene scene = new Scene(root, 500, 300);
        stage.setTitle("Scheduler v1.0");
        stage.setScene(scene);
        stage.show();
    }

    /** This is an event handler for default schedule view. */
    public void defaultScheduleMenuClicked(ActionEvent actionEvent) {
    }
    /** This is an event handler for Weekly schedule view. */
    public void byWeekScheduleMenuClicked(ActionEvent actionEvent) {
    }
    /** This is an event handler for Monthly schedule view. */
    public void byMonthScheduleMenuClicked(ActionEvent actionEvent) {
    }
    /** This is an event handler for adding an appointment. */
    public void addAppointmentMenuClicked(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/addAppointmentView.fxml")));
        Stage stage = (Stage) allAppointmentsTableView.getScene().getWindow();
        Scene scene = new Scene(root, 380, 430);
        stage.setTitle("Add Appointment");
        stage.setScene(scene);
        stage.show();
    }
    /** This is an event handler for editing appointments. */
    public void editAppointmentMenuClicked(ActionEvent actionEvent) throws IOException {

        tempAppointment = allAppointmentsTableView.getSelectionModel().getSelectedItem();

        if (tempAppointment == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No Selection");
            alert.setContentText("You must have an appointment selected to edit.");
            alert.setGraphic(null);
            alert.showAndWait();
            return;
        }

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/editAppointmentView.fxml")));
        // Couldn't get stage from menu item. Had to pick something else on the screen. I picked the table view.
        Stage stage = (Stage) allAppointmentsTableView.getScene().getWindow();
        Scene scene = new Scene(root, 380, 430);
        stage.setTitle("Edit Appointment");
        stage.setScene(scene);
        stage.show();
    }
    /** This is an event handler for deleting appointments. */
    public void deleteAppointmentMenuClicked(ActionEvent actionEvent) {
        tempAppointment = allAppointmentsTableView.getSelectionModel().getSelectedItem();
        if (tempAppointment == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No Selection");
            alert.setContentText("You must have an appointment selected to delete.");
            alert.setGraphic(null);
            alert.showAndWait();
            return;
        }

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure you want to delete this Appointment?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                String change = AppointmentMSQL.delete(tempAppointment);
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setTitle("Deleted");
                alert2.setHeaderText("You deleted the following!");
                alert2.setContentText(change);
                alert2.setGraphic(null);
                alert2.showAndWait();
                ObservableList<Appointment> allAppointments = AppointmentMSQL.findAll();
                allAppointmentsTableView.setItems(allAppointments);
            }
        }


    /** This is an event handler for customer management. */
    public void customerEditclicked(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/customerView.fxml")));
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(root, 540, 400);
        stage.setTitle("Customer Management");
        stage.setScene(scene);
        stage.show();
    }
    /** This is an event handler for type by month report. */
    public void typeByMonthReportMenuClicked(ActionEvent actionEvent) {
    }
    /** This is an event handler for contact report. */
    public void contactReportMenuClicked(ActionEvent actionEvent) {
    }
    /** This is an event handler for custom report. */
    public void customReportMenuClicked(ActionEvent actionEvent) {
    }

    public void onWeeklyRadioButton(ActionEvent actionEvent) {
        filteredList.setPredicate(new Predicate<Appointment>() {
            @Override
            public boolean test(Appointment appointment) {
                if(true){
                    return true;
                }
                return false;
            }

        });
    }

    public void onMonthRadioButton(ActionEvent actionEvent) {
        filteredList.setPredicate(new Predicate<Appointment>() {
            @Override
            public boolean test(Appointment appointment) {
                if(appointment.getStartDateTime().getMonth() == LocalDateTime.now().getMonth()){
                    return true;
                }
                return false;
            }

        });

    }

    public void onAllRadioButton(ActionEvent actionEvent) {
        filteredList.setPredicate(new Predicate<Appointment>() {
            @Override
            public boolean test(Appointment appointment) {
                return true;
            }
        });
    }

}
