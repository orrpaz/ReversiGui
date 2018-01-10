import java.awt.Point;
import java.util.Set;

public class HumanPlayer implements Player {
    private Value sign;
    private Printer printer;
    public HumanPlayer(Value token) {
        sign = token;
        printer = new ConsolePrinter();
    }

    public Point makeTurn(Logic l, Board b, Set<Point> availableMoves) {
        if (availableMoves.isEmpty()) {
            return new Point(-1, -1);
        }
        printer.availableMoves(availableMoves); // Print available moves
        
        printer.massage("\nEnter Row: ");
        int row = printer.getInput();
        while (row < 1) {
            printer.massage("Input MUST be a positive NUMBER!\n");
            printer.massage("Enter Row: ");
            row = printer.getInput();
        }
        printer.massage("Enter Col: ");
        int col = printer.getInput();
        while (col < 1) {
            printer.massage("Input MUST be a positive NUMBER!\n");
            printer.massage("Enter Col: ");
            col = printer.getInput();
        }
        printer.massage("\n");
        return new Point(row - 1, col - 1); //the -- because the input is higher
    }

    @Override
    public Value getToken() {
        // TODO Auto-generated method stub
        return sign;
    }

    @Override
    public void startTurn(Value val, Point c) {
        // TODO Auto-generated method stub
        printer.playingMove(val, c);
        printer.yourTurn(sign);
    }

    @Override
    public void cantMove(Logic l) {
        l.couldntMove();
        printer.cantMove(); //print that the player cant move
        printer.pressAnyKey(); //wait for user to press any key
    }

}
