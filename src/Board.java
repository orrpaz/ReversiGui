import java.awt.Point;

public class Board {

    private int size;
    private Value p1Token;
    private Value p2Token;
    private Cell[][] board;
    
   // enum Value {Black,Empty,White};
    
    Board(int s, Value p1, Value p2) {
        this.size = s;
        this.p1Token = p1;
        this.p2Token = p2;
        
        initialize();
        startMode(p1Token, p2Token);
    }
    void initialize() {
        //Allocates a 2d matrix
        board = new Cell[size][]; // size-times array of cells
        for (int i = 0; i < size; i++) {
            board[i] = new Cell[size]; //each array is size's-cells
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = new Cell(i, j, Value.Empty); // each
            }
        }
    }
    
    Value getP1() {
        return p1Token;
    }
    Value getP2() {
        return p2Token;
    }
    Value getOpponent(Value x) {
        return p1Token == x? p2Token: p1Token;
    }
    int getSize() {
        return size;
    }
    void clear() {
        //Set empty cells
        for (int x = 0;x < size;x++) {
            for(int y = 0;y < size;y++) {
                board[x][y].setSign(Value.Empty);
            }
        }
    }
    
    void startMode(Value p1Token, Value p2Token) {
        board[(size / 2) - 1][(size / 2) - 1].setSign(p2Token);
        board[size / 2][size / 2].setSign(p2Token);
        board[(size / 2) - 1][size / 2].setSign(p1Token);
        board[size / 2][(size / 2) - 1].setSign(p1Token);
    }
    //Updates the board
    void update(int row, int col, Value player) {
        board[row][col].setSign(player);
    }
    //Updates the board by coordinate
    void update(Point c, Value player) {
        board[(int) c.getX()][(int) c.getY()].setSign(player);
     }
  //Returns the board
    Value getValue(int row, int col) {
        return board[row][col].getSign();
    }
  //Returns if the board is full
    boolean isFull() {
        for (int row = 0;row < size;row++) {
            for(int col = 0;col < size;col++) {
                if (board[row][col].isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }
    
    int score(Value player) {
        Value opponent = getOpponent(player);
        int score = 0;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                Value val = board[row][col].getSign();
                if (val == player) {
                    score++;
                } else if (val == opponent) {
                    score--;
                }
            }
        }
        return score;
    }
}
