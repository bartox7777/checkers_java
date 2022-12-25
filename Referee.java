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

    private char getSymbolOfOpponent(){
        if (currentPlayer==player.WHITE){
            return '@';
        } else {
            return 'O';
        }
    }

    // move ONLY in format "a3 b4" converted to coordinates [0][2] [1][3]
    private int[][] convertMoveToCoordinates(String move){
        int[][] coordinates = new int[2][2];
        coordinates[0][0] = move.charAt(1)-49;
        coordinates[0][1] = move.charAt(0)-97;
        coordinates[1][0] = move.charAt(4)-49;
        coordinates[1][1] = move.charAt(3)-97;
        return coordinates;
    }

    // check if move is valid when user wants to take a pawn
    // move is in format "a3 b4x c5 [...]"; a3 is the pawn to move, b4x is the pawn to take, c5 is the destination
    private boolean isMoveWithTakeValid(String move){
        char [][] boardCopy = new char[8][8];
        for (int i=0; i<8; i++){
            for (int j=0; j<8; j++){
                boardCopy[i][j] = board.getBoard()[i][j];
            }
        }

        // this ifology is bad idea, TODO: refactor
        Boolean validMove = false;
        while (true){
            if (move.length()>=9){
                    if (move.charAt(5)=='x'){
                        int[][] coordinates = convertMoveToCoordinates(move.substring(0,5));
                        int x1 = coordinates[0][0];
                        int y1 = coordinates[0][1];
                        int x2 = coordinates[1][0];
                        int y2 = coordinates[1][1];
                        // char[][] board = this.board.getBoard();
                        if (boardCopy[x1][y1]==getSymbolOfCurrentPlayer() && boardCopy[x2][y2]==getSymbolOfOpponent()){
                            coordinates = convertMoveToCoordinates(move.substring(4)); // get coordinates of destination; only coordinates [1][0] and [1][1] are used
                            int x3 = coordinates[1][0];
                            int y3 = coordinates[1][1];
                            if (boardCopy[x3][y3]==' '){
                                if (Math.abs(x1-x3)==2 && Math.abs(y1-y3)==2){ //  TODO: take can be done backwards? One take can be done forward and one backward in "one move".
                                    validMove = true;
                                    move = move.substring(7); // remove first part of move, eg. "a3 b4x c5" -> "c5"
                                    boardCopy[x1][y1]=' '; // remove pawn that was moved
                                    boardCopy[x2][y2]=' '; // remove pawn that was taken
                                    boardCopy[x3][y3]=getSymbolOfCurrentPlayer(); // move pawn to destination
                                    System.out.println(move);
                                    if (!move.contains("x")){
                                        if (move.length()!=2){
                                            validMove = false;
                                        }
                                        break;
                                    }
                                } else {validMove = false; break;}
                            } else {validMove = false; break;}
                        } else {validMove = false; break;}
                    } else {validMove = false; break;}
            } else {validMove = false; break;}
        }
        return validMove;
    }

    public boolean isMoveValid(String move){
        // TODO: check if move is inside the board
        if (move.length()!=5){
            if (move.contains("x")){ // user wants to take a pawn(s)
                return isMoveWithTakeValid(move);
            }
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
    // move is in format "a3 b4" or "a3 b4x c5 [...]"
    public Board makeMove(String move){
        if (!move.contains("x")){ // move without taking a pawn
            int[][] coordinates = convertMoveToCoordinates(move);
            int x1 = coordinates[0][0];
            int y1 = coordinates[0][1];
            int x2 = coordinates[1][0];
            int y2 = coordinates[1][1];
            char[][] board = this.board.getBoard();
            board[x2][y2]=board[x1][y1];
            board[x1][y1]=' ';
            this.board.setBoard(board);
        } else{ // move with taking a pawn
            while (move.length() >= 9){
                int[][] coordinates = convertMoveToCoordinates(move);
                int x1 = coordinates[0][0];
                int y1 = coordinates[0][1];
                int x2 = coordinates[1][0];
                int y2 = coordinates[1][1];
                coordinates = convertMoveToCoordinates(move.substring(4)); // get coordinates of destination; only coordinates [1][0] and [1][1] are used
                int x3 = coordinates[1][0];
                int y3 = coordinates[1][1];
                char[][] board = this.board.getBoard();
                board[x3][y3]=board[x1][y1];
                board[x1][y1]=' ';
                board[x2][y2]=' ';
                move = move.substring(7); // remove first part of move, eg. "a3 b4x c5" -> "c5"
            }
        }
        return this.board;
    }
}
