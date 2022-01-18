package controller;

import data_access.UserMYSQL;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.User;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Locale;
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
    @FXML
    public Label userNameLabel;
    @FXML
    public Label passwordLabel;
    @FXML
    public Button loginButtonText;
    @FXML
    public Button exitButtonText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /* Locale french = new Locale("fr","France");
        Locale.setDefault(french); */
        userLocation.setText((Locale.getDefault().getCountry()));
        ResourceBundle rb = ResourceBundle.getBundle("language_files/rb",Locale.getDefault());
        if(Locale.getDefault().getLanguage().equals("fr")){
            userNameLabel.setText(rb.getString("Username"));
            usernameTextField.setPromptText(rb.getString("Username"));
            passwordTextField.setPromptText(rb.getString("Password"));
            passwordLabel.setText(rb.getString("Password"));
            loginButtonText.setText(rb.getString("Login"));
            exitButtonText.setText(rb.getString("Exit"));
        }
    }
/** Event handler for login button. */
    public void loginButtonPressed(ActionEvent actionEvent) throws IOException, SQLException {
        String retrievedPassword = null;
        try{
            User user = UserMYSQL.findByUsername(usernameTextField.getText());
            retrievedPassword = user.getPassword();
        } catch (Exception e){
           System.out.println(e.getMessage());
        }

        if(passwordTextField.getText().equals(retrievedPassword)){
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/appoinmentView.fxml")));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 1020, 475);
            stage.setTitle("Scheduler v1.0 Appointments");
            stage.setScene(scene);
            stage.show();
        }
            ResourceBundle rb = ResourceBundle.getBundle("language_files/rb",Locale.getDefault());
            if(Locale.getDefault().getLanguage().equals("fr")){
                errorMessageLabel.setText(rb.getString("Invalid") + " " + rb.getString("Login"));
            } else {
                errorMessageLabel.setText(rb.getString("Invalid") + " " + rb.getString("Login"));
            }
        }

/** Event handler for the exit button. */
    public void exitButtonPressed(ActionEvent actionEvent) {
        Platform.exit();
    }
}
