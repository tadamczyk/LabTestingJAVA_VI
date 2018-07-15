package projekt1;

class Board {
  public static int rows;
  public static int columns;

  Cell[][] cells;
  int currentRow;
  int currentColumn;

  public Board(int rows, int columns) {
    try {
      areRowsAndColumnsEqual(rows, columns);
      isRowsValid(rows);
      isColumnsValid(columns);
      Board.rows = rows;
      Board.columns = columns;
      cells = new Cell[rows][columns];
      initializeBoard();
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException(e.getMessage());
    }
  }

  public void areRowsAndColumnsEqual(int rows, int columns) {
    if (rows != columns) {
      throw new IllegalArgumentException("Rows and columns must be equal.");
    }
  }

  public void isRowsValid(int rows) {
    if (rows < 3) {
      throw new IllegalArgumentException("Rows must be greater or equal than 3.");
    }
  }

  public void isColumnsValid(int columns) {
    if (columns < 3) {
      throw new IllegalArgumentException("Columns must be greater or equal than 3.");
    }
  }

  public void initializeBoard() {
    for (int row = 0; row < rows; ++row) {
      for (int column = 0; column < columns; ++column) {
        cells[row][column] = new Cell(row + 1, column + 1);
      }
    }
  }

  public boolean isDraw() {
    for (int row = 0; row < rows; ++row) {
      for (int column = 0; column < columns; ++column) {
        if (cells[row][column].symbol == CellSymbol.EMPTY) {
          return false;
        }
      }
    }
    return true;
  }

  public boolean hasWon(CellSymbol symbol) {
    return symbol != CellSymbol.EMPTY
        ? hasWonInRow(currentRow, symbol) || hasWonInColumn(currentColumn, symbol)
            || hasWonInForwardDiagonally(symbol) || hasWonInBackwardDiagonally(symbol)
        : false;
  }

  public boolean hasWonInRow(int row, CellSymbol symbol) {
    for (int column = 0; column < columns; ++column) {
      if (cells[row][column].symbol != symbol) {
        return false;
      }
    }
    return true;
  }

  public boolean hasWonInColumn(int column, CellSymbol symbol) {
    for (int row = 0; row < rows; ++row) {
      if (cells[row][column].symbol != symbol) {
        return false;
      }
    }
    return true;
  }

  public boolean hasWonInForwardDiagonally(CellSymbol symbol) {
    for (int row = 0; row < rows; ++row) {
      if (cells[row][row].symbol != symbol) {
        return false;
      }
    }
    return true;
  }

  public boolean hasWonInBackwardDiagonally(CellSymbol symbol) {
    for (int row = 0, column = rows - 1; row < rows; ++row, --column) {
      if (cells[row][column].symbol != symbol) {
        return false;
      }
    }
    return true;
  }

  public void printBoard() {
    for (int row = 0; row < rows; ++row) {
      for (int column = 0; column < columns; ++column) {
        System.out.print(cells[row][column].printCell());
        if (column < columns - 1) {
          System.out.print("|");
        }
      }
      System.out.println();
      if (row < rows - 1) {
        int counter = columns * 4 - 1;
        while (counter-- > 0) {
          System.out.print("-");
        }
        System.out.println();
      }
    }
  }
}