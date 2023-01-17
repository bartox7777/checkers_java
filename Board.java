public class Board {
    // the board is a 2D array of Pawns
    // private Pawn[][] board = new Pawn[][]{ // polymorphism
    //     {new EmptyPlace(), new BlackPawn(), new EmptyPlace(), new BlackPawn(), new EmptyPlace(), new BlackPawn(), new EmptyPlace(), new BlackPawn()},
    //     {new BlackPawn(), new EmptyPlace(), new BlackPawn(), new EmptyPlace(), new BlackPawn(), new EmptyPlace(), new BlackPawn(), new EmptyPlace()},
    //     {new EmptyPlace(), new BlackPawn(), new EmptyPlace(), new BlackPawn(), new EmptyPlace(), new BlackPawn(), new EmptyPlace(), new BlackPawn()},
    //     {new EmptyPlace(), new EmptyPlace(), new EmptyPlace(), new EmptyPlace(), new EmptyPlace(), new EmptyPlace(), new EmptyPlace(), new EmptyPlace()},
    //     {new EmptyPlace(), new EmptyPlace(), new EmptyPlace(), new EmptyPlace(), new EmptyPlace(), new EmptyPlace(), new EmptyPlace(), new EmptyPlace()},
    //     {new WhitePawn(), new EmptyPlace(), new WhitePawn(), new EmptyPlace(), new WhitePawn(), new EmptyPlace(), new WhitePawn(), new EmptyPlace()},
    //     {new EmptyPlace(), new WhitePawn(), new EmptyPlace(), new WhitePawn(), new EmptyPlace(), new WhitePawn(), new EmptyPlace(), new WhitePawn()},
    //     {new WhitePawn(), new EmptyPlace(), new WhitePawn(), new EmptyPlace(), new WhitePawn(), new EmptyPlace(), new WhitePawn(), new EmptyPlace()}
    // };
    private Pawn[][] board = new Pawn[][]{ // polymorphism
        {new EmptyPlace(), new BlackPawn(), new EmptyPlace(), new BlackPawn(), new EmptyPlace(), new EmptyPlace(), new EmptyPlace(), new BlackPawn()},
        {new BlackPawn(), new EmptyPlace(), new BlackPawn(), new EmptyPlace(), new WhitePawn(), new EmptyPlace(), new BlackPawn(), new EmptyPlace()},
        {new EmptyPlace(), new BlackPawn(), new EmptyPlace(), new EmptyPlace(), new EmptyPlace(), new BlackPawn(), new EmptyPlace(), new BlackPawn()},
        {new EmptyPlace(), new EmptyPlace(), new EmptyPlace(), new EmptyPlace(), new EmptyPlace(), new EmptyPlace(), new EmptyPlace(), new EmptyPlace()},
        {new EmptyPlace(), new EmptyPlace(), new EmptyPlace(), new EmptyPlace(), new EmptyPlace(), new EmptyPlace(), new EmptyPlace(), new EmptyPlace()},
        {new WhitePawn(), new EmptyPlace(), new WhitePawn(), new EmptyPlace(), new WhitePawn(), new EmptyPlace(), new WhitePawn(), new EmptyPlace()},
        {new EmptyPlace(), new WhitePawn(), new EmptyPlace(), new WhitePawn(), new EmptyPlace(), new WhitePawn(), new EmptyPlace(), new WhitePawn()},
        {new WhitePawn(), new EmptyPlace(), new WhitePawn(), new EmptyPlace(), new WhitePawn(), new EmptyPlace(), new WhitePawn(), new EmptyPlace()}
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
    public Pawn[][] getBoard(){
        return board;
    }
    public void setBoard(Pawn[][] board){
        this.board = board;
    }

    // helper methods to draw the board
    private void drawLine(){
        for (int i=0; i<8; i++){
            System.out.print("----");
        }
        System.out.print("-");
    }
    private void drawPlacesInRow(Pawn[] boardsRow, int number){
        for (int i=0; i<8; i++){
            if (i!=7)
                System.out.print("| "+ boardsRow[i].getSymbol() +" ");
            else
                System.out.print("| "+ boardsRow[i].getSymbol() +" | "+ number);
        }
    }
}
