public class BlackPawn extends Pawn {
    BlackPawn(){
        super("x");
    }
    public int getPromoteToQueenLine(){
        return 7; // black pawns promote to queen on line 7 (counting from 0)
    }
}