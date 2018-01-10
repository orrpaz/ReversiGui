import java.awt.Point;
import java.util.Set;

public interface Player {
    Value getToken();
    Point makeTurn(Logic l, Board b, Set<Point> availableMoves);
    void startTurn(final Value sign, Point c);
    void cantMove( Logic l);
    
}
