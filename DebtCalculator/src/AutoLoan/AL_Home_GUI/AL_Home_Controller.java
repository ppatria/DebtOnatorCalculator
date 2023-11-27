package AutoLoan.AL_Home_GUI;

import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

public class AL_Home_Controller {

    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private Button homepageSubmitButton;
    @FXML
    private Button newUserButton;

    public void homepageSubmit(ActionEvent homepageSubmitEvent) throws IOException{
        checkUsernameAndPassword();
    }

    private void checkUsernameAndPassword() throws FileNotFoundException {
        String inputUsername = usernameTextField.getText();
        String inputPassword = passwordTextField.getText();
        
        try {
            File file = new File("resources/" + inputUsername + ".txt");
            Scanner scanner = new Scanner(file);
            
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] details = line.split(",");

                if(details[0].equals("username") && details[1].equals(inputUsername)){
                    if (scanner.hasNextLine()) {
                        line = scanner.nextLine();
                        details = line.split(",");
                        if(details[0].equals("password") && details[1].equals(inputPassword)){
                            new Alert(Alert.AlertType.INFORMATION, "Login Successful").showAndWait();
                            return;
                        }
                    }
                    new Alert(Alert.AlertType.ERROR, "Invalid password").showAndWait();
                    return;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        new Alert(Alert.AlertType.ERROR, "Username does not exist").showAndWait();
    }

      public void newUser(ActionEvent newUserEvent) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AutoLoan/AL_New_User_GUI/AL_New_User.fxml"));
        Parent root = loader.load();
        Main sceneChangerObject = new Main();
        sceneChangerObject.changeScene(root);
    }

}