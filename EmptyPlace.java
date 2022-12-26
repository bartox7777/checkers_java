public class EmptyPlace extends Pawn {
    EmptyPlace(){
        super(" ");
    }
    public int getPromoteToQueenLine(){
        return -1; // empty places don't start on any line
    }
}
