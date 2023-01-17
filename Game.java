public class Game{
    public static void main(String[] args) throws CloneNotSupportedException{
        Game game = new Game();
        game.start();
    }

    public void start() throws CloneNotSupportedException{
        Board board = new Board();
        Referee referee = new Referee(board);
        System.out.print("\033[H\033[2J");
        board.draw();
        while (true){
            String move = referee.getMove(); // take move from one of players | referee decides from whom
            if (referee.isMoveValid(move)){ // check if given move is valid
                board = referee.makeMove(move);
                // clear console
                System.out.print("\033[H\033[2J");
                board.draw();
                if (referee.ifSomeoneWon()){
                    System.out.println("Game over!");
                    break;
                }
                referee.changePlayer();
            } else {
                System.out.println("Invalid move!");
            }
        }
    }
}