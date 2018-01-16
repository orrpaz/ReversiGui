package theGame;
import java.awt.Point;
import java.util.Set;

public interface Player {
    /**
     * get the sign of player.
     * @return return Valur
     */
    Value getToken();

    /**
     * this method make turn of plyer.
     * @param l - logic
     * @param b - board
     * @param availableMoves - available moves
     * @return Point
     */
    Point makeTurn(Logic l, Board b, Set<Point> availableMoves);

    
}
