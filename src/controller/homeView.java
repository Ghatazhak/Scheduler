package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class homeView implements Initializable {
    @FXML
    public TableView allAppointmentsTableView;
    @FXML
    public TableColumn appointmentIdCol;
    @FXML
    public TableColumn titleCol;
    @FXML
    public TableColumn descriptionCol;
    @FXML
    public TableColumn locationCol;
    @FXML
    public TableColumn contactCol;
    @FXML
    public TableColumn typeCol;
    @FXML
    public TableColumn startDateTimeCol;
    @FXML
    public TableColumn endDateTimeCol;
    @FXML
    public TableColumn customerIDCol;
    @FXML
    public TableColumn userIdCol;
/** This is an event handler for log off. */
    public void logOffMenuClicked(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/loginView.fxml")));
        // Couldn't get stage from menu item. Had to pick something else on the screen. I picked the table view.
        Stage stage = (Stage) allAppointmentsTableView.getScene().getWindow();
        Scene scene = new Scene(root, 500, 300);
        stage.setTitle("Scheduler v1.0");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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
        // Couldn't get stage from menu item. Had to pick something else on the screen. I picked the table view.
        Stage stage = (Stage) allAppointmentsTableView.getScene().getWindow();
        Scene scene = new Scene(root, 333, 429);
        stage.setTitle("Add Appointment");
        stage.setScene(scene);
        stage.show();
    }
    /** This is an event handler for editing appointments. */
    public void editAppointmentMenuClicked(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/editAppointmentView.fxml")));
        // Couldn't get stage from menu item. Had to pick something else on the screen. I picked the table view.
        Stage stage = (Stage) allAppointmentsTableView.getScene().getWindow();
        Scene scene = new Scene(root, 333, 429);
        stage.setTitle("Edit Appointment");
        stage.setScene(scene);
        stage.show();
    }
    /** This is an event handler for deleting appointments. */
    public void deleteAppointmentMenuClicked(ActionEvent actionEvent) {
    }
    /** This is an event handler for customer management. */
    public void customerManagementMenuClicked(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/customerManagementView.fxml")));
        // Couldn't get stage from menu item. Had to pick something else on the screen. I picked the table view.
        Stage stage = (Stage) allAppointmentsTableView.getScene().getWindow();
        Scene scene = new Scene(root, 920, 600);
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



}
