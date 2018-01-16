package theGame;
import java.awt.Point;

public class Cell {
    private Point coordinate;
    private Value sign;

    /**
     * constructor
     * @param x value
     * @param y value
     * @param sign - Value of cell
     */
    public Cell(int x, int y , Value sign) {
        this.coordinate = new Point(x, y);
        this.sign = sign;
    }

    /**
     * constructor.
     * @param coordinate -point
     * @param sign Value of cell
     */
    public Cell(Point coordinate, Value sign) {
        this.coordinate = coordinate;
        this.sign = sign;
    }

    /**
     * get function
     * @return get function
     */
    public Value getSign() {
        return this.sign;
    }

    /**
     * set Value.
     * @param val - Value
     */
    public void setSign(Value val) {
        this.sign = val;
    }

    /**
     * check if cell is empty.
     * @return true if yes and false otherwise.
     */
    public boolean isEmpty() {
        return sign == Value.Empty;
    }
}
