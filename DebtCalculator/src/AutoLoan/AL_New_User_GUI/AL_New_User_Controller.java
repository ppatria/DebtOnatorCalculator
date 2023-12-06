package AutoLoan.AL_New_User_GUI;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import AutoLoan.AL_Calculations_GUI.AL_Calculations_Controller;
import AutoLoan.AL_Home_GUI.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

public class AL_New_User_Controller {

    @FXML
    private TextField newUsernameTextField;
    @FXML
    private TextField newPasswordTextField;
    @FXML
    private TextField vehicleYearTextField;
    @FXML
    private TextField vehicleMakeTextField;
    @FXML
    private TextField vehicleModelTextField;
    @FXML
    private Button createNewAccountButton;
    

    public void createNewAccount(ActionEvent createNewAccountEvent) throws IOException {
        createNewUserFile();
    }

    private void createNewUserFile() throws IOException {
        // Get username, password and vehicle info from the textFields
        String username = newUsernameTextField.getText();
        String password = newPasswordTextField.getText();
        String vehicleYear = vehicleYearTextField.getText();
        String vehicleMake = vehicleMakeTextField.getText();
        String vehicleModel = vehicleModelTextField.getText();

        // Validate inputs for Username, password and vehicle info
        Pattern usernamePattern = Pattern.compile("[A-Za-z0-9]*");
        Matcher usernameMatcher = usernamePattern.matcher(username);
        Pattern passwordPattern = Pattern.compile("[A-Za-z0-9]*");
        Matcher passwordMatcher = passwordPattern.matcher(password);
        Pattern vehicleYearPattern = Pattern.compile("[0-9]{4}");
        Matcher vehicleYearMatcher = vehicleYearPattern.matcher(vehicleYear);
        Pattern vehicleMakePattern = Pattern.compile("[A-Za-z]*");
        Matcher vehicleMakeMatcher = vehicleMakePattern.matcher(vehicleMake);
        Pattern vehicleModelPattern = Pattern.compile("[A-Za-z]*");
        Matcher vehicleModelMatcher = vehicleModelPattern.matcher(vehicleModel);

        // If statements for any error/warning/informational messages
        if (!usernameMatcher.matches() || !passwordMatcher.matches()) {
            new Alert(Alert.AlertType.ERROR, "You can only input letters and numbers for the Username and Password").showAndWait();
            return;
        }
        if(username.isEmpty() || password.isEmpty() || vehicleYear.isEmpty() || vehicleMake.isEmpty() || vehicleModel.isEmpty()){
            new Alert(Alert.AlertType.ERROR, "Please fill in all of the textfields").showAndWait();
            return;
        }
        if (!vehicleYearMatcher.matches()) {
            new Alert(Alert.AlertType.ERROR, "You can only input the Year in the form of YYYY").showAndWait();
            return;
        }
        if (!vehicleModelMatcher.matches() || !vehicleMakeMatcher.matches()) {
            new Alert(Alert.AlertType.ERROR, "You can only input letters for the Make and Model").showAndWait();
            return;
        }
        
        // Path to the resources folder
        Path resourceDir = Paths.get("resources");

        // Create a new text file in the 'resources' folder of the project.
        String fileName = resourceDir.toString() + "/" + username + ".txt";
        File file = new File(fileName);
            
        // Check if the file already exists
        if (file.exists()) {
            new Alert(Alert.AlertType.INFORMATION, "Username is already used").showAndWait();
            return;
        }
        
        try {
            file.createNewFile();
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write("username,"+ username + "\n");
            fileWriter.write("password," + password + "\n");
            fileWriter.write("year," + vehicleYear + "\n");
            fileWriter.write("make," + vehicleMake + "\n");
            fileWriter.write("model," + vehicleModel + "\n");
            fileWriter.write("state," + "\n");
            fileWriter.write("total loan amount," + "\n");
            fileWriter.write("original loan date," + "\n");
            fileWriter.write("interest rate," + "\n");
            fileWriter.write("loan term," + "\n");
            fileWriter.write("down payment," + "\n");
            fileWriter.write("sales tax," + "\n");
            fileWriter.write("additional fees," + "\n");
            fileWriter.close();

            new Alert(Alert.AlertType.INFORMATION, "Account created successfully").showAndWait();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AutoLoan/AL_Calculations_GUI/AL_Calculations.fxml"));
            Parent root = loader.load();
            AL_Calculations_Controller calculationsController = loader.getController();
            calculationsController.setUserData(username, vehicleYear, vehicleMake, vehicleModel);

            Main sceneChangerObject = new Main();
            sceneChangerObject.changeScene(root);
            
        } catch (IOException ex) {
            ex.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error in creating account").showAndWait();
        }        
    }
        
}