package projekt1;

import java.util.Scanner;

class Game {
  public static Scanner in = new Scanner(System.in);

  Board board;
  GameState currentState;
  CellSymbol currentPlayer;

  public Game(int rows, int columns) {
    initializeGame(rows, columns);
  }

  public void initializeGame(int rows, int columns) {
    try {
      board = new Board(rows, columns);
      currentPlayer = CellSymbol.CROSS;
      currentState = GameState.PLAYING;
    } catch (Exception e) {
      System.out.println("Rows and columns must be greater or equal than 3. Closing...");
      System.exit(0);
    }
  }

  public void playGame() {
    do {
      movePlayer(currentPlayer);
      board.printBoard();
      currentState = setGameState(currentPlayer);
      currentPlayer = setCurrentPlayer();
      showFinalResult();
    } while (currentState == GameState.PLAYING);
  }

  public void movePlayer(CellSymbol symbol) {
    boolean validInput = false;
    do {
      setCurrentSymbol(symbol);
      int row = in.nextInt() - 1;
      int column = in.nextInt() - 1;
      if (isValid(row, column)) {
        setCellOnBoard(row, column, symbol);
        validInput = true;
      } else {
        System.out.println("This move is not valid. Try again!");
      }
    } while (!validInput);
  }

  public void setCurrentSymbol(CellSymbol symbol) {
    if (symbol == CellSymbol.CROSS) {
      System.out.print("Player 'X' move - enter row and column: ");
    } else {
      System.out.print("Player 'O' move - enter row and column: ");
    }
  }

  public void setCellOnBoard(int row, int column, CellSymbol symbol) {
    board.cells[row][column].symbol = symbol;
    board.currentRow = row;
    board.currentColumn = column;
  }

  public boolean isValid(int row, int column) {
    return isRowInRange(row) && isColumnInRange(column) && isEmptyCell(board.cells[row][column]);
  }

  public boolean isRowInRange(int row) {
    return row >= 0 && row < Board.rows;
  }

  public boolean isColumnInRange(int column) {
    return column >= 0 && column < Board.columns;
  }

  public boolean isEmptyCell(Cell cell) {
    return cell.symbol == CellSymbol.EMPTY;
  }

  public GameState setGameState(CellSymbol symbol) {
    if (board.hasWon(symbol)) {
      return currentState = (symbol == CellSymbol.CROSS) ? GameState.CROSS_WON : GameState.CIRCLE_WON;
    } else if (board.isDraw()) {
      return currentState = GameState.DRAW;
    } else {
      return currentState = GameState.PLAYING;
    }
  }

  public void showFinalResult() {
    if (currentState == GameState.CROSS_WON) {
      System.out.println("'X' won!");
    } else if (currentState == GameState.CIRCLE_WON) {
      System.out.println("'O' won!");
    } else if (currentState == GameState.DRAW) {
      System.out.println("Draw!");
    }
  }

  public CellSymbol setCurrentPlayer() {
    return currentPlayer = (currentPlayer == CellSymbol.CROSS) ? CellSymbol.CIRCLE : CellSymbol.CROSS;
  }
}