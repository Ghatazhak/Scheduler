package controller;

import data_access.ContactReportMYSQL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.Main;
import model.ContactReport;
import view.FXMLLoaderInterface;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class typeAmountByView implements Initializable {
    public ObservableList<ContactReport> contactReport = FXCollections.observableArrayList();
    public TableView<ContactReport> typeAmountByMonthTableView;
    public TableColumn<ContactReport, String> monthCol;
    public TableColumn<ContactReport, String> typeCol;
    public TableColumn<ContactReport, Integer> amountCol;

    FXMLLoaderInterface loaderLambda = s -> FXMLLoader.load((Objects.requireNonNull(getClass().getResource(s))));

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        contactReport = ContactReportMYSQL.getContactReport();
        typeAmountByMonthTableView.setItems(contactReport);
        monthCol.setCellValueFactory(new PropertyValueFactory<>("month"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        amountCol.setCellValueFactory(new PropertyValueFactory<>("typeAmount"));
    }
/** A event handler that returns the user to appointment view. (loaderLambda) A Lambda that keeps the compiler from complaining about duplicate code. It Loads the fxml file into root.*/
    public void returnButtonClicked(ActionEvent actionEvent) throws IOException {
        Parent root = loaderLambda.getRoot("/view/appointmentView.fxml");
        Stage stage = (Stage) typeAmountByMonthTableView.getScene().getWindow();
        Scene scene = new Scene(root, 1020, 475);
        stage.setTitle("Scheduler v1.0 Appointments:   " + Main.currentUser.getUsername());
        stage.setScene(scene);
        stage.show();
    }
}
