package game;

import helpers.ErrorMessageLogger;
import helpers.SuccessMessageLogger;

import java.util.Random;
import java.util.Scanner;

public class Game {
    private static char[][] gameBoard = {{' ',' ',' '},{' ',' ',' '},{' ',' ',' '}};
    private static Scanner scan = new Scanner(System.in);
    private static String userInput;
    SuccessMessageLogger success = new SuccessMessageLogger();
    ErrorMessageLogger error = new ErrorMessageLogger();
    private void drawBoard(){
        for(int i = 0;i<gameBoard.length;i++){
            for(int j = 0;j<gameBoard[i].length;j++){
                if(j!= gameBoard.length -1 ){
                    System.out.print(gameBoard[i][j] + " | ");
                }
                else{
                    System.out.print(gameBoard[i][j]);
                }
            }
            System.out.println();
            if(i!= gameBoard.length -1){
                System.out.println("--+--+--");
            }
        }
    }

    private Boolean isValidMove(String position){
        switch(position){
            case "1":
                return gameBoard[0][0] == ' ';
            case "2":
                return gameBoard[0][1] == ' ';
            case "3":
                return gameBoard[0][2] == ' ';
            case "4":
                return gameBoard[1][0] == ' ';
            case "5":
                return gameBoard[1][1] == ' ';
            case "6":
                return gameBoard[1][2] == ' ';
            case "7":
                return gameBoard[2][0] == ' ';
            case "8":
                return gameBoard[2][1] == ' ';
            case "9":
                return gameBoard[2][2] == ' ';
            default:
                return false;
        }
    }

    private void playerTurn(){
        while(true){
            System.out.println("Where would you like to play? (1-9)");
            userInput = scan.nextLine();
            if(isValidMove(userInput)){
                break;
            }
            else{
                error.log(userInput+" is not a valid move");
            }
        }
        placeMove(userInput,'X');
    }

    private void computerTurn(){
        Random random = new Random();
        int computerMove;
        while(true){
            computerMove = random.nextInt(9) + 1;
            if(isValidMove(Integer.toString(computerMove))){
                break;
            }
        }
        success.log("Computer chose: "+computerMove);
        placeMove(Integer.toString(computerMove),'O');
    }
    private Boolean isGameFinished(){
        for(int i=0;i< gameBoard.length;i++){
            for(int j=0;j<gameBoard[i].length;j++){
                if(gameBoard[i][j] == ' '){
                    return false;
                }
            }
        }
        return true;
    }

    private void placeMove(String position, char symbol){
        switch(position){
            case "1":
                gameBoard[0][0] = symbol;
                break;
            case "2":
                gameBoard[0][1] = symbol;
                break;
            case "3":
                gameBoard[0][2] = symbol;
                break;
            case "4":
                gameBoard[1][0] = symbol;
                break;
            case "5":
                gameBoard[1][1] = symbol;
                break;
            case "6":
                gameBoard[1][2] = symbol;
                break;
            case "7":
                gameBoard[2][0] = symbol;
                break;
            case "8":
                gameBoard[2][1] = symbol;
                break;
            case "9":
                gameBoard[2][2] = symbol;
                break;
            default:
                error.log("An error occured");
                System.exit(-1);
        }
    }

    public void play(){
           success.log("### BEGINNER LEVEL ###");
            drawBoard();
            while(true){
                playerTurn();
                if(isGameFinished()){
                    break;
                }
                computerTurn();
                if(isGameFinished()){
                    break;
                }
                drawBoard();
            }
    }
}
