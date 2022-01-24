package controller;

import data_access.AppointmentMSQL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Appointment;
import view.FXMLLoaderInterface;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class appointmentView implements Initializable {
    public static Appointment tempAppointment;
    private ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();


    FXMLLoaderInterface loaderLambda = s -> {
        Parent root = FXMLLoader.load((Objects.requireNonNull(getClass().getResource(s))));
        return root;
    };

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
        allAppointmentsTableView.setItems(allAppointments);
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
        Parent root = loaderLambda.getRoot("/view/loginView.fxml");
        Stage stage = (Stage) allAppointmentsTableView.getScene().getWindow();
        Scene scene = new Scene(root, 500, 300);
        stage.setTitle("Scheduler v1.0");
        stage.setScene(scene);
        stage.show();
    }

        /** This is an event handler for adding an appointment. */
    public void addAppointmentMenuClicked(ActionEvent actionEvent) throws IOException {
        Parent root = loaderLambda.getRoot("/view/addAppointmentView.fxml");
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

        Parent root = loaderLambda.getRoot("/view/editAppointmentView.fxml");
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
        Parent root = loaderLambda.getRoot("/view/customerView.fxml");
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(root, 540, 400);
        stage.setTitle("Customer Management");
        stage.setScene(scene);
        stage.show();
    }
    /** This is an event handler for type by month report. */
    @FXML
    public void typeByMonthReportMenuClicked(ActionEvent actionEvent) {
    }
    /** This is an event handler for contact report. */
    @FXML
    public void contactReportMenuClicked(ActionEvent actionEvent) throws IOException {
        Parent root = loaderLambda.getRoot("/view/ContactSelectForReportView.fxml");
        Stage stage = (Stage) allAppointmentsTableView.getScene().getWindow();
        Scene scene = new Scene(root, 340, 175);
        stage.setTitle("Contact Select Screen");
        stage.setScene(scene);
        stage.show();
    }
    /** This is an event handler for custom report. */
    @FXML
    public void customReportMenuClicked(ActionEvent actionEvent) {
    }
    @FXML
    public void onWeeklyRadioButton(ActionEvent actionEvent) {

    }
    @FXML
    public void onMonthRadioButton(ActionEvent actionEvent) {


    }
    @FXML
    public void onAllRadioButton(ActionEvent actionEvent) {

    }

}
