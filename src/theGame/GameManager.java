package theGame;
import java.awt.Point;
import java.util.Set;

import GUI.GuiPlayer;

public class GameManager {
    private Board board;
    private Logic logic;
    private Player[] players;
    private Printer printer;
    private int tie;
    private boolean shouldStop;
    private int turn;
    private Value currentPlayer;


    /**
     * constructor.
     * @param size -size
     * @param prnt-
     */
    public GameManager(int size, GuiPrinter prnt) {
        final Value p1Token = Value.PlayerX;
        final Value p2Token = Value.PlayerO;
        printer = prnt;
        board = new Board(size, p1Token, p2Token);
        logic = new NormalLogic(board);
        players = new Player[2];
        players[0] = new GuiPlayer(printer, p1Token);
        players[1] = new GuiPlayer(printer, p2Token);
    }

    /**
     * this method initialize the game, draw possible moves of current player
     */
    public void startGame() {
        shouldStop = false;
        turn = 0;
        tie = 0;
        currentPlayer = Value.PlayerX;
        printer.availableMoves(logic.availableMoves(currentPlayer));
        printer.printBoard(board);
    }
    /**
     * run one turn of the game.
     */
    public void playTurn() {

        if(shouldStop) {
            return;
        }
        Set<Point> availableMoves = logic.availableMoves(currentPlayer); // Get available moves

        if (!availableMoves.isEmpty()) { //Check if there are avaliable moves for the player
            tie = 0;
            Point position = (players[turn].makeTurn(logic, board, availableMoves)); //Get coordinate by player's choose
            if (logic.isLegal(position)) { //Check if the move is legal
                logic.makeMove(position, players[turn].getToken(),board); // flip other tokens
                if(board.isFull()) {
                    endGame();
                    return;
                }
                changeTurn();
            } else {
                printer.massage("Illegal move\n");
                printer.printBoard(board);
            }
        } else {
            tie++; //if it equals to 2 - there's a tie
            if (tie == 2) {
                shouldStop = true;
                winner();
            }
            changeTurn();

        }
        // if we need  to stop the game.
        if ((tie == 2) || this.board.isFull()) {
            shouldStop = true;
        }
    }

//    public void putNext(Player p, Set<Point> availableMoves){
//        boolean flag = true;
//
//        while (flag) {
//            Point position = (p.makeTurn(logic, board, availableMoves)); //Get coordinate by player's choose
////            if (position.getX() < 0 || (board.isFull()) || (tie ==2)) { //means that the player couldn't move
////                break;
////            }
//            if (logic.isLegal(position)) { //Check if the move is legal
//                flag = false;
//                logic.makeMove(position, p.getToken(),board); // flip other tokens
//            } else {
//                printer.massage("Illegal move\n");
//            }
//        }
//    }

    /**
     * this method check who is the winner and print message.
     */
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

    /**
     * this method end game.
     */
    public void endGame() {
        logic.endTurn();
        shouldStop = true;
        printer.printBoard(board);
        winner();
    }

    /**
     * this method change the turn between 2 players.
     */
    public void changeTurn(){
        turn = 1 - turn;
        this.currentPlayer = (this.currentPlayer == Value.PlayerO ? Value.PlayerX : Value.PlayerO);
        logic.endTurn();
        // if there is no available moves.
        if (logic.availableMoves(currentPlayer).isEmpty()) {
          printer.cantMove();  
        } else{
        printer.availableMoves(logic.availableMoves(currentPlayer));
        }

        printer.printBoard(board);
    }

    /**
     * get function.
     * @return get fucntion
     */
    public int getCurrentPlayer() {
        return turn;
    }
    /**
     * get function.
     * @return get function
     */
    public Board getTheBoard() {
    return this.board;
}
    /**
     * get function.
     * @return get function
     */
    public int getScore(int playerNumber) {
        playerNumber--;
         Value v = players[playerNumber].getToken();
        return board.getPlayerScore(v);
    }

}