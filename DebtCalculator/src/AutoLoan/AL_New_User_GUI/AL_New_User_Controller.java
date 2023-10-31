package AutoLoan.AL_New_User_GUI;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class AL_New_User_Controller {

    @FXML
    private TextField newUsernameTextField;
    @FXML
    private TextField newPasswordTextField;
    @FXML
    private Button createNewAccountButton;

    public void createNewAccount(ActionEvent createNewAccountEvent) throws IOException {
        createNewUserFile();
    }

    private void createNewUserFile() throws IOException {
        // Get username and password from the textFields
        String username = newUsernameTextField.getText();
        String password = newPasswordTextField.getText();

        // Validate inputs
        Pattern usernamePattern = Pattern.compile("[A-Za-z0-9]*");
        Matcher usernameMatcher = usernamePattern.matcher(username);

        // If statements for any error/warning/informational messages
        if (!usernameMatcher.matches()) {
            new Alert(Alert.AlertType.ERROR, "You can only input letters and numbers").showAndWait();
            return;
        }
        if(username.isEmpty()){
            new Alert(Alert.AlertType.ERROR, "Username field is empty. Please fill in the username field").showAndWait();
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
            fileWriter.write("password," + password);
            fileWriter.close();

            new Alert(Alert.AlertType.INFORMATION, "Account created successfully").showAndWait();
        } catch (IOException ex) {
            ex.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error in creating account").showAndWait();
        }        
    }
}