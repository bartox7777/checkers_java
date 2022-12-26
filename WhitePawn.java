public class WhitePawn extends Pawn { // heritance
    WhitePawn(){
        super("o");
    }
    public int getPromoteToQueenLine(){
        return 0; // white pawns promote to queen on line 0 (counting from 0)
    }
}