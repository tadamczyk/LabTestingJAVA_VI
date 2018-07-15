package projekt1;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.Mock;

@ExtendWith(MockitoExtension.class)
class GameTest {
  Game game;

  @Mock
  Game mockGame;

  @BeforeEach
  void setUp() {
    game = new Game(5, 5);
  }

  @AfterEach
  void tearDown() {
    game = null;
  }

  @ParameterizedTest
  @CsvFileSource(resources = "/boards-good-examples.csv")
  void testInitializedGameWhenBoardSizesIsValid(int rows, int columns) {
    game = new Game(rows, columns);
    assertTrue(game instanceof Game);
  }

  @Test
  void testDisplayWhenCurrentSymbolIsCross() {
    /*** PREPARE OUTPUT STREAM FOR TEST ***/
    OutputStream outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));

    CellSymbol testedSymbol = CellSymbol.CROSS;
    game.setCurrentSymbol(testedSymbol);
    String actual = outputStream.toString();
    assertThat(actual).contains("Player 'X' move");

    /* SET OUTPUT STREAM ON DEFAULT VALUE */
    System.setOut(System.out);
  }

  @Test
  void testDisplayWhenCurrentSymbolIsCircle() {
    /*** PREPARE OUTPUT STREAM FOR TEST ***/
    OutputStream outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));

    CellSymbol testedSymbol = CellSymbol.CIRCLE;
    game.setCurrentSymbol(testedSymbol);
    String actual = outputStream.toString();
    assertThat(actual).contains("Player 'O' move");

    /* SET OUTPUT STREAM ON DEFAULT VALUE */
    System.setOut(System.out);
  }

  @Test
  void testCellSymbolAfterSetCellOnBoard() {
    int insertedRow = 2;
    int insertedColumn = 2;
    CellSymbol insertedSymbol = CellSymbol.CROSS;

    CellSymbol expected = insertedSymbol;
    game.setCellOnBoard(insertedRow, insertedColumn, insertedSymbol);
    CellSymbol actual = game.board.cells[2][2].symbol;

    assertThat(actual, equalTo(expected));
  }

  @Test
  void testIsRowInRangeWhenRowsIsValid() {
    int testedRow = Board.rows - 1;
    boolean condition = game.isRowInRange(testedRow);
    assertTrue(condition);
  }

  @Test
  void testIsRowInRangeWhenRowsIsNotValid() {
    int testedRow = Board.rows + 1;
    boolean condition = game.isRowInRange(testedRow);
    assertFalse(condition);
  }

  @Test
  void testIsRowInRangeWhenColumnsIsValid() {
    int testedColumn = Board.columns - 1;
    boolean condition = game.isColumnInRange(testedColumn);
    assertTrue(condition);
  }

  @Test
  void testIsRowInRangeWhenColumnsIsNotValid() {
    int testedColumn = Board.columns + 1;
    boolean condition = game.isColumnInRange(testedColumn);
    assertFalse(condition);
  }

  @Test
  void testIsEmptyCellWhenCellSymbolIsNotEmpty() {
    Cell testedCell = new Cell(1, 1);
    testedCell.symbol = CellSymbol.CROSS;
    boolean condition = game.isEmptyCell(testedCell);
    assertFalse(condition);
  }

  @Test
  void testIsEmptyCellWhenCellSymbolIsEmpty() {
    Cell testedCell = new Cell(1, 1);
    boolean condition = game.isEmptyCell(testedCell);
    assertTrue(condition);
  }

  @Test
  void testIsValidCellWhenRowAndColumnIsNotInRangeByMock() {
    int testedRow = -1;
    int testedColumn = -1;
    when(mockGame.isValid(testedRow, testedColumn)).thenReturn(true);
    boolean condition = mockGame.isValid(-1, -1);
    assertTrue(condition);
  }

  @Test
  void testIsValidEmptyCellOnBoard() {
    int testedRow = 0;
    int testedColumn = 0;
    boolean condition = game.isValid(testedRow, testedColumn);
    assertTrue(condition);
  }

  @Test
  void testIsNotValidWhenCellOnBoardHasCrossSymbol() {
    int testedRow = 0;
    int testedColumn = 0;
    CellSymbol testedSymbol = CellSymbol.CROSS;

    game.board.cells[0][0].symbol = testedSymbol;
    boolean condition = game.isValid(testedRow, testedColumn);

    assertFalse(condition);
  }

  @Test
  void testUpdateGameWhenGameStateIsPlaying() {
    CellSymbol testedSymbol = CellSymbol.CROSS;

    GameState expected = GameState.PLAYING;
    game.setGameState(testedSymbol);
    GameState actual = game.currentState;

    assertEquals(expected, actual);
  }

  @Test
  void testUpdateGameWhenCellSymbolIsCrossAndItHasWon() {
    CellSymbol testedSymbol = CellSymbol.CROSS;

    GameState expected = GameState.CROSS_WON;
    when(mockGame.setGameState(testedSymbol)).thenReturn(expected);
    GameState actual = mockGame.setGameState(testedSymbol);

    assertEquals(expected, actual);
  }

  @Test
  void testUpdateGameWhenCellSymbolIsCircleAndItHasWon() {
    CellSymbol testedSymbol = CellSymbol.CIRCLE;

    GameState expected = GameState.CIRCLE_WON;
    when(mockGame.setGameState(testedSymbol)).thenReturn(expected);
    GameState actual = mockGame.setGameState(testedSymbol);

    assertEquals(expected, actual);
  }

  @Test
  void testUpdateGameWhenIsDraw() {
    CellSymbol testedSymbol = CellSymbol.CROSS;

    GameState expected = GameState.DRAW;
    when(mockGame.setGameState(testedSymbol)).thenReturn(expected);
    GameState actual = mockGame.setGameState(testedSymbol);

    assertEquals(expected, actual);
  }

  @Test
  void testDisplayWhenCrossWon() {
    /*** PREPARE OUTPUT STREAM FOR TEST ***/
    OutputStream outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));

    game.currentState = GameState.CROSS_WON;
    game.showFinalResult();
    String actual = outputStream.toString();
    assertThat(actual).contains("'X' won!");

    /* SET OUTPUT STREAM ON DEFAULT VALUE */
    System.setOut(System.out);
  }

  @Test
  void testDisplayWhenCircleWon() {
    /*** PREPARE OUTPUT STREAM FOR TEST ***/
    OutputStream outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));

    game.currentState = GameState.CIRCLE_WON;
    game.showFinalResult();
    String actual = outputStream.toString();
    assertThat(actual).contains("'O' won!");

    /* SET OUTPUT STREAM ON DEFAULT VALUE */
    System.setOut(System.out);
  }

  @Test
  void testDisplayWhenIsDraw() {
    /*** PREPARE OUTPUT STREAM FOR TEST ***/
    OutputStream outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));

    game.currentState = GameState.DRAW;
    game.showFinalResult();
    String actual = outputStream.toString();
    assertThat(actual).contains("Draw!");

    /* SET OUTPUT STREAM ON DEFAULT VALUE */
    System.setOut(System.out);
  }

  @Test
  void testSetCurrentPlayerWhenCellSymbolIsCross() {
    game.currentPlayer = CellSymbol.CROSS;
    CellSymbol expected = CellSymbol.CIRCLE;
    CellSymbol actual = game.setCurrentPlayer();
    assertThat(actual, equalTo(expected));
  }

  @Test
  void testSetCurrentPlayerWhenCellSymbolIsCircle() {
    game.currentPlayer = CellSymbol.CIRCLE;
    CellSymbol expected = CellSymbol.CROSS;
    CellSymbol actual = game.setCurrentPlayer();
    assertThat(actual, equalTo(expected));
  }
}