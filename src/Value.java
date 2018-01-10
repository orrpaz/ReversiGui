public enum Value {
      //Black, White, Empty;
    Black('X'), White('O'), Empty(' ');
    private char value;
    private Value(char value) { 
        this.value = value;
        }
    
}
