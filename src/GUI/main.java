package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

//import sun.plugin.javascript.navig.Anchor;

public class main extends Application {

    /**
     * start the GUI
     * @param primaryStage stage of the GUI
     * @throws Exception exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        try {
            GridPane root = (GridPane)FXMLLoader.load(getClass().getResource("MenuFXML.fxml"));
            Scene scene = new Scene(root,450,350);
            scene.getStylesheets().add((getClass().getResource("application.css").toExternalForm()));
            primaryStage.setTitle("Reversi game");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * start of the Reversi app
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}