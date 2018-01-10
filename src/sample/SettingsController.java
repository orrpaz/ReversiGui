package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.*;

public class SettingsController  {
    ObservableList<String> colorsList = FXCollections.
            observableArrayList("Black", "White", "Blue", "Yellow", "Red", "Green");
    ObservableList<String> sizeList = FXCollections.
            observableArrayList("4", "5", "6", "7", "8","9","10","11","12","13","14","15","16","17"
                    ,"18","19","20");
    ObservableList<String> playersList = FXCollections.
            observableArrayList("Player 1", "Player 2");

    @FXML
    private ComboBox<String> boardSize;
    @FXML
    private ComboBox<String> player1Color;
    @FXML
    private ComboBox<String> player2Color;
    @FXML
    private ComboBox<String> openPlayer;
    @FXML
    private Button srart;

    @FXML
    private void initialize(){
        boardSize.setValue("8");
        boardSize.setItems(sizeList);

        player1Color.setValue("Black");
        player1Color.setItems(colorsList);

        player2Color.setValue("White");
        player2Color.setItems(colorsList);

        openPlayer.setValue("Player 1");
        openPlayer.setItems(playersList);
    }

    private void writeToFile(){
        Writer writer = null;

        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("settingFile.txt"), "utf-8"));
            String settings = "startPlayer: " + openPlayer + "\ncolorPlayer1: " + player1Color +
                    "\ncolorPlayer2: " + player2Color + "\nsizeBoard: " + boardSize;
            writer.write(settings);
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
