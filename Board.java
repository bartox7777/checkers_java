import java.lang.Math;

public class Board {
    // the board
    // @ - black pawn
    // O - white pawn
    private char[][] board = new char[][] {
        {' ', '@', ' ', '@', ' ', '@', ' ', '@'},
        {'@', ' ', '@', ' ', '@', ' ', '@', ' '},
        {' ', '@', ' ', '@', ' ', '@', ' ', '@'},
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        {'O', ' ', 'O', ' ', 'O', ' ', 'O', ' '},
        {' ', 'O', ' ', 'O', ' ', 'O', ' ', 'O'},
        {'O', ' ', 'O', ' ', 'O', ' ', 'O', ' '}
    };

    // method to draw the board
    public void draw(){
        for (int i=0; i<8; i++){
            drawLine();
            System.out.println();
            drawPlacesInRow(board[i], i+1);
            System.out.println();
            if (i==7){
                drawLine();
            }
        }
        System.out.println();
        for (int i=0; i<8; i++){
            System.out.print("  "+(char)(i+97)+" ");
        }
        System.out.println();
    }

    // getters and setters
    public char[][] getBoard(){
        return board;
    }
    public void setBoard(char[][] board){
        this.board = board;
    }

    // helper methods to draw the board
    private void drawLine(){
        for (int i=0; i<8; i++){
            System.out.print("****");
        }
        System.out.print("*");
    }
    private void drawPlacesInRow(char[] boardsRow, int number){
        for (int i=0; i<8; i++){
            if (i!=7)
                System.out.print("* "+ boardsRow[i] +" ");
            else
                System.out.print("* "+ boardsRow[i] +" * "+ number);
        }
    }
}
