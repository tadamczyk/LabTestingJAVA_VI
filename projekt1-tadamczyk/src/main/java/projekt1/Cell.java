package projekt1;

class Cell {
  CellSymbol symbol;
  int row;
  int column;

  public Cell(int row, int column) {
    try {
      this.row = row;
      this.column = column;
      isRowValid(this.row);
      isColumnValid(this.column);
      clearCell();
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException(e.getMessage());
    }
  }

  public void isRowValid(int row) throws IllegalArgumentException {
    if (row <= 0) {
      throw new IllegalArgumentException("Row must be greater than 0.");
    }
  }

  public void isColumnValid(int column) throws IllegalArgumentException {
    if (column <= 0) {
      throw new IllegalArgumentException("Column must be greater than 0.");
    }
  }

  public void clearCell() {
    symbol = CellSymbol.EMPTY;
  }

  public String printCell() {
    switch (symbol) {
    case EMPTY:
      return "   ";
    case CROSS:
      return " X ";
    case CIRCLE:
      return " O ";
    default:
      return null;
    }
  }
}