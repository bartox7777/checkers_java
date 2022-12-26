public abstract class Pawn implements Cloneable{ // abstract class
    private String symbol;
    private Boolean isQueen;
    Pawn(String symbol){
        setSymbol(symbol.toLowerCase());
        isQueen=false;
    }
    public abstract int getPromoteToQueenLine();
    public String getSymbol(){
        return symbol;
    }
    public void setSymbol(String symbol){
        this.symbol=symbol;
    }
    public Boolean isQueen(){
        return isQueen;
    }
    public void setQueen(){
        this.isQueen=true;
        setSymbol(getSymbol().toUpperCase());
    }

    public Pawn clone() throws CloneNotSupportedException{
        return (Pawn) super.clone();
    }
}
