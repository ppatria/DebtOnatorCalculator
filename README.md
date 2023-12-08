# DebtOnator Calculator 
## The AutoLoan Calculator

Version 1.0 of this project was completed on 12/07/2023 for the class CSC1061681 Computer Sci II: (Language) taught by Patrick McDougle.

The AutoLoan Calculator lets users provide their details such as state, total loan amount, original loan date, interest rate, loan term, down payment, sales tax, and additional fees to calculate the minimum payment per month, the date when it will be fully paid, total interest cost, and total cost.

This project is implemented using JavaFX for the GUI, and plain old text files to store users' information.

## File Breakdown

### AL_Home_GUI

1. `Main.java:` This file is the entry point of the application. It displays the Home GUI where users can log in to their account.

2. `AL_Home_Controller.java:` This file is responsible for handling users actions at the Home GUI. If a user is logging in, it verifies their credentials and transitions to the Calculations GUI. If the user is a new user, it transitions to the New User GUI.

### AL_New_User_GUI

1. `AL_New_User_Controller.java:` This file is responsible for providing functionality to generate new user files and store user information such as username, password, and vehicle information.

### AL_Calculations_GUI

1. `AL_Calculations_Controller.java:` This file is responsible for providing functionality on the application's calculation screen. It takes users' inputs and calculates the information related to their auto loan. It also allows the user to save their information.


## Getting Started

This java project was created and setup to be used with Visual Studio Code.

### Installations
This project was created with the use of:
-  Java SDK - Version 17.0.8
-  JavaFX - 17.0.8
-  Visual Studio Code - 1.84.2
  
### Getting Repository
1. Clone the repository to your local system.
2. Open the project in your favorite Java IDE (like IntelliJ, Eclipse, etc.)
3. Run the `Main.java` file under `AutoLoan/AL_Home_GUI/`.
4. Use the GUI to either create a new user or log in with existing credentials. Provide the necessary details to calculate your loan.

## Contact

-  Prodeo Et Patria - prodeopatria51@gmail.com
-  Jacob Conroy - jconroy102@gmail.com
  
## Future Contributions
Feel free to contribute to this project by creating a fork and submit a Pull Request. You can also raise issues if you find any problems or have suggestions for improvements.
