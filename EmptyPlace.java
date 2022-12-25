public class EmptyPlace extends Pawn {
    EmptyPlace(){
        super(' ');
    }
    public int getStartingLine(){
        return -1; // empty places don't start on any line
    }
}
