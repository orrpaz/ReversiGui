package GUI;

import java.awt.Point;
import java.util.Set;

import theGame.Board;
import theGame.GuiPrinter;
import theGame.Logic;
import theGame.Player;
import theGame.Printer;
import theGame.Value;

public class GuiPlayer implements Player {
    
    private GuiPrinter printer;
    private Value token;
    
    public GuiPlayer(Printer printer, Value token) {
        this.printer = (GuiPrinter) printer;
        this.token = token;
    }
    
    @Override
    public Value getToken() {
        return this.token;
    }

    @Override
    public Point makeTurn(Logic l, Board b, Set<Point> availableMoves) {
        Point clickedMove = this.printer.getInput();
        return clickedMove;
    }


}
