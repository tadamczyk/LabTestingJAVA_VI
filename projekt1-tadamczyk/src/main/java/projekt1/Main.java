package projekt1;

import java.util.Scanner;

class Main {
  public static Scanner in = new Scanner(System.in);

  public static void main(String[] args) {
    System.out.print("Enter board rows value and columns value: ");
    int rows = in.nextInt();
    int columns = in.nextInt();
    Game game = new Game(rows, columns);
    game.playGame();
  }
}