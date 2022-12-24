public class Game{
    public static void main(String[] args){
        Game game = new Game();
        game.start();
    }

    public void start(){
        Board board = new Board();
        Referee referee = new Referee(board);
        board.draw();
        while (true){
            String move = referee.getMove();
            if (referee.isMoveValid(move)){
                board = referee.makeMove(move);
                board.draw();
                referee.changePlayer();
            } else {
                System.out.println("Invalid move!");
            }
        }
    }
}