package AutoLoan.AL_Calculations_GUI;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import AutoLoan.AL_Home_GUI.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class AL_Calculations_Controller {

    @FXML
    private TextField stateTextField;
    @FXML
    private TextField totalLoanAmountTextField;
    @FXML
    private TextField originalLoanDateTextField;
    @FXML
    private TextField interestRateTextField;
    @FXML
    private TextField loanTermTextField;
    @FXML
    private TextField downPaymentTextField;
    @FXML
    private Label vehicleInfoLabel;
    @FXML
    private Button exitButton;
    @FXML
    private Button calculateButton;
    @FXML
    private Button saveButton;

    public void setUserData(String username, String vehicleYear, String vehicleMake, String vehicleModel) {
    vehicleInfoLabel.setText("Username: " + username + "\n" + 
                             "Vehicle Year: " + vehicleYear + "\n" +
                             "Vehicle Make: " + vehicleMake + "\n" +
                             "Vehicle Model: " + vehicleModel);
}

}