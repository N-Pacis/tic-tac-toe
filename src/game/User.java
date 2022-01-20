package game;

import helpers.ErrorMessageLogger;
import helpers.SuccessMessageLogger;
import helpers.UniqueRandomCharacters;
import services.UserService;
import validators.UserValidator;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Scanner;

public class User {
    UserValidator validator = new UserValidator();
    UserService userService = new UserService();
    private static Scanner scan = new Scanner(System.in);

    private static String email;
    private static String password;
    private String userId;
    private static ErrorMessageLogger error = new ErrorMessageLogger();
    private static SuccessMessageLogger success = new SuccessMessageLogger();

    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest msgDigest = MessageDigest.getInstance("SHA-256");
        byte[] hash = msgDigest.digest(password.getBytes(StandardCharsets.UTF_8));
        BigInteger no= new BigInteger(1,hash);
        StringBuilder hexStr = new StringBuilder(no.toString(16));
        while(hexStr.length() < 32){
            hexStr.insert(0,'0');
        }
        return hexStr.toString();
    }

    public Boolean registerUser() throws IOException, SQLException, NoSuchAlgorithmException {
        UniqueRandomCharacters randomCharacters = new UniqueRandomCharacters();
        String[] fields= {"email","password"};
        System.out.println("### \t\tPLEASE FILL THE REQUIRED INFORMATION ###");
        for(String field : fields){
            generateInput(field,"register");
        }
        String userId = randomCharacters.random();
        String hashed_password = hashPassword(password);
        userService.registerUser(userId,email,hashed_password);
        success.log("### REGISTERED SUCCESSFULLY ###");
        setUserId(userId);
        return true;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public Boolean login() throws IOException, SQLException, NoSuchAlgorithmException {
        String[] fields = {"email","password"};
        System.out.println("### \t\t ENTER YOUR ACCOUNT");
        for(String field: fields){
            generateInput(field,"login");
        }
        String hashed_password = hashPassword(password);
        String checkCredentials = userService.checkCredentials(email,hashed_password);
        if(!checkCredentials.isEmpty()){
            setUserId(checkCredentials);
            success.log("### LOGGED IN SUCCESSFULLY ###");
            return true;
        }
        return false;
    }

    private void generateInput(String fieldName,String source) throws IOException, SQLException {
        System.out.println("\t\t - ENTER YOUR "+fieldName.toUpperCase());
        switch(fieldName){
            case "email":
                email = scan.nextLine().toLowerCase();
                if(source.equals("register")){
                    email = validator.checkEmail(email);
                }
                break;
            case "password":
                password = scan.nextLine();
                password = validator.checkPassword(password);
                break;
            default:
                error.log("Invalid field");
        }
    }
}
