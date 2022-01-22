package game;

import helpers.DatabaseConnection;
import helpers.ErrorMessageLogger;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    private static Integer choice;
    private static Boolean loggedIn = true;
    private static String userId;
    private static ErrorMessageLogger error = new ErrorMessageLogger();
    private static Game game = new Game();

    public static void main(String[] args) throws IOException, SQLException, NoSuchAlgorithmException {
        new DatabaseConnection();
        do{
            System.out.println("-------------------------------------------------------");
            System.out.println(" ###### TIC TAC TOE GAME #####");
            System.out.println("\t\t\t\t 1. Register to the system");
            System.out.println("\t\t\t\t 2. Login to the system");
            System.out.println("\t\t\t\t 0. Exit");

            Scanner scan = new Scanner(System.in);
            choice = scan.nextInt();
            User user = new User();

            switch(choice){
                case 1:
                    if(user.registerUser()){
                        userId = user.getUserId();
                        loggedIn = true;
                    }
                    break;
                case 2:
                    if(!user.login()){
                        error.log("### INVALID CREDENTIALS ###");
                        user.login();
                    }
                    userId = user.getUserId();
                    loggedIn = true;
                    break;
                case 0:
                    System.exit(0);
                default:
                    error.log("!!! Invalid choice !!!");
            }

        }while (!loggedIn);
        if(loggedIn){
            do{
                System.out.println("### CHOOSE A DIFFICULTY LEVEL BELOW ###");
                System.out.println("\t\t\t\t 1. Beginner");
                System.out.println("\t\t\t\t 2. Advanced");

                System.out.println("\t\t\t\t 0. Exit");
                System.out.println("PLEASE ENTER YOUR CHOICE:");

                Scanner scan = new Scanner(System.in);
                choice = scan.nextInt();

                switch(choice){
                    case 1:
                        game.play();
                        break;
                    case 2:
                        break;
                    case 0:
                        System.exit(0);
                    default:
                        error.log("!!! Invalid choice !!!");
                }

            }while(!choice.equals(0));
        }
    }
}
