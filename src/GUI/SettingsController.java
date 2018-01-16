package GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {
    ObservableList<String> colorsList = FXCollections.
            observableArrayList("Black", "White", "Blue", "Yellow", "Red", "Green", "Purple", "Gray", "Brown");
    ObservableList<String> sizeList = FXCollections.
            observableArrayList("4", "5", "6", "7", "8", "9"
                    , "10", "11", "12", "13", "14", "15", "16", "17"
                    , "18", "19", "20");
//    ObservableList<String> playersList = FXCollections.
//            observableArrayList("Player 1", "Player 2");

    @FXML
    private ComboBox<String> boardSize;
    @FXML
    private ComboBox<String> player1Color;
    @FXML
    private ComboBox<String> player2Color;
//    @FXML
//    private ComboBox<String> openPlayer;
    @FXML
    private Button saveButton;

    static HashMap<String, String> map = new HashMap<String, String>();


    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        FileSetting settings = FileSetting.getInstance();
//        readSetting();

        String size = settings.getBoardSize();
//        String whoStart = settings.getStarterName();
        String color1, color2;
        color1 = settings.getPlayer1Color();
        color2 = settings.getPlayer2Color();



        boardSize.setValue(size);
        boardSize.setItems(sizeList);

        player1Color.setValue(color1);
        player1Color.setItems(colorsList);

        player2Color.setValue(color2);
        player2Color.setItems(colorsList);

//        openPlayer.setValue(whoStart);
//        openPlayer.setItems(playersList);
    }

    /**
     * call to write function in the singleton fileSetting
     * @param size - size of board
     * @param color1 - color of player 1
     * @param color2 - color of player 2
     */
    private void writeToFile(String size,
                             String color1, String color2) {

        FileSetting.getInstance().writeToFile(size,color1,color2);

    }

    /**
     * back to menu after create file setting.
     */
    public void save() {
        if (player1Color.getValue().equals(player2Color.getValue())) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Input not valid");
            errorAlert.setContentText("Players must have different colors");
            errorAlert.showAndWait();
            return;
        }
        writeToFile(boardSize.getValue(), player1Color.getValue(),
                player2Color.getValue());

//        Menu m = new Menu();
        Stage stage = (Stage) saveButton.getScene().getWindow();
//        m.start(stage);
        try {
            Pane root = (Pane) FXMLLoader.load(getClass().getResource("MenuFXML.fxml"));
            Scene scene = new Scene(root, 450, 350);
            scene.getStylesheets().add((getClass().getResource("application.css").toExternalForm()));
            stage.setTitle("Reversi game");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
