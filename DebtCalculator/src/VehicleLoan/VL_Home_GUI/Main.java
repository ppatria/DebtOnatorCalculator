package VehicleLoan.VL_Home_GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

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