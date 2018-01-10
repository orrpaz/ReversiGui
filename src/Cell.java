import java.awt.Point;

public class Cell {
    private Point coordinate;
    private Value sign;

    public Cell(int x, int y , Value sign) {
        this.coordinate = new Point(x, y);
        this.sign = sign;
    }
    public Cell(Point coordinate, Value sign) {
        this.coordinate = coordinate;
        this.sign = sign;
    }
    public Value getSign() {
        return this.sign;
    }
    public void setSign(Value val) {
        this.sign = val;
    }
    public boolean isEmpty() {
        return sign == Value.Empty;
    }
}
