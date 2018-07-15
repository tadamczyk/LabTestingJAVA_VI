package projekt1;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.EnumSet;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class CellSymbolTest {
  @Test
  void testElementCellSymbolClass() {
    CellSymbol.valueOf(CellSymbol.EMPTY.toString());
  }

  @ParameterizedTest
  @EnumSource(value = CellSymbol.class)
  void testTypeElementsFromCellSymbol(CellSymbol symbol) {
    assertTrue(EnumSet.of(CellSymbol.EMPTY, CellSymbol.CROSS, CellSymbol.CIRCLE).contains(symbol));
  }
}