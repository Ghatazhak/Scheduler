package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

/** The controller for loginView. */
public class loginView implements Initializable {
    @FXML
    public TextField usernameTextField;
    @FXML
    public Label errorMessageLabel;
    @FXML
    public TextField passwordTextField;
    @FXML
    public Label userLocation;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
/** Event handler for login button. */
    public void loginButtonPressed(ActionEvent actionEvent) throws IOException {

        if(Objects.equals(usernameTextField.getText(), "admin") && Objects.equals(passwordTextField.getText(), "admin")){
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/homeView.fxml")));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 1020, 475);
            stage.setTitle("Scheduler v1.0 Appointments");
            stage.setScene(scene);
            stage.show();
        } else {
            errorMessageLabel.setText("--Invalid login credentials--");
        }




    }
/** Event handler for the exit button. */
    public void exitButtonPressed(ActionEvent actionEvent) {
        Platform.exit();
    }
}
