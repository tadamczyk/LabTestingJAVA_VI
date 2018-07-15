package projekt1;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.either;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CellTest {
  Cell cell;

  @BeforeEach
  void setUp() {
    cell = new Cell(1, 1);
  }

  @AfterEach
  void tearDown() {
    cell = null;
  }

  @Test
  void testWhenRowAndColumnIsNotGreaterThanZero() {
    Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
      new Cell(-1, 0);
    });
    String actual = exception.getMessage();
    String rowExceptionMessage = "Row must be greater than 0.";
    String columnExceptionMessage = "Column must be greater than 0.";
    assertThat(actual, either(is(rowExceptionMessage)).or(is(columnExceptionMessage)));
  }

  @Test
  void testWhenRowIsNotGreaterThanZero() {
    Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
      cell.isRowValid(-1);
    });
    String actual = exception.getMessage();
    String exceptionMessage = "Row must be greater than 0.";
    assertThat(actual).hasToString(exceptionMessage);
  }

  @Test
  void testWhenColumnIsNotGreaterThanZero() {
    Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
      cell.isColumnValid(-1);
    });
    String actual = exception.getMessage();
    String exceptionMessage = "Column must be greater than 0.";
    assertThat(actual).hasToString(exceptionMessage);
  }

  @Test
  void testCellSymbolAfterRunClearCellMethod() {
    cell.symbol = CellSymbol.CROSS;
    cell.clearCell();
    CellSymbol expected = CellSymbol.EMPTY;
    CellSymbol actual = cell.symbol;
    assertEquals(expected, actual);
  }

  @Test
  void testPrintCellSymbolWhenIsEmpty() {
    cell.symbol = CellSymbol.EMPTY;
    String actual = cell.printCell();
    assertThat(actual).hasSize(3).contains(" ").startsWith(" ").endsWith(" ");
  }

  @Test
  void testPrintCellSymbolWhenIsCross() {
    cell.symbol = CellSymbol.CROSS;
    String actual = cell.printCell();
    assertThat(actual).hasSize(3).contains("X").startsWith(" ").endsWith(" ");
  }

  @Test
  void testPrintCellSymbolWhenIsCircle() {
    cell.symbol = CellSymbol.CIRCLE;
    String actual = cell.printCell();
    assertThat(actual).hasSize(3).contains("O").startsWith(" ").endsWith(" ");
  }
}