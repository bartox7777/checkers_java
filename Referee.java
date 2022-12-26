import java.util.Scanner;

public class Referee {
    public static enum player {WHITE, BLACK};
    private player currentPlayer;
    private Board board;
    private String whitePawnSymbol = new WhitePawn().getSymbol();
    private String blackPawnSymbol = new BlackPawn().getSymbol();

    private Board copyBoard(final Board board) throws CloneNotSupportedException{
        Board boardCopy = new Board();
        for (int i=0; i<8; i++){
            for (int j=0; j<8; j++){
                boardCopy.getBoard()[i][j] = board.getBoard()[i][j].clone();
            }
        }
        return boardCopy;
    }

    public Boolean ifSomeoneWon(){
        int whitePawns = 0;
        int blackPawns = 0;
        for (int i=0; i<8; i++){
            for (int j=0; j<8; j++){
                if (board.getBoard()[i][j].getSymbol().equals(whitePawnSymbol)){
                    whitePawns++;
                }
                if (board.getBoard()[i][j].getSymbol().equals(blackPawnSymbol)){
                    blackPawns++;
                }
            }
        }
        if (whitePawns == 0){
            System.out.println("("+ blackPawnSymbol +") Black won!");
            return true;
        }
        if (blackPawns == 0){
            System.out.println("("+ whitePawnSymbol +") White won!");
            return true;
        }
        return false;
    }

    public Referee(final Board board) throws CloneNotSupportedException{
        this.board=copyBoard(board);
        currentPlayer=player.WHITE;
    }

    public void changePlayer(){
        if (currentPlayer.equals(player.WHITE)){
            currentPlayer=player.BLACK;
        } else {
            currentPlayer=player.WHITE;
        }
    }

    private void transformToQueensIfNeccessary(){
        for (int i=0; i<8; i++){
            if (board.getBoard()[0][i].getPromoteToQueenLine() == 0){
                board.getBoard()[0][i].setQueen();
            }
            if (board.getBoard()[7][i].getPromoteToQueenLine() == 7){
                board.getBoard()[7][i].setQueen();
            }
        }
    }

    public String getMove(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("("+getSymbolOfCurrentPlayer()+") Enter move: ");
        return scanner.nextLine();
    }

    private String getSymbolOfCurrentPlayer(){
        if (currentPlayer.equals(player.WHITE)){
            return whitePawnSymbol;
        } else {
            return blackPawnSymbol;
        }
    }

    private String getSymbolOfOpponent(){
        if (currentPlayer.equals(player.WHITE)){
            return blackPawnSymbol;
        } else {
            return whitePawnSymbol;
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
    private boolean isMoveWithTakeValid(String move) throws CloneNotSupportedException{
        Pawn [][] boardCopy = copyBoard(board).getBoard();
        while (true){
            if (move.length()<9 || move.charAt(5)!='x'){
                return false;
            }
                int[][] coordinates = convertMoveToCoordinates(move);
                int x1 = coordinates[0][0];
                int y1 = coordinates[0][1];
                int x2 = coordinates[1][0];
                int y2 = coordinates[1][1];
                coordinates = convertMoveToCoordinates(move.substring(4)); // get coordinates of destination; only coordinates [1][0] and [1][1] are used
                int x3 = coordinates[1][0];
                int y3 = coordinates[1][1];
                if (x1<0 || x1>7 || y1<0 || y1>7 || x2<0 || x2>7 || y2<0 || y2>7 || x3<0 || x3>7 || y3<0 || y3>7){
                    return false;
                }
                // check if pawn to move is of current player, pawn to take is of opponent and destination is empty
                if (!boardCopy[x1][y1].getSymbol().equalsIgnoreCase(getSymbolOfCurrentPlayer()) || !boardCopy[x2][y2].getSymbol().equalsIgnoreCase(getSymbolOfOpponent()) || !boardCopy[x3][y3].getSymbol().equals(" ")){
                    return false;
                }
                if (Math.abs(x1-x3)!=2 || Math.abs(y1-y3)!=2){

                    // if queen
                    if (boardCopy[x1][y1].isQueen()){
                        // queen can move diagonally as many fields as she wants
                        if (Math.abs(x1-x2)==Math.abs(y1-y2)){
                            // there is exacly one opponent's pawn between queen and destination
                            int opponentPawnsBetweenQueenAndDestination = 0;
                            for (int x1i=x1+Integer.signum(x3-x1), y1i=y1+Integer.signum(y3-y1); x1i!=x3 && y1i!=y3; x1i+=Integer.signum(x3-x1), y1i+=Integer.signum(y3-y1)){
                                if (!boardCopy[x1i][y1i].getSymbol().equals(" ")){
                                    if (boardCopy[x1i][y1i].getSymbol().equalsIgnoreCase(getSymbolOfOpponent())){
                                        opponentPawnsBetweenQueenAndDestination++;
                                    } else {
                                        return false;
                                    }
                                }
                            }
                            if (opponentPawnsBetweenQueenAndDestination!=1){
                                return false;
                            }
                        }
                    } else{
                        return false;
                    }
                }
                move = move.substring(7); // remove first part of move, eg. "a3 b4x c5" -> "c5"
                boardCopy[x3][y3]=boardCopy[x1][y1]; // move pawn to destination
                boardCopy[x1][y1]=new EmptyPlace(); // remove pawn that was moved
                boardCopy[x2][y2]=new EmptyPlace(); // remove pawn that was taken
                if (!move.contains("x")){
                    if (move.length()!=2){
                        return false;
                    }
                    break;
                }
        }
        return true;
    }

    public boolean isMoveValid(String move) throws CloneNotSupportedException{
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

        if (x1<0 || x1>7 || y1<0 || y1>7 || x2<0 || x2>7 || y2<0 || y2>7){
            return false;
        }
        Pawn[][] board = this.board.getBoard();
        if (board[x1][y1].getSymbol().equalsIgnoreCase(getSymbolOfCurrentPlayer()) ){
            if (board[x2][y2].getSymbol().equals(" ")){
                if (Math.abs(x1-x2)==1 && Math.abs(y1-y2)==1){
                    return true;
                }

                // if queen
                if (board[x1][y1].isQueen()){
                    // queen can move diagonally as many fields as she wants
                    if (Math.abs(x1-x2)==Math.abs(y1-y2)){
                        // check if there are no pawns between start and destination
                        for (int x1i=x1+Integer.signum(x2-x1), y1i=y1+Integer.signum(y2-y1); x1i!=x2 && y1i!=y2; x1i+=Integer.signum(x2-x1), y1i+=Integer.signum(y2-y1)){
                            if (!board[x1i][y1i].getSymbol().equals(" ")){
                                return false;
                            }
                        }
                        return true;
                    }
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
            Pawn[][] board = this.board.getBoard();
            board[x2][y2]=board[x1][y1];
            board[x1][y1]=new EmptyPlace();
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
                Pawn[][] board = this.board.getBoard();
                board[x3][y3]=board[x1][y1];
                board[x1][y1]=new EmptyPlace();
                board[x2][y2]=new EmptyPlace();
                move = move.substring(7); // remove first part of move, eg. "a3 b4x c5" -> "c5"
            }
        }
        transformToQueensIfNeccessary();
        return this.board;
    }
}
