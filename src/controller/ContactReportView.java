package controller;

import data_access.AppointmentMSQL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Appointment;

import java.net.URL;
import java.util.ResourceBundle;

public class ContactReportView implements Initializable {
    ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
    ObservableList<Appointment> appointmentsByContact = FXCollections.observableArrayList();
    public TableView contactReportTableView;
    public TableColumn appointmentIDCol;
    public TableColumn titleCol;
    public TableColumn typeCol;
    public TableColumn descriptionCol;
    public TableColumn startCol;
    public TableColumn endCol;
    public TableColumn customerIDCol;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        allAppointments = AppointmentMSQL.findAll();
        for(Appointment a: allAppointments){
            if(a.getContactId() == ContactSelectForReportView.tempContact.getContactId()){
                appointmentsByContact.add(a);
            }
        }
        contactReportTableView.setItems(appointmentsByContact);

        appointmentIDCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        startCol.setCellValueFactory(new PropertyValueFactory<>("startDateTime"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("endDateTime"));
        customerIDCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));

    }
}
