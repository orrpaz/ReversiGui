package GUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class MenuController  {
    @FXML
    private Button settings;

    @FXML
    private Button startGame;

    /**
     * this method rsponsible to start the game by click on start button.
     */
    public void StartGame(){
//        Setting s = new Setting ();

        Stage primaryStage = (Stage)startGame.getScene().getWindow();
        try {
            HBox root = (HBox)FXMLLoader.load(getClass().getResource("ReversiGame.fxml"));
            Scene scene = new Scene(root,620,500);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setTitle("Reversi");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }

    }
    /**
     * this method rsponsible to open the setting by click on setting button.
     */
    public void setSettings(){
        Stage stage = (Stage)settings.getScene().getWindow();
        try {
            GridPane root = (GridPane) FXMLLoader.load(getClass().getResource("SettingFXML.fxml"));
            Scene scene = new Scene(root,450,350);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            stage.setTitle("Settings");
            stage.setScene(scene);
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }

    }


}
