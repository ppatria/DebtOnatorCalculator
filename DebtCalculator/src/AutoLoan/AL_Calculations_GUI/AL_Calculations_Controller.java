package AutoLoan.AL_Calculations_GUI;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;

import AutoLoan.AL_Home_GUI.Main;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class AL_Calculations_Controller {

    @FXML
    private ComboBox stateComboBox;
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
    private TextField salesTaxTextField;
    @FXML
    private TextField additionalFeesTextField;
    @FXML
    private TextField minPaymentPerMonthTextField;
    @FXML
    private TextField fullyPaidDateTextField;
    @FXML
    private TextField totalInterestCostTextField;
    @FXML
    private TextField totalCostTextField;
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
                             "Vehicle Model: " + vehicleModel + "\n");
    }

    public void initialize() {
    stateComboBox.setItems(FXCollections.observableArrayList(
        "AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", 
        "GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA",
        "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE",
        "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "OH", "OK",
        "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT",
        "VA", "WA", "WV", "WI", "WY"
        ));
    }

    public void handleCalculateButtonOnAction(ActionEvent event) {

        Pattern datePattern = Pattern.compile("^(0[1-9]|1[0-2])/(0[1-9]|[12][0-9]|3[01])/((19|20)\\d\\d)$");
        Matcher dateMatcher = datePattern.matcher(originalLoanDateTextField.getText());
    
        if(!dateMatcher.matches()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Original loan date shall only accept MM/DD/YYYY dates");
            alert.showAndWait();
            return;
        }
    
        Pattern loanTermPattern = Pattern.compile("^\\d{1,3}$");
        Matcher loanTermMatcher = loanTermPattern.matcher(loanTermTextField.getText());
    
        if(!loanTermMatcher.matches()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Loan term shall only accept up to 3 digits");
            alert.showAndWait();
            return;
        }
        if(stateComboBox.getValue() != null && !totalLoanAmountTextField.getText().isEmpty() &&
            !originalLoanDateTextField.getText().isEmpty() && !interestRateTextField.getText().isEmpty() && 
            !loanTermTextField.getText().isEmpty() && !downPaymentTextField.getText().isEmpty() && 
            !salesTaxTextField.getText().isEmpty() && !additionalFeesTextField.getText().isEmpty()) {
      
            double totalLoanAmount = Double.parseDouble(totalLoanAmountTextField.getText());
            double interestRate = Double.parseDouble(interestRateTextField.getText()) / 100;
            int loanTerm = Integer.parseInt(loanTermTextField.getText());
            double downPayment = Double.parseDouble(downPaymentTextField.getText());
            double salesTax = Double.parseDouble(salesTaxTextField.getText());
            double additionalFees = Double.parseDouble(additionalFeesTextField.getText());
            double interestPerMonth = interestRate / 12;
            
            double minPaymentPerMonth = (totalLoanAmount+salesTax+additionalFees-downPayment)*(interestPerMonth)/(1-(Math.pow((1+interestPerMonth),-loanTerm)));
            double totalInterestCost = (minPaymentPerMonth * loanTerm) - (totalLoanAmount + salesTax + additionalFees - downPayment);
            double totalExpectedCost = totalLoanAmount + salesTax + additionalFees + totalInterestCost - downPayment;
        
            minPaymentPerMonthTextField.setText(String.format("%.2f", minPaymentPerMonth));
            totalInterestCostTextField.setText(String.format("%.2f", totalInterestCost));
            totalCostTextField.setText(String.format("%.2f", totalExpectedCost));
        
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            LocalDate originalLoanDate = LocalDate.parse(originalLoanDateTextField.getText(), formatter);
        
            LocalDate expectedFullyPaidDate = originalLoanDate.plusMonths(loanTerm);
            fullyPaidDateTextField.setText(expectedFullyPaidDate.format(formatter));
      
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Please fill in all the fields.");
            alert.showAndWait();
        }
    }

    public void handleSaveButtonOnAction(ActionEvent event) {
        try {
            Path path = Paths.get("output.txt");
            FileWriter writer = new FileWriter(new File(path.toUri()));

            writer.write("Minimum Payment per Month: " + minPaymentPerMonthTextField.getText() + "\n");
            writer.write("Total Interest Cost: " + totalInterestCostTextField.getText() + "\n");
            writer.write("Total Expected Cost: " + totalCostTextField.getText() + "\n");
            writer.write("Expected Fully Paid Date: " + fullyPaidDateTextField.getText() + "\n");
            writer.write("Loan term (Months): " + loanTermTextField.getText() + "\n");
            writer.write("Down payment: " + downPaymentTextField.getText() + "\n");

            writer.close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Save");
            alert.setHeaderText(null);
            alert.setContentText("Information saved.");
            alert.showAndWait();

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("An error occurred while trying to save the information.");
            alert.showAndWait();
        }
    }


    public void handleExitButtonOnAction(ActionEvent event) {
        System.exit(0);
    }

}