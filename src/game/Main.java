package game;

import helpers.DatabaseConnection;
import helpers.ErrorMessageLogger;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    private static Integer choice;
    private static Boolean loggedIn = false;
    private static String userId;
    private static ErrorMessageLogger error = new ErrorMessageLogger();

    public static void main(String[] args) throws IOException, SQLException {
        new DatabaseConnection();
        do{
            System.out.println("##################################################");
            System.out.println(" ###### TIC TAC TOE GAME #####");
            System.out.println("\t\t\t\t 1. Register to the system");
            System.out.println("\t\t\t\t 2. Login to the system");
            System.out.println("\t\t\t\t 0. Exit");

            Scanner scan = new Scanner(System.in);
            choice = scan.nextInt();
        }while (choice != 0);
    }
}
