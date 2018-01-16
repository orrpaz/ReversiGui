package theGame;

import java.awt.Point;
import java.util.Set;

import javafx.scene.layout.GridPane;
import GUI.ReversiGameController;

public class GuiPrinter extends GridPane implements Printer {
    
    private ReversiGameController reversiGameController;
    
        
    public GuiPrinter(ReversiGameController ctrl) {
        this.reversiGameController = ctrl;
    }

    @Override
    public void printBoard(Board board) {
        this.reversiGameController.draw();
    }

    @Override
    public void massage(String s) {
        this.reversiGameController.setMessage(s);
        
    }

    @Override
    public void cantMove() {
        this.reversiGameController.setMessage("OOPS! There are\nNO MOVES!!!\nClick to continue");
    }

    @Override
    public void noMoreMoves() {
        this.reversiGameController.finishGame("Draw!", "Both Players can't move!!!");
    }

    @Override
    public void availableMoves(Set<Point> legalMoves) {
        this.reversiGameController.getGuiBoard().setAvailableMoves(legalMoves);
    }

    @Override
    public void yourTurn(Value player) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void playingMove(Value v, Point c) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void winner(Value p1, Value p2, int score1, int score2) {
        String winner = "";
        if (!this.reversiGameController.getGuiBoard().getTheBoard().isFull()) {
            winner += "Both Players can't move!!! --- ";
        }
        if (score1 == score2) {
            winner += "Draw!";
        } else if (score1 > score2) {
            winner += "Player 1 WON!!!!!";
        } else {
            winner += "Player 2 WON!!!!!";
        }
        this.reversiGameController.finishGame(winner, "Final Scores:\np1: " + score1 + "\np2: " + score2);
    }

    @Override
    public Point getInput() {
        return this.reversiGameController.getClickedMove();
    }

    @Override
    public void pressAnyKey() {
        // TODO Auto-generated method stub
        
    }

}
