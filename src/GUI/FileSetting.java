package GUI;


import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Singleton of file setting.
 */
public class FileSetting {

    private String player1Color;
    private String player2Color;
//    private String startPlayer;
    private String boardSize;
    private static FileSetting instance = null;


    public static FileSetting getInstance() {
        if(instance == null) {
            instance = new FileSetting();
        }
        return instance;
    }

    /**
     * constructor.
     */
    public FileSetting() {
        String filePath = "settingFile.txt";
        String line;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            Map<String, String> map = new HashMap<String, String>();

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":", 2);
                if (parts.length >= 2) {
                    String key = parts[0];
                    String value = parts[1];
                    map.put(key, value);
                } else {
                    System.out.println("ignoring line: " + line);
                }
            }

            player1Color = map.get("colorPlayer1");
            player2Color= map.get("colorPlayer2");
//            startPlayer = map.get("startPlayer");
            boardSize = map.get("sizeBoard");
            reader.close();
        } catch (FileNotFoundException noFile) {
            // default values
            writeToFile("8", "Black", "White");

        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    /**
     * write setting to file
     * @param size - size of board
     * @param color1 - color of player 1
     * @param color2 - color of player 2
     */
    public void writeToFile(String size,
                             String color1, String color2) {
        Writer writer = null;
        player1Color = color1;
        player2Color=color2;
//        this.startPlayer=startPlayer;
        this.boardSize = size ;

        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("settingFile.txt"), "utf-8"));
            String settings = "colorPlayer1:" + color1 +
                    "\ncolorPlayer2:" + color2 + "\nsizeBoard:" + size;
            writer.write(settings);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @return size
     */
    public String getBoardSize() {
        return boardSize;
    }

    /**
     *
     * @return Player 1 Color
     */
    public String getPlayer1Color() {
        return player1Color;
    }
    /**
     *
     * @return Player 2 Color
     */
    public String getPlayer2Color() {
        return player2Color;
    }


}

