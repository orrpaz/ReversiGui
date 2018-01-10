public class Coordinate {
    private int x;
    private int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getRow() {
        return this.x;
    }

    public void setRow(int x) {
        this.x = x;
    }

    public int getCol() {
        return this.y;
    }

    public void setCol(int y) {
        this.y = y;
    }


    public boolean compare(Coordinate other) {
        int row = 0, col = 0;
        return row == other.getRow() && col == other.getCol();
    }

    public Coordinate apply(Coordinate other) {
        if (this != other) {
            int row = other.getRow();
            int col = other.getCol();
            return new Coordinate(row, col);
        }
        return this;
    }
}




