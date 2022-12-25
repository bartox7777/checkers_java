public class WhitePawn extends Pawn {
    WhitePawn(){
        super('O');
    }
    public int getStartingLine(){
        return 7; // white pawns start on line 7 (counting from 0)
    }
}