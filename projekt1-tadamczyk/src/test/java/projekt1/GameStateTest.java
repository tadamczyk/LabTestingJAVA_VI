package projekt1;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.EnumSet;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class GameStateTest {
  @Test
  void testElementGameStateClass() {
    GameState.valueOf(GameState.PLAYING.toString());
  }

  @ParameterizedTest
  @EnumSource(value = GameState.class)
  void testTypeElementsFromGameState(GameState state) {
    assertTrue(EnumSet.of(GameState.PLAYING, GameState.DRAW, GameState.CROSS_WON, GameState.CIRCLE_WON)
        .contains(state));
  }
}