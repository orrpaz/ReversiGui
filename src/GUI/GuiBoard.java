package GUI;

import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import theGame.Board;
import theGame.Value;

import java.awt.Point;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class GuiBoard extends GridPane {
    private Board board;
    private Color p1Color;
    private Color p2Color;
    private int currentPlayer;
    private int boardSize;
    private Set<Point> availableMoves;

    /**
     * constructor.
     * @param b - board
     * @param p1Color - color of player 1
     * @param p2Color - color of player 2
     */
    public GuiBoard(Board b, Color p1Color, Color p2Color) {
        this.board = b;
        boardSize = b.getSize();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ReversiBoard.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        this.p1Color = p1Color;
        this.p2Color = p2Color;
        currentPlayer = 0;
        this.availableMoves = new HashSet<Point>();


        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        try {
            fxmlLoader.load();
            this.setOnMouseClicked(event2 -> {

                
                int height = (int)this.getPrefHeight();
                int width = (int)this.getPrefWidth();
                int cellHeight = height / this.board.getSize();
                int cellWidth = width / this.board.getSize();
                
                int row = (int) (event2.getY()/cellHeight);
                int col = (int) (event2.getX()/cellWidth);
                event2.consume();
                
            });
        } catch (Exception e) {}

    }
    /**
     * @return the size of board
     */
    public int getBoardSize() {
        return this.boardSize;
    }

    /**
     * draw the board on screen.
     */
    public void draw() {
        this.getChildren().clear();

        //Set cell width and height
        int height = (int)this.getPrefHeight();
        int width = (int)this.getPrefWidth();
        int cellHeight = height / this.board.getSize();
        int cellWidth = width / this.board.getSize();


        for (int i = 0; i < this.board.getSize(); i++) {
            for (int j = 0; j < this.board.getSize(); j++) {
                if (this.board.getValue(j, i) == Value.Empty) { //In case it is just empty cell
                    Rectangle rec = new Rectangle(cellWidth, cellHeight, Color.valueOf("#C5FCBA"));
                    //rec.setFill(Color.BLUE);
                    rec.setStroke(Color.BLACK);
                    this.add(rec, j, i);
                } else { //In case it contains value, we need to first have cell, and then have value
                    StackPane stackPane = new StackPane();
                    Rectangle base = new Rectangle(cellWidth, cellHeight, Color.valueOf("#C5FCBA"));
                    base.setStroke(Color.BLACK);
                    stackPane.getChildren().add(base);
                    if (this.board.getValue(j, i) == Value.PlayerX) {
                        Circle disk = new Circle(cellWidth, cellHeight, Math.min(cellWidth, cellHeight) / 2.1, p1Color);
                        disk.setStroke(Color.BLACK);
                        stackPane.getChildren().add(disk);
                    }
                    else if (this.board.getValue(j, i) == Value.PlayerO) {
                        Circle disk = new Circle(cellWidth, cellHeight, Math.min(cellWidth, cellHeight) / 2.1, p2Color);
                        disk.setStroke(Color.BLACK);
                        stackPane.getChildren().add(disk);
                    }
                    this.add(stackPane, j, i);
                }
            }
        }

        for (Point p : this.availableMoves) {
            int y = p.y;
            int x = p.x;
            StackPane stackPane = new StackPane();
            Circle cir = new Circle(cellWidth, cellHeight, (Math.min(cellHeight, cellWidth) / 5), Color.AZURE);
            if (currentPlayer == 0) {
                cir.setStroke(this.p1Color);
            } else {
                cir.setStroke(this.p2Color);
            }
            stackPane.getChildren().add(cir);
            this.add(stackPane, x, y);
        }
    }
    /**
     * set Available Moves.
     * @param legalMoves Available Moves
     */
    public void setAvailableMoves(Set<Point> legalMoves){
        this.availableMoves = legalMoves;
        
    }
    /**
     * @return the board.
     */
    public Board getTheBoard() {
        return this.board;
    }
    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;

    }
}
