package theGame;
import java.awt.Point;
import java.util.Set;

public interface Logic {

    void calculateAll(final Value player);
    void couldntMove();
    void endTurn();
    
    Point getLastMove();
    Set<Point> availableMoves(final Value token);
    boolean isLegal(Point c);
    void makeMove(Point position, Value token, Board b);

}
