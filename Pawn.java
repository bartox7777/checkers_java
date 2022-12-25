public abstract class Pawn {
    private char symbol;
    private Boolean isQueen;
    Pawn(char symbol){
        setSymbol(symbol);
        setQueen(false);
    }
    public abstract int getStartingLine();
    public char getSymbol(){
        return symbol;
    }
    public void setSymbol(char symbol){
        this.symbol=symbol;
    }
    public Boolean isQueen(){
        return isQueen;
    }
    public void setQueen(Boolean isQueen){
        this.isQueen=isQueen;
    }
}
