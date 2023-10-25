package VehicleLoan.VL_Home_GUI;

import javafx.scene.layout.GridPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class test extends Application{
    
    test test = new test();

    @Override
    public void start(Stage primaryStage) {

        GridPane gridSecond = new GridPane();
        Label secondLabel = new Label(test.getExistingUser());
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

