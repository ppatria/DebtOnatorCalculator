package AutoLoan.AL_Home_GUI;

import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import AutoLoan.AL_Calculations_GUI.AL_Calculations_Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;

public class AL_Home_Controller {

    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private Button homepageSubmitButton;
    @FXML
    private Button newUserButton;

    public void homepageSubmit(ActionEvent homepageSubmitEvent) throws IOException{
        if (usernameTextField.getText().isEmpty() || passwordTextField.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please Enter Username and Password").showAndWait();
        } else {
            try{
                checkUsernameAndPassword();
            } catch (FileNotFoundException e){

            }
        }
    }

    private void checkUsernameAndPassword() throws FileNotFoundException {
      String inputUsername = usernameTextField.getText();
      String inputPassword = passwordTextField.getText();
      String username = null;
      String vehicleYear = null;
      String vehicleMake = null;
      String vehicleModel = null;
      Scanner scanner = null;
      
      try {
          File file = new File("resources/" + inputUsername + ".txt");
          scanner = new Scanner(file);
          while (scanner.hasNextLine()) {
              String line = scanner.nextLine();
              String[] details = line.split(",");
              switch (details[0]) {
                  case "username":
                      if (details[1].equals(inputUsername)) {
                          username = inputUsername;
                      }
                      break;
                  case "password":
                      if (details[1].equals(inputPassword)) {
                          // if password correct but username wrong or not present
                          if (username == null) {
                              new Alert(Alert.AlertType.ERROR, "Username does not exist").showAndWait();
                              return;
                          } else {
                              new Alert(Alert.AlertType.INFORMATION, "Login Successful").showAndWait();
                          }
                      } else {
                          new Alert(Alert.AlertType.ERROR, "Invalid password").showAndWait();
                          return;
                      }
                      break;
                  case "year":
                      vehicleYear = details[1];
                      break;
                  case "make":
                      vehicleMake = details[1];
                      break;
                  case "model":
                      vehicleModel = details[1];
                      break;
              }
          }
      } catch (FileNotFoundException e) {
          new Alert(Alert.AlertType.ERROR, "Username does not exist").showAndWait();
          throw e;
      } finally {
          if (scanner != null) {
            scanner.close();
      }
      }
    
      // Check all fields are filled correctly
      if (username != null && vehicleYear != null && vehicleMake != null && vehicleModel != null) {
          try {
            existingUser(username, vehicleYear, vehicleMake, vehicleModel);
          } catch (IOException e) {
            e.printStackTrace();
          }
      } else {
          new Alert(Alert.AlertType.ERROR, "Missing user or vehicle details").showAndWait();
      }

    }

    // Method to go to Calculations_GUI
    public void existingUser(String username, String vehicleYear, String vehicleMake, String vehicleModel) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AutoLoan/AL_Calculations_GUI/AL_Calculations.fxml"));
        Parent root = loader.load();
        AL_Calculations_Controller calculationsController = loader.getController();
        calculationsController.setUserData(username, vehicleYear, vehicleMake, vehicleModel);
        Main sceneChangerObject = new Main();
        sceneChangerObject.changeScene(root);
    }

      // Goes to New_User_GUI
      public void newUser(ActionEvent newUserEvent) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AutoLoan/AL_New_User_GUI/AL_New_User.fxml"));
        Parent root = loader.load();
        Main sceneChangerObject = new Main();
        sceneChangerObject.changeScene(root);
    }

}