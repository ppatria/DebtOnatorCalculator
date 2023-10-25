package VehicleLoan.VL_Home_GUI;

import javafx.application.Application;
import javafx.geometry.Insets;
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

public class VL_Home_GUI extends Application{

    @Override
    public void start(Stage primaryStage) {

        //Create Grid Pane
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(0, 10, 0, 10));

        //Create Elements
        Label labelTitle = new Label("Homepage");
        grid.add(labelTitle, 0, 0, 2, 1);

        Label labelNewUser = new Label("New User Enter Here:");
        grid.add(labelNewUser, 0, 1);

        TextField textFieldNewUser = new TextField();
        grid.add(textFieldNewUser, 1, 1);
        
        Label labelExistingUser = new Label("Existing User Enter Here:");
        grid.add(labelExistingUser, 0, 2);

        TextField textFieldExistingUser = new TextField();
        grid.add(textFieldExistingUser, 1, 2);

        Button buttonSubmit = new Button("Submit");
        grid.add(buttonSubmit, 1, 3);

        //Set OnClick for the button        
        buttonSubmit.setOnMouseClicked(e -> {
            String newUser = textFieldNewUser.getText();
            String existingUser = textFieldExistingUser.getText();

            // Validate inputs
            Pattern pattern = Pattern.compile("[A-Za-z0-9]*");
            Matcher newMatcher = pattern.matcher(newUser);
            Matcher existMatcher = pattern.matcher(existingUser);

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
                // Create a new text file in the 'src' folder of the project.
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
                    Stage secondStage = new Stage();
                    GridPane gridSecond = new GridPane();
                    Label secondLabel = new Label(existingUser);
                    gridSecond.add(secondLabel, 1, 1);
                    Scene secondScene = new Scene(gridSecond, 200, 200);
                    secondStage.setScene(secondScene);
                    secondStage.show();
                    primaryStage.hide();
                } else {
                    new Alert(Alert.AlertType.ERROR, "This user doesn't exist, Please enter in as a new user").showAndWait();
                }
            }

        
        });

        //Build the Stage
        Scene scene = new Scene(grid, 500, 500);
        primaryStage.setTitle("Welcome to the DebtOnator Calculator Homepage!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}