public class Game{
    public static void main(String[] args){
        Game game = new Game();
        game.start();
    }

    public void start(){
        Board board = new Board();
        Referee referee = new Referee(board);
        System.out.print("\033[H\033[2J");
        board.draw();
        while (true){
            String move = referee.getMove();
            if (referee.isMoveValid(move)){
                board = referee.makeMove(move);
                // clear console
                System.out.print("\033[H\033[2J");
                board.draw();
                referee.changePlayer();
            } else {
                System.out.println("Invalid move!");
            }
        }
    }
}