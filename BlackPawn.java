public abstract class BlackPawn extends Pawn {
    BlackPawn(){
        super('X');
    }
    public int getStartingLine(){
        return 0; // black pawns start on line 0 (counting from 0)
    }
}