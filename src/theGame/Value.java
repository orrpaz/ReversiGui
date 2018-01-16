package theGame;

/**
 * enum Value.
 */
public enum Value {
      //Black, White, Empty;
    PlayerX('X'), PlayerO('O'), Empty(' ');
    private char value;
    private Value(char value) { 
        this.value = value;
        }
    
}
