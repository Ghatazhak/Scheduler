package controller;

import data_access.AppointmentMSQL;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointment;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class appointmentView implements Initializable {
    public static Appointment tempAppointment;
    @FXML
    public TableView<Appointment> allAppointmentsTableView;
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
    public TableColumn<Appointment, String> startDateTimeCol;
    @FXML
    public TableColumn<Appointment, String> endDateTimeCol;
    @FXML
    public TableColumn<Appointment, String> customerIDCol;
    @FXML
    public TableColumn<Appointment, String> userIdCol;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Appointment> list;
        try{
            list = AppointmentMSQL.findAll();
            allAppointmentsTableView.setItems(list);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

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
        // Couldn't get stage from menu item. Had to pick something else on the screen. I picked the table view.
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
        // Couldn't get stage from menu item. Had to pick something else on the screen. I picked the table view.
        Stage stage = (Stage) allAppointmentsTableView.getScene().getWindow();
        Scene scene = new Scene(root, 333, 429);
        stage.setTitle("Add Appointment");
        stage.setScene(scene);
        stage.show();
    }
    /** This is an event handler for editing appointments. */
    public void editAppointmentMenuClicked(ActionEvent actionEvent) throws IOException {

        tempAppointment = allAppointmentsTableView.getSelectionModel().getSelectedItem();

        if(tempAppointment == null){
            return;
        }

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
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/customerView.fxml")));
        // Couldn't get stage from menu item. Had to pick something else on the screen. I picked the table view.
        Stage stage = (Stage) allAppointmentsTableView.getScene().getWindow();
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
    }

    public void onMonthRadioButton(ActionEvent actionEvent) {
    }

    public void onAllRadioButton(ActionEvent actionEvent) {
    }
}
