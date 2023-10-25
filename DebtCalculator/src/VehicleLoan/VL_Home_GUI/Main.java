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

public class Main extends Application{

    private static Stage sceneChangerObject;

    @Override
    public void start(Stage primaryStage) throws Exception{
        sceneChangerObject = primaryStage;
        primaryStage.setResizable(true);
        Parent root = FXMLLoader.load(getClass().getResource("VL_Home.fxml"));
        primaryStage.setTitle("DebtOnator Calculator");
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.show();
    }
    
    public void changeScene(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        sceneChangerObject.getScene().setRoot(pane);
    }

    public static void main(String[] args) {
        launch(args);
    }
}