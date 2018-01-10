//import java.util.Iterator;
//import java.util.Scanner;
//import java.util.Set;
//
//public class ConsoleScreen implements Printer {
//    @Override
//    public void printBoard(Board board) {
//        System.out.println();
//            const int size = board.getSize();
//        System.out.print(" ");
//        for (int i = 1; i <= size; i++) {
//            System.out.print("| " + i + " ");
//
//        }
//        System.out.print("|");
//        for (int i = 0; i < 3 + 4 * size; i++) {
//            System.out.print("-");
//        }
//        System.out.println();
//
//
//        for (int x = 0; x < size; x++) {
//            int row = x + 1;
//            System.out.print(row);
//            for (int y = 0; y < size; y++) {
//                System.out.print(" | " + board.getValue(x, y));
//
//            }
//            System.out.println(" |");
//
//            //Prints the line below
//            System.out.print("|");
//            for (int i = 0; i < 3 + 4 * size; i++) {
//                System.out.print("-");
//            }
//            System.out.println();
//        }
//    }
//
//
//    @Override
//    public void massage(String s) {
//        System.out.print(s);
//    }
//
//    @Override
//    public void cantMove() {
//        System.out.println("No possible moves. Play passes back to the other player. "
//                "Press any key and enter to continue");
//    }
//
//
//    @Override
//    public void noMoreMoves() {
//        System.out.println("Neither players can move!!!");
//    }
//
//    @Override
//    public void availableMoves(Set<Coordinate> legalMoves) {
//        System.out.println("Your possible moves:");
//        Iterator<Coordinate> iterator = legalMoves.iterator();
//        while (iterator.hasNext()) {
//            System.out.print("(" + iterator.next().getRow() + 1 +
//                    "," + iterator.next().getCol() + 1 + ") ");
//        }
//        System.out.println();
//
//    }
//
//    @Override
//    public void yourTurn(final Value player) {
//        System.out.println();
//        System.out.print(player);
//        System.out.println(": It's your move");
//
//    }
//
//    @Override
//    public void playingMove(Coordinate c) {
//        System.out.println("Playing: (" + c.getRow() + 1 + "," + c.getCol() + 1 + ")\n");
//    }
//
//    @Override
//    public void winner(Value p1, Value p2, int score1, int score2) {
//        System.out.println("Score: ");
//        System.out.println(p1 + ": " + score1 + ", " + p2 + ": " + score2);
//        if (score1 == score2) {
//            System.out.println("Tie!");
//        } else {
//            if (score1 > score2) {
//                System.out.println("The winner is: " + p1);
//            } else {
//                System.out.println("The winner is: " + p2);
//            }
//        }
//    }
//
//    public int input() {
//        Scanner in = new Scanner(System.in);
//        int s = in.nextInt();
//        if(!in.hasNextInt()) {
//            System.out.println("Invalid input! please insert a number");
//            in.next();
//        }
//        return s;
//
//    }
//}
//
