package theGame;
import java.awt.Point;
import java.util.Set;

public interface Printer {

    /**
     * Print the board
     * @param board
     */
         void printBoard(Board  board);

    /**
     * Print the input string
     * @param s
     */
         void massage(final String s);

    /**
     * Print in case player cant move
     */
         void cantMove();
        //Print when both players cant move
         void noMoreMoves();
        //Print the available moves
         void availableMoves(Set<Point> legalMoves);
        //Print that it's the input players turn
         void yourTurn(final Value player);
        //Print the Point of the played move
         void playingMove(Value v, Point c);
        //Prints the winner
         void winner(final Value p1, final Value p2, final int score1,
                     final int score2);
        Point getInput();
        void pressAnyKey() ;


}
