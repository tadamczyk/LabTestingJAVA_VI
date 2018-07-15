package projekt1;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BoardTest {
  Board board;

  @BeforeEach
  void setUp() {
    board = new Board(3, 3);
  }

  @AfterEach
  void tearDown() {
    board = null;
  }

  @Test
  void testWhenBoardIsGreaterThanStandard() {
    board = new Board(5, 5);
    assertThat(board).hasFieldOrPropertyWithValue("rows", 5).hasFieldOrPropertyWithValue("columns", 5);
  }

  @Test
  void testWhenRowsAndColumnsAreNotEqual() {
    Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
      new Board(4, 3);
    });
    String actual = exception.getMessage();
    String exceptionMessage = "Rows and columns must be equal.";
    assertThat(actual).hasToString(exceptionMessage);
  }

  @Test
  void testWhenRowsIsNotValid() {
    Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
      board.isRowsValid(0);
    });
    String actual = exception.getMessage();
    String exceptionMessage = "Rows must be greater or equal than 3.";
    assertThat(actual).hasToString(exceptionMessage);
  }

  @Test
  void testWhenColumnsIsNotValid() {
    Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
      board.isColumnsValid(2);
    });
    String actual = exception.getMessage();
    String exceptionMessage = "Columns must be greater or equal than 3.";
    assertThat(actual).hasToString(exceptionMessage);
  }

  @Test
  void testResultWhenIsEmptyBoard() {
    boolean condition = board.isDraw();
    assertFalse(condition);
  }

  @Test
  void testWhenBoardIsFullAndResultIsDraw() {
    for (Cell[] row : board.cells) {
      for (Cell cell : row) {
        cell.symbol = null;
      }
    }
    boolean condition = board.isDraw();
    assertTrue(condition);
  }

  @Test
  void testWhenHasNotWonCrossAndCircle() {
    exampleBoardWhenIsDraw();
    boolean condition = board.isDraw();
    assertTrue(condition);
  }

  @Test
  void testWhenCrossHasWonInRow() {
    int testedRow = 0;
    CellSymbol testedSymbol = CellSymbol.CROSS;
    exampleBoardWhenHasWonInRow(testedRow, testedSymbol);
    boolean condition = board.hasWonInRow(testedRow, testedSymbol);
    assertTrue(condition);
  }

  @Test
  void testWhenCrossHasNotWonInRow() {
    int testedRow = 0;
    CellSymbol testedSymbol = CellSymbol.CROSS;
    exampleBoardWhenHasWonInColumn(testedRow, testedSymbol);
    boolean condition = board.hasWonInRow(testedRow, testedSymbol);
    assertFalse(condition);
  }

  @Test
  void testWhenCrossHasWonInColumn() {
    int testedColumn = 2;
    CellSymbol testedSymbol = CellSymbol.CROSS;
    exampleBoardWhenHasWonInColumn(testedColumn, testedSymbol);
    boolean condition = board.hasWonInColumn(testedColumn, testedSymbol);
    assertTrue(condition);
  }

  @Test
  void testWhenCrossHasNotWonInColumn() {
    int testedColumn = 2;
    CellSymbol testedSymbol = CellSymbol.CROSS;
    exampleBoardWhenHasWonInRow(testedColumn, testedSymbol);
    boolean condition = board.hasWonInColumn(testedColumn, testedSymbol);
    assertFalse(condition);
  }

  @Test
  void testWhenCrossHasWonInDiagonallyForward() {
    CellSymbol testedSymbol = CellSymbol.CROSS;
    exampleBoardWhenHasWonInForwardDiagonally(testedSymbol);
    boolean condition = board.hasWonInForwardDiagonally(testedSymbol);
    assertTrue(condition);
  }

  @Test
  void testWhenCrossHasNotWonInDiagonallyForward() {
    int testedColumn = 2;
    CellSymbol testedSymbol = CellSymbol.CROSS;
    exampleBoardWhenHasWonInRow(testedColumn, testedSymbol);
    boolean condition = board.hasWonInForwardDiagonally(testedSymbol);
    assertFalse(condition);
  }

  @Test
  void testWhenCrossHasWonInDiagonallyBackward() {
    CellSymbol testedSymbol = CellSymbol.CROSS;
    exampleBoardWhenHasWonInBackwardDiagonally(testedSymbol);
    boolean condition = board.hasWonInBackwardDiagonally(testedSymbol);
    assertTrue(condition);
  }

  @Test
  void testWhenCrossHasNotWonInDiagonallyBackward() {
    int testedRow = 0;
    CellSymbol testedSymbol = CellSymbol.CROSS;
    exampleBoardWhenHasWonInRow(testedRow, testedSymbol);
    boolean condition = board.hasWonInBackwardDiagonally(testedSymbol);
    assertFalse(condition);
  }

  @Test
  void testWhenSymbolIsEmptySymbolAndHasWonOnEmptyBoard() {
    CellSymbol testedSymbol = CellSymbol.EMPTY;
    boolean condition = board.hasWon(testedSymbol);
    assertThat(condition).isFalse();
  }

  @Test
  void testWhenSymbolIsEmptySymbolAndHasWonOnRowWonBoard() {
    int testedRow = 0;
    CellSymbol testedSymbol = CellSymbol.EMPTY;
    exampleBoardWhenHasWonInRow(testedRow, testedSymbol);
    boolean condition = board.hasWon(testedSymbol);
    assertThat(condition).isFalse();
  }

  @Test
  void testWhenSymbolIsCircleSymbolAndHasWonOnRowWonBoard() {
    int testedRow = 0;
    CellSymbol testedSymbol = CellSymbol.CIRCLE;
    exampleBoardWhenHasWonInRow(testedRow, testedSymbol);
    boolean condition = board.hasWon(testedSymbol);
    assertThat(condition).isTrue();
  }

  @Test
  void testWhenSymbolIsCircleSymbolAndHasWonOnColumnWonBoard() {
    int testedColumn = 0;
    CellSymbol testedSymbol = CellSymbol.CIRCLE;
    exampleBoardWhenHasWonInColumn(testedColumn, testedSymbol);
    boolean condition = board.hasWon(testedSymbol);
    assertThat(condition).isTrue();
  }

  @Test
  void testWhenSymbolIsCircleSymbolAndHasWonOnForwardDiagonallyWonBoard() {
    CellSymbol testedSymbol = CellSymbol.CIRCLE;
    exampleBoardWhenHasWonInForwardDiagonally(testedSymbol);
    boolean condition = board.hasWon(testedSymbol);
    assertThat(condition).isTrue();
  }

  @Test
  void testWhenSymbolIsCircleSymbolAndHasWonOnBackwardDiagonallyWonBoard() {
    CellSymbol testedSymbol = CellSymbol.CIRCLE;
    exampleBoardWhenHasWonInBackwardDiagonally(testedSymbol);
    boolean condition = board.hasWon(testedSymbol);
    assertThat(condition).isTrue();
  }

  @Test
  void testWhenSymbolIsCircleSymbolAndHasWonOnEmptyBoard() {
    CellSymbol testedSymbol = CellSymbol.CIRCLE;
    boolean condition = board.hasWon(testedSymbol);
    assertThat(condition).isFalse();
  }

  @Test
  public void testPrintBoardMethodWhenBoardIsEmpty() {
    /*** PREPARE OUTPUT STREAM FOR TEST ***/
    OutputStream outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));

    board.printBoard();
    String actual = outputStream.toString();
    assertThat(actual).contains("|", " ", "-").hasLineCount(5).doesNotContain("X").doesNotContain("O");

    /* SET OUTPUT STREAM ON DEFAULT VALUE */
    System.setOut(System.out);
  }

  @Test
  public void testPrintBoardMethodWhenBoardIsFullAndIsDraw() {
    /*** PREPARE OUTPUT STREAM FOR TEST ***/
    OutputStream outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));

    exampleBoardWhenIsDraw();
    board.printBoard();
    String actual = outputStream.toString();
    assertThat(actual).contains("X", "O", "|", " ", "-").hasLineCount(5);

    /* SET OUTPUT STREAM ON DEFAULT VALUE */
    System.setOut(System.out);
  }

  /******************** HELPER METHODS ********************/

  private void exampleBoardWhenIsDraw() {
    board.cells[0][0].symbol = CellSymbol.CIRCLE;
    board.cells[0][1].symbol = CellSymbol.CROSS;
    board.cells[0][2].symbol = CellSymbol.CIRCLE;
    board.cells[1][0].symbol = CellSymbol.CIRCLE;
    board.cells[1][1].symbol = CellSymbol.CROSS;
    board.cells[1][2].symbol = CellSymbol.CIRCLE;
    board.cells[2][0].symbol = CellSymbol.CROSS;
    board.cells[2][1].symbol = CellSymbol.CIRCLE;
    board.cells[2][2].symbol = CellSymbol.CROSS;
  }

  private void exampleBoardWhenHasWonInRow(int row, CellSymbol symbol) {
    for (Cell cell : board.cells[row]) {
      cell.symbol = symbol;
    }
  }

  private void exampleBoardWhenHasWonInColumn(int column, CellSymbol symbol) {
    for (Cell[] row : board.cells) {
      Cell cell = row[column];
      cell.symbol = symbol;
    }
  }

  private void exampleBoardWhenHasWonInForwardDiagonally(CellSymbol symbol) {
    for (int i = 0; i < Board.rows; ++i) {
      board.cells[i][i].symbol = symbol;
    }
  }

  private void exampleBoardWhenHasWonInBackwardDiagonally(CellSymbol symbol) {
    for (int i = 0; i < Board.rows; ++i) {
      board.cells[Board.rows - i - 1][i].symbol = symbol;
    }
  }
}