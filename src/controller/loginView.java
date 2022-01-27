package controller;

import data_access.AppointmentMSQL;
import data_access.UserLogI;
import data_access.UserMYSQL;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.Main;
import model.Appointment;
import model.User;
import view.FXMLLoaderInterface;

import java.io.*;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * The controller for loginView.
 */
public class loginView implements Initializable {
    String retrievedPassword = null;
    String loginResult = null;
    String userAttemptingLogin = "null";
    ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
    boolean isUpcomingAppointment = false;

    FXMLLoaderInterface loaderLambda = s -> FXMLLoader.load((Objects.requireNonNull(getClass().getResource(s))));

    UserLogI logLambda = (user, ldt, result) -> {

        PrintWriter log = null;
        try {
            log = new PrintWriter(new FileOutputStream(new File("login_activity.txt"), true));
            log.append("\nUser [" + user + "] attempted to login at [" + ldt + "] Result [ " + result + "]");
            log.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    };

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

        userLocation.setText(ZoneId.systemDefault().toString());
        ResourceBundle rb = ResourceBundle.getBundle("language_files/rb", Locale.getDefault());
        if (Locale.getDefault().getLanguage().equals("fr")) {
            userNameLabel.setText(rb.getString("Username"));
            usernameTextField.setPromptText(rb.getString("Username"));
            passwordTextField.setPromptText(rb.getString("Password"));
            passwordLabel.setText(rb.getString("Password"));
            loginButtonText.setText(rb.getString("Login"));
            exitButtonText.setText(rb.getString("Exit"));
        }
        }
    /** An event handler for the login button. It checks your username and password and logs the time and if the login was successful in a text file. Lambda #1 A Lambda that writes the login log. Simplifies the code.*/
        public void loginButtonPressed (ActionEvent actionEvent)  {
            userAttemptingLogin = usernameTextField.getText();
            try {
                User user = UserMYSQL.findByUsername(usernameTextField.getText());
                Main.currentUser = user;
                retrievedPassword = user.getPassword();
                userAttemptingLogin = Main.currentUser.getUsername();
            } catch (Exception e) {
                System.out.println("User Not in Database");
            }

            if (passwordTextField.getText().equals(retrievedPassword)) {
                loginResult = "Successful";
                logLambda.writeLog(userAttemptingLogin, LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM-dd-yyyy hh:mm")), loginResult);


                allAppointments = AppointmentMSQL.findAll();

                for (Appointment a : allAppointments) {


                    ZoneId zid = ZoneId.of("US/Eastern");
                    LocalDateTime currentTime = LocalDateTime.now(zid);

                    ZonedDateTime startDT = a.getStartDateTime().atZone(zid);

                    long timeDifference = ChronoUnit.MINUTES.between(startDT.toLocalDateTime(), currentTime) + 1;

                    if ((timeDifference * -1) < 15 && (timeDifference * -1 > 0)) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Appointments Alert");
                        alert.setHeaderText("Upcoming Appointment");
                        alert.setContentText("You have the following Appointment with in 15 minutes: ID: "+ a.getAppointmentId() +" Date/Time: "+a.getStartDateTime());
                        alert.setGraphic(null);
                        isUpcomingAppointment = true;
                        alert.showAndWait();
                    }
                }

                if(!isUpcomingAppointment){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Appointments Alert");
                    alert.setHeaderText("No Appointment");
                    alert.setContentText("You have no appointment within 15 minutes");
                    alert.setGraphic(null);
                    alert.showAndWait();
                }

                Parent root = null;
                try {
                    root = loaderLambda.getRoot("/view/appointmentView.fxml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene(root, 1020, 475);
                stage.setTitle("Scheduler v1.0 Appointments:   " + Main.currentUser.getUsername());
                stage.setScene(scene);
                stage.show();
            } else {
                ResourceBundle rb = ResourceBundle.getBundle("language_files/rb", Locale.getDefault());
                if (Main.currentUser == null) {

                }
                if (Locale.getDefault().getLanguage().equals("fr")) {
                    loginResult = "Failed";
                    logLambda.writeLog(userAttemptingLogin, LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM-dd-yyyy hh:mm")), loginResult);
                    errorMessageLabel.setText(rb.getString("Invalid") + " " + rb.getString("Login"));
                } else {
                    loginResult = "Failed";
                    logLambda.writeLog(userAttemptingLogin, LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM-dd-yyyy hh:mm")), loginResult);
                    errorMessageLabel.setText(rb.getString("Invalid") + " " + rb.getString("Login"));
                }
            }
        }

/** Event handler for the exit button. It exits the application */
        public void exitButtonPressed (ActionEvent actionEvent){
            Platform.exit();
        }
    }
