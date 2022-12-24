import java.util.Scanner;

public class Referee {
    private enum player {WHITE, BLACK};
    private player currentPlayer;
    private Board board;

    public Referee(Board board){
        this.board=board;
        currentPlayer=player.WHITE;
    }

    public void changePlayer(){
        if (currentPlayer==player.WHITE){
            currentPlayer=player.BLACK;
        } else {
            currentPlayer=player.WHITE;
        }
    }

    public String getMove(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("("+getSymbolOfCurrentPlayer()+") Enter move: ");
        return scanner.nextLine();
    }

    private char getSymbolOfCurrentPlayer(){
        if (currentPlayer==player.WHITE){
            return 'O';
        } else {
            return '@';
        }
    }

    // move in format "a3 b4" converted to coordinates [0][2] [1][3]
    private int[][] convertMoveToCoordinates(String move){
        int[][] coordinates = new int[2][2];
        coordinates[0][0] = move.charAt(1)-49;
        coordinates[0][1] = move.charAt(0)-97;
        coordinates[1][0] = move.charAt(4)-49;
        coordinates[1][1] = move.charAt(3)-97;
        return coordinates;
    }

    public boolean isMoveValid(String move){
        if (move.length()!=5){
            return false;
        }
        int[][] coordinates = convertMoveToCoordinates(move);
        int x1 = coordinates[0][0];
        int y1 = coordinates[0][1];
        int x2 = coordinates[1][0];
        int y2 = coordinates[1][1];
        char[][] board = this.board.getBoard();
        if (board[x1][y1]==getSymbolOfCurrentPlayer()){
            if (board[x2][y2]==' '){
                if (Math.abs(x1-x2)==1 && Math.abs(y1-y2)==1){
                    return true;
                }
            }
        }
        return false;
    }

    // make a move on the board
    // return new state of the board
    // move is in format "a2 a3"
    public Board makeMove(String move){
        int[][] coordinates = convertMoveToCoordinates(move);
        int x1 = coordinates[0][0];
        int y1 = coordinates[0][1];
        int x2 = coordinates[1][0];
        int y2 = coordinates[1][1];
        char[][] board = this.board.getBoard();
        board[x2][y2]=board[x1][y1];
        board[x1][y1]=' ';
        this.board.setBoard(board);
        return this.board;
    }
}
