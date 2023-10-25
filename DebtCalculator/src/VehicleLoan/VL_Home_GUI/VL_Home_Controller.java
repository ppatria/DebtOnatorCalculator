package VehicleLoan.VL_Home_GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class VL_Home_Controller {

    @FXML
    private TextField newUserTextField;
    @FXML
    private TextField existingUserTextField;
    @FXML
    private Label errorLabel;
    @FXML
    private Button homepageSubmitButton;

    public void homepageSubmit(ActionEvent homepageSubmitEvent) throws IOException{
        checkUsernames();
    }

    private void checkUsernames() throws IOException{
        Main sceneChangerObject = new Main();

        // Get Strings from newUser and existingUser textFields
        String newUser = newUserTextField.getText();
        String existingUser = existingUserTextField.getText();

        // Validate inputs
        Pattern pattern = Pattern.compile("[A-Za-z0-9]*");
        Matcher newMatcher = pattern.matcher(newUser);
        Matcher existMatcher = pattern.matcher(existingUser);

        // If statements for any error/warning/informational messages
        if (!newMatcher.matches() || !existMatcher.matches()) {
            new Alert(Alert.AlertType.ERROR, "You can only input letters and numbers").showAndWait();
            return;
        }
        if(!newUser.isEmpty() && !existingUser.isEmpty()){
            new Alert(Alert.AlertType.ERROR, "Only Enter in One text box").showAndWait();
            return;
        }
        if (newUser.isEmpty() && existingUser.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "You must fill in one of the text boxes").showAndWait();
            return;
        }

        if(!newUser.isEmpty()){
            // Create a new text file in the 'resources' folder of the project.
            try {
                String fileName = "resources/" + newUser + "_vehicleinfo.txt";
                Path pathToFile = Paths.get(fileName);
                Files.createFile(pathToFile);
            } catch (IOException ex) {
                ex.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Username already used").showAndWait();
                return;
            } catch (IllegalArgumentException iae){
                new Alert(Alert.AlertType.ERROR, "Username not found").showAndWait();
                return;
            }
            return;
        }

        if(!existingUser.isEmpty()){
            // Here we check if a text file exists that matches the entered username.
            File tmpDir = new File("resources/" + existingUser + "_vehicleinfo.txt");
            if (tmpDir.exists()) {
                sceneChangerObject.changeScene("/VehicleLoan/VL_Info_GUI/VL_Info.fxml");
            } else {
                new Alert(Alert.AlertType.ERROR, "This user doesn't exist, Please enter in as a new user").showAndWait();
            }
        }
    }

}