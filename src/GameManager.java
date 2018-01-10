import java.awt.Point;
import java.util.Set;

public class GameManager {
    private Board board;
    private Logic logic;
    private Player[] players;
    private Printer printer;
    private int tie;
    private static final int NUM_PLAYERS = 2;
    private static final int SIZE = 8;
    


    public GameManager(int size) {
        final Value p1Token = Value.Black;
        final Value p2Token = Value.White;
        
        printer = new ConsolePrinter();
        board = new Board(size, p1Token, p2Token);
        logic = new NormalLogic(board);
        players = new Player[2];
        players[0] = new HumanPlayer(p1Token);
        players[1] = new HumanPlayer(p2Token);
    }

    public void run() {
        tie = 0; //updates when player cant move
        int turn = 0;
        while (!board.isFull()) {
            playTurn(players[turn]); // play current turn
            turn = 1 - turn; // switch the turn to other player
            if (tie == 2) { // if both players cant move
                printer.noMoreMoves();
                break;
            }
            
            logic.endTurn();
        }
        endGame();
    }

    public void playTurn(Player player) {
        printer.printBoard(board);
        final Value token = player.getToken();
        Set<Point> availableMoves = logic.availableMoves(token); // Get available moves
        player.startTurn(board.getOpponent(player.getToken()), logic.getLastMove());


        if (!availableMoves.isEmpty()) { //Check if there are avaliable moves for the player
            putNext(player, availableMoves); //Player puts his token
            tie = 0;
        } else {
            tie++; //if it equals to 2 - theres a tie
          //  if(!board.isFull()){ //If the board is full the game should be end without this print
                player.cantMove(logic);
            //}
        }
    }

    public void putNext(Player p, Set<Point> availableMoves){
        boolean flag = true;

        while (flag) {
            Point position = (p.makeTurn(logic, board, availableMoves)); //Get coordinate by player's choose
            if (position.getX() < 0 || (board.isFull()) || (tie ==2)) { //means that the player couldn't move
                break;
            }
            if (logic.isLegal(position)) { //Check if the move is legal
                flag = false;
                logic.makeMove(position, p.getToken(),board); // flip other tokens
            } else {
                printer.massage("Illegal move\n");
            }
        }
    }
    public void winner() {
        Value p1 = players[0].getToken();
        Value p2 = players[1].getToken();
        int score1 = 0;
        int score2 = 0;

        for (int x = 0;x < board.getSize();x++) {
            for(int y = 0;y < board.getSize();y++) {
                if (board.getValue(x, y) == p1) {
                    score1++;
                } else if (board.getValue(x, y) == p2) {
                    score2++;
                }
            }
        }
        printer.winner(p1, p2, score1, score2);
    }

    public void endGame() {
        printer.printBoard(board);
        winner();
    }
}

