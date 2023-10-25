package VehicleLoan.VL_InfoGUI;

import javafx.scene.layout.GridPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class VL_Info_GUI extends Application{
    @Override
    public void start(Stage primaryStage) {

        GridPane gridSecond = new GridPane();
        Label secondLabel = new Label(existingUser);
        gridSecond.add(secondLabel, 1, 1);
        Scene secondScene = new Scene(gridSecond, 200, 200);
        primaryStage.setScene(secondScene);
        primaryStage.show();
        primaryStage.hide();
    }

    public static void main(String[] args) {
        launch(args);
    }
}