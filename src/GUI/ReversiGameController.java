package GUI;

import theGame.GameManager;
import theGame.GuiPrinter;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.Point;
import java.net.URL;
import java.util.ResourceBundle;

public class ReversiGameController implements Initializable {
    @FXML
    private HBox root;
    private GameManager gManager;
    private Point clickedMove;
    private GuiBoard guiBoard;
    private String sMessage;
    @FXML
    private Text message;
    @FXML
    private Text playersTurn;
    @FXML
    private Text P1Score;
    @FXML
    private Text P2Score;

    @FXML
    private Button returnMenu;
    
    private String p1Color;
    private String p2Color;
    


    @Override
    /**
     * initialize the game
     */
    public void initialize(URL location, ResourceBundle resources) {
        // read setting.
        FileSetting setting = FileSetting.getInstance();
        GuiPrinter printer = new GuiPrinter(this);
        this.p1Color = setting.getPlayer1Color();
        this.p2Color = setting.getPlayer2Color();
        // create game manager.
        gManager = new GameManager(Integer.parseInt(setting.getBoardSize()), printer);
        this.guiBoard = new GuiBoard(gManager.getTheBoard(),Color.valueOf(this.p1Color),
                Color.valueOf(this.p2Color));
        guiBoard.setPrefWidth(400);
        guiBoard.setPrefHeight(400);
        root.getChildren().add(0, guiBoard);
        guiBoard.draw();


        // listener
        root.widthProperty().addListener((observable, oldValue, newValue) -> {
            double boardNewWidth = newValue.doubleValue() - 190;
            guiBoard.setPrefWidth(boardNewWidth);
            guiBoard.draw();
        });
        // listener
        root.heightProperty().addListener((observable, oldValue, newValue) -> {
            guiBoard.setPrefHeight(newValue.doubleValue() - 50);
            guiBoard.draw();
        });

        gManager.startGame();
        draw();

        // listener
        guiBoard.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                int height = (int)guiBoard.getPrefHeight();
                int width = (int)guiBoard.getPrefWidth();
                int size = guiBoard.getBoardSize();
                int cellHeight = height / size;
                int cellWidth = width / size;
                
                int col = (int) (mouseEvent.getY()/cellHeight);
                int row = (int) (mouseEvent.getX()/cellWidth);
                clickedMove = new Point (row, col);
                gManager.playTurn();
            }
        });
        
        
    }

    /**
     * @return the move was clicked.
     */
    public Point getClickedMove() {
        return this.clickedMove;
    }

    /**
     *
     * @return guiBoard.
     */
    public GuiBoard getGuiBoard() {
        return this.guiBoard;
    }

    /**
     * set messege.
     * @param message
     */
    public void setMessage(String message) {
        sMessage = message;
    }

    /**
     * this method pop windows after the game is over.
     * @param winner - winner
     * @param info - info
     */
    public void finishGame(String winner, String info) {
        // alert the user
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Finish Him!");
        alert.setHeaderText(winner);
        alert.setContentText(info);
        alert.showAndWait();
        return;
    }

    /**
     * this method responsible to draw the info of the game.
     */
    public void draw() {

        
        playersTurn.setText((gManager.getCurrentPlayer() == 1)?this.p2Color : this.p1Color);
        P1Score.setText("" + gManager.getScore(1));
        P2Score.setText("" + gManager.getScore(2));
        guiBoard.setCurrentPlayer(gManager.getCurrentPlayer());
        guiBoard.draw();
        message.setText(sMessage);
        sMessage = "";
    }


    /**
     * back to menu.
     */
    public void backMenu() {
    Stage stage = (Stage)this.returnMenu.getScene().getWindow();
    try {

        GridPane root = (GridPane)FXMLLoader.load(getClass().getResource("MenuFXML.fxml"));
        Scene scene = new Scene(root,451,350);
        scene.getStylesheets().add((getClass().getResource("application.css").toExternalForm()));
        stage.setTitle("Reversi game");
        stage.setScene(scene);
        stage.show();
        
    } catch(Exception e) {
        e.printStackTrace();
    }
}

}
//$$$