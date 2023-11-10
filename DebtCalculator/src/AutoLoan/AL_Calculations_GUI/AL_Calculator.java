import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CarLoanApplication {

    public static void main(String[] args) {
        try {
            // Read inputs from file
            BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
            double downPayment = Double.parseDouble(reader.readLine());
            double interestRate = Double.parseDouble(reader.readLine());
            double salesTax = Double.parseDouble(reader.readLine());
            int loanTerm = Integer.parseInt(reader.readLine());

            // Close the file reader
            reader.close();

            // Perform calculations
            double loanAmount = 0; // Initialize loan amount to be calculated

            // Calculate loan amount after down payment
            // (assuming down payment is applied before sales tax)
            loanAmount = calculateLoanAmount(downPayment, salesTax);

            // Calculate monthly payment
            double monthlyPayment = calculateMonthlyPayment(loanAmount, interestRate, loanTerm);

            // Display results
            System.out.println("Loan Amount: $" + loanAmount);
            System.out.println("Monthly Payment: $" + monthlyPayment);

        } catch (IOException e) {
            System.err.println("Error reading input file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Invalid input format: " + e.getMessage());
        }
    }

    // Method to calculate loan amount after down payment and sales tax
    public static double calculateLoanAmount(double downPayment, double salesTax) {
        // Assuming sales tax is applied after down payment
        double loanAmount = downPayment / (1 + (salesTax / 100));
        return loanAmount;
    }

    // Method to calculate monthly payment
    public static double calculateMonthlyPayment(double loanAmount, double interestRate, int loanTerm) {
        double monthlyInterestRate = interestRate / 12 / 100;
        int numberOfPayments = loanTerm * 12;

        double monthlyPayment = (loanAmount * monthlyInterestRate) / (1 - Math.pow(1 + monthlyInterestRate, -numberOfPayments));
        return monthlyPayment;
    }
}




