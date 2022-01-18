package validators;

import helpers.ErrorMessageLogger;
import helpers.SuccessMessageLogger;
import services.UserService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class UserValidator{
    private static UserService userService = new UserService();
    private static Scanner scan = new Scanner(System.in);
    private static ErrorMessageLogger error = new ErrorMessageLogger();
    private static SuccessMessageLogger success = new SuccessMessageLogger();
    private static String userEmail;
    private static String userPassword;

    public String checkEmail(String email) throws IOException, SQLException {
        String[] splitted = email.split("@");
        List<String> emailSplitted = new ArrayList<String>(Arrays.asList(splitted));

        if(email.length() < 5 || !emailSplitted.contains("gmail.com")){
            error.log("Error: Email must be valid");
            userEmail = generateInput("email");
        }
        else if(userService.checkEmail(email)){
            error.log("Error: Email already exists");
            userEmail = generateInput("email");
        }
        else{
            userEmail = email;
        }
        return userEmail;
    }

    public String checkPassword(String password) throws IOException, SQLException {
        if(password.length() < 5){
            error.log("Error: Password must be at least 5 characters");
            userPassword = generateInput("password");
        }
        else{
            userPassword = password;
        }
        return userPassword;
    }

    public String generateInput(String fieldName) throws IOException, SQLException {
        System.out.println("\t\t - ENTER YOUR "+fieldName.toUpperCase());
        String value = "";
        switch(fieldName){
            case "email":
                userEmail = scan.nextLine().toLowerCase();
                checkEmail(userEmail);
                value = userEmail;
                break;
            case "password":
                userPassword = scan.nextLine();
                checkPassword(userPassword);
                value = userPassword;
                break;
            default:
                error.log("Invalid field");
        }
        return value;
    }
}
