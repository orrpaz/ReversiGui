package theGame;
import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

public class NormalLogic implements Logic {

    private Board board;
    private Set<Point> canMove;
    private Point last;
    private int size;
    
    
    NormalLogic(Board b) {
        board = b;
        size = b.getSize();
        last = new Point(-3,-3);
        canMove = new HashSet<Point>();
    }
    
    public void calculateAll(final Value player) {
        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                if (board.getValue(row, col) == player) {
                    Point coordinate =  new Point(row,col);
                    calculate(coordinate, player);
                }
            }
        }
    }
    public void couldntMove() {
        last = new Point(-2,-2);
    }
    public void endTurn() {
        canMove.clear();
    }
    
    public Point getLastMove() {
        return last;
    }
    
    


void setAvailableMoves(Set<Point> availableMoves) {
    canMove = availableMoves;
}

//All of the other funcs were described in Logic class
public Set<Point> availableMoves(final Value token) {
    calculateAll(token);
    return canMove;
}
public boolean isLegal(Point c) {
    int row = (int) c.getX();
    int col = (int) c.getY();
    if ((row >= size) || (col >= size)) {
        return false;
    }

    Point coordinate =  new Point(row, col);

    //Check if it is not a move from the avaliable moves
    boolean is_in = canMove.contains(coordinate);
    if (!is_in) {
        return false;
    }
    return true;
}

boolean shouldStop(final Value current, final Value player,
        final Value other, boolean sawOther, boolean need_update) {
        if (current == other) {
            sawOther = true;
            return false;
        }
        if (current == Value.Empty) {
            if (sawOther) {
                need_update = true;
            }
        }
        //In case of ' ' or player's Value
        return true;
    }


public void calculate(Point c, final Value player) {
    int row = (int) c.getX();
    int col = (int) c.getY();
    Value current;
    Value other = board.getOpponent(player);
    boolean sawOther = false;
    boolean need_update = false;

    //Down
    int i;
    for (i = row + 1; i < size; i ++) {
        current = board.getValue(i, col);
        if (current == other) {
            sawOther = true;
            continue;
        }
        if (current == Value.Empty) {
            if (sawOther) {
                need_update = true;
            }
        }
        //In case of ' ' or player's Value
        break;
    }
    if (need_update) {
        canMove.add(new Point(i, col));
    }

    //Right
    sawOther = need_update = false;

    for (i = col + 1; i < size; i ++) {
        current = board.getValue(row, i);
        if (current == other) {
            sawOther = true;
            continue;
        }
        if (current == Value.Empty) {
            if (sawOther) {
                need_update = true;
            }
        }
        //In case of ' ' or player's Value
        break;
    }
    if (need_update) {
        canMove.add(new Point(row, i));
    }

    //Left
    sawOther = need_update = false;

    for (i = col - 1; i >= 0; i--) {
        current = board.getValue(row, i);
        if (current == other) {
            sawOther = true;
            continue;
        }
        if (current == Value.Empty) {
            if (sawOther) {
                need_update = true;
            }
        }
        //In case of ' ' or player's Value
        break;
    }
    if (need_update) {
        canMove.add(new Point(row, i));
    }

    //Up
    sawOther = need_update = false;

    for (i = row - 1; i  >= 0; i --) {
        current = board.getValue(i, col);
        if (current == other) {
            sawOther = true;
            continue;
        }
        if (current == Value.Empty) {
            if (sawOther) {
                need_update = true;
            }
        }
        //In case of ' ' or player's Value
        break;
    }
    if (need_update) {
        canMove.add(new Point(i, col));
    }

    //Down-right
    int temp_row = row + 1;
    sawOther = need_update = false;

    for (i = col + 1; i < size && temp_row < size; i++, temp_row++) {
        current = board.getValue(temp_row, i);
        if (current == other) {
            sawOther = true;
            continue;
        }
        if (current == Value.Empty) {
            if (sawOther) {
                need_update = true;
            }
        }
        //In case of ' ' or player's Value
        break;
    }
    if (need_update) {
        canMove.add(new Point(temp_row, i));
    }

    //Up-right
    temp_row = row - 1;
    sawOther = need_update = false;

    for (i = col + 1; i < size && temp_row >= 0; i++, temp_row--) {
        current = board.getValue(temp_row, i);
        if (current == other) {
            sawOther = true;
            continue;
        }
        if (current == Value.Empty) {
            if (sawOther) {
                need_update = true;
            }
        }
        //In case of ' ' or player's Value
        break;
    }
    if (need_update) {
        canMove.add(new Point(temp_row, i));
    }
    //Up-left
    temp_row = row - 1;
    sawOther = need_update = false;

    for (i = col - 1; i >= 0 && temp_row >= 0; i--, temp_row--) {
        current = board.getValue(temp_row, i);
        if (current == other) {
            sawOther = true;
            continue;
        }
        if (current == Value.Empty) {
            if (sawOther) {
                need_update = true;
            }
        }
        //In case of ' ' or player's Value
        break;
    }
    if (need_update) {
        canMove.add(new Point(temp_row, i));
    }

    //Down-left
    temp_row = row + 1;
    sawOther = need_update = false;

    for (i = col - 1; i >= 0 && temp_row < size; i--, temp_row++) {
        current = board.getValue(temp_row, i);
        if (current == other) {
            sawOther = true;
            continue;
        }
        if (current == Value.Empty) {
            if (sawOther) {
                need_update = true;
            }
        }
        //In case of ' ' or player's Value
        break;
    }
    if (need_update) {
        canMove.add(new Point(temp_row, i));
    }
}
public void setCurrentBoard(Board  b) {
    board = b;
    size = b.getSize();
}
public void makeMove(Point position, Value token, Board b) {
    board.update(position, token); //update this one token
    flip(position, token); // flip other tokens
    last = position;
}

/*
Name: flipHelper
Input: current - the current Value on the board
player - the player
hasSame - booleanthat checks if there's a token in this line that is same
as the player's token
Output: Return true if the loop should break (when see ' ' or player's Value)
Operation: Helps the flip to decide if the line should be flipped
*/
boolean flipHelper(final Value current, final Value player, boolean hasSame) {
   if((current != player) && (current != Value.Empty)) {
       return false;
   }
   if (current == player) {
       hasSame = true;
   }
   return true;
}

public void flip(Point c, final Value player) {
    int row = (int) c.getX();
    int col = (int) c.getY();

    boolean hasSame = false;
    Value current;
    int i, j, k;

    //Right
    for (i = col + 1; i < size; i++) {
        current = board.getValue(row, i); 
        if((current != player) && (current != Value.Empty)) {
            continue;
        }
        if (current == player) {
            hasSame = true;
        }
        break;
    }
    if (hasSame) {
        for (j = col + 1; j <= i; j++) {
            board.update(row, j, player);
        }
    }

    //Left
    hasSame = false;
    for (i = col - 1; i >= 0; i--) {
        current = board.getValue(row, i);
        if((current != player) && (current != Value.Empty)) {
            continue;
        }
        if (current == player) {
            hasSame = true;
        }
        break;
    }
    if (hasSame) {
        for (j = col - 1; j >= i; j--) {
            board.update(row, j, player);
        }
    }
    //Down
    hasSame = false;
    for (i = row + 1; i < size; i++) {
        current = board.getValue(i, col);
        if((current != player) && (current != Value.Empty)) {
            continue;
        }
        if (current == player) {
            hasSame = true;
        }
        break;
    }
    if (hasSame) {
        for (j = row + 1; j <= i; j++) {
            board.update(j, col, player);
        }
    }

    //Up
    hasSame = false;
    for (i = row - 1; i >= 0; i--) {
        current = board.getValue(i, col);
        if((current != player) && (current != Value.Empty)) {
            continue;
        }
        if (current == player) {
            hasSame = true;
        }
        break;
    }
    if (hasSame) {
        for (j = row - 1; j >= i; j--) {
            board.update(j, col, player);
        }
    }

    //Down-Right
    int temp_row;
    hasSame = false;
    for (i = col + 1, temp_row = row + 1; i < size && temp_row < size; i++, temp_row++) {
        current = board.getValue(temp_row, i);
        if((current != player) && (current != Value.Empty)) {
            continue;
        }
        if (current == player) {
            hasSame = true;
        }
        break;
    }
    if (hasSame) {
        for (j = col + 1, k = row + 1; j <= i && k <= temp_row; j++, k++) {
            board.update(k, j, player);
        }
    }

    //Up-Right
    hasSame = false;
    for (i = col + 1, temp_row = row - 1; i < size && temp_row >= 0; i++, temp_row--) {
        current = board.getValue(temp_row, i);
        if((current != player) && (current != Value.Empty)) {
            continue;
        }
        if (current == player) {
            hasSame = true;
        }
        break;
    }
    if (hasSame) {
        for (j = col + 1, k = row - 1; j <= i && k >= temp_row; j++, k--) {
            board.update(k, j, player);
        }
    }

    //Up-Left
    hasSame = false;
    for (i = col - 1, temp_row = row - 1; i >= 0 && temp_row >= 0; i--, temp_row--) {
        current = board.getValue(temp_row, i);
        if((current != player) && (current != Value.Empty)) {
            continue;
        }
        if (current == player) {
            hasSame = true;
        }
        break;
    }
    if (hasSame) {
        for (j = col - 1, k = row - 1; j >= i && k >= temp_row; j--, k--) {
            board.update(k, j, player);
        }
    }

    //Down-Left
    hasSame = false;
    for (i = col - 1, temp_row = row + 1; i >= 0 && temp_row < size; i--, temp_row++) {
        current = board.getValue(temp_row, i);
        if((current != player) && (current != Value.Empty)) {
            continue;
        }
        if (current == player) {
            hasSame = true;
        }
        break;
    }
    if (hasSame) {
        for (j = col - 1, k = row + 1; j >= i && k <= temp_row; j--, k++) {
            board.update(k, j, player);
        }
    }   
    
}

}
