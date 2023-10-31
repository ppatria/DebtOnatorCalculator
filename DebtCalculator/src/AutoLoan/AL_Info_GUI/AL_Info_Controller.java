package AutoLoan.AL_Info_GUI;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import AutoLoan.AL_Home_GUI.Main;

public class AL_Info_Controller {
    
    @FXML
    private TextField vehicleYearTextField;
    @FXML
    private TextField vehicleMakeTextField;
    @FXML
    private TextField vehicleModelTextField;

    public void infopageSubmitEvent(ActionEvent infopageSubmitEvent) throws IOException{
        checkVehicleInfo();
    }

    private void checkVehicleInfo() throws IOException{
        Main sceneChangerObject = new Main();

        String vehicleYear = vehicleYearTextField.getText();
        String vehicleMake = vehicleMakeTextField.getText();
        String vehicleModel = vehicleModelTextField.getText();

        Pattern onlyNumbers = Pattern.compile("[0-9]*");
        Pattern onlyLetters = Pattern.compile("[A-za-z]*");

        Matcher vehicleYearMatcher = onlyNumbers.matcher(vehicleYear);
        Matcher vehicleMakeMatcher = onlyLetters.matcher(vehicleMake);
        Matcher vehicleModelMatcher = onlyLetters.matcher(vehicleModel);

        if (!vehicleYearMatcher.matches()){
            new Alert(Alert.AlertType.ERROR, "Please Enter the Year in the format of YYYY").showAndWait();
            return;
        }
    }
    }

