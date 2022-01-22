package game;

public class Game {
    private static char[][] gameBoard = {{' ',' ',' '},{' ',' ',' '},{' ',' ',' '}};
    private static void drawBoard(){
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
    public void play(){
        drawBoard();
    }
}
