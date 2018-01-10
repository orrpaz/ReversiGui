import java.awt.Point;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

public class ConsolePrinter implements Printer {

    @Override
    public void printBoard(Board board) {
        final int size = board.getSize();
        System.out.print("  ");
        for(int i = 1;i <= size;i++) {
            System.out.print( "| " + i + " ");
        }
        System.out.print( "|" + "\n");

        String line ="";
        char c = '-';
        int number = size*4 + 3;
        char[] repeat = new char[number];
        Arrays.fill(repeat, c);
        line+= new String(repeat);
        
        System.out.print(line + "\n");

        for(int x = 0;x < size;x++) {
            int row = x + 1;
            System.out.print(row);
            for(int y = 0;y < size;y++) {
                Value v = board.getValue(x, y);
                System.out.print( " | " + convertToChar(v));
            }
            System.out.print( " |" + "\n");
            //Prints the line below
            System.out.print( line  + "\n");
        }
    }
    char convertToChar(Value v) {
            
        if (v == Value.Black) {
            return 'X';
        }
        if (v == Value.White) {
            return 'O';
        }
        return ' ';
    }

    @Override
    public void massage(String s) {
        System.out.print( s);
    }

    @Override
    public void cantMove() {
        System.out.print("No possible moves. Play passes back to the other player. "
                + "Press any key and enter to continue" + "\n");
    }

    @Override
    public void noMoreMoves() {
        System.out.print( "Neither players can move!!!" + "\n");
    }

    @Override
    public void availableMoves(Set<Point> legalMoves) {
        System.out.print( "Your possible moves:" + "\n");
        
        String moves = "";
        for (Point c : legalMoves) {
            moves+= "(" + ((int)c.getX() + 1) + "," + ((int)c.getY() + 1) + "),";
        }
        moves = moves.substring(0, moves.length() - 1);
        System.out.println(moves);
    }

    @Override
    public void yourTurn(Value player) {
        System.out.print( "\n");
        System.out.print( convertToChar(player) + ": It's your move" + "\n");
    }

    @Override
    public void playingMove(Value sign, Point c) {
        if((int)c.getX() >= 0) {
            System.out.print( convertToChar(sign) + " Played: (" + ((int)c.getX() + 1)
                 + "," + ((int)c.getY() + 1) + ")\n" + "\n");
        } else {
            if ((int)c.getX() == -2) {
                System.out.print( convertToChar(sign) + " wasn't able to move\n");
            }
        }
    }

    @Override
    public void winner(Value p1, Value p2, int score1, int score2) {
        System.out.print( "Score: " + "\n");
        System.out.print( convertToChar(p1) + ": " + score1 + ", " + convertToChar(p2) + ": " + score2 + "\n");
        if (score1 == score2) {
            System.out.print( "Tie!" + "\n");
        } else {
            if (score1 > score2) {
                System.out.print( "The winner is: " + convertToChar(p1) + "\n");
            } else {
                System.out.print( "The winner is: " + convertToChar(p2) + "\n");
            }
        }
    }

    @Override
    public int getInput() {
        Scanner in = new Scanner(System.in);
        int num = -1;
        try
        {
          num=in.nextInt();
          
        }
        catch(InputMismatchException exception)
        {           
            System.out.println("Catch");
        }
        return num;

    }

    @Override
    public void pressAnyKey() {
        // TODO Auto-generated method stub
        Scanner scan = new Scanner(System.in);
        String s = scan.next();
    }

}
