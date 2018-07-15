package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.Table;

public class TableTest {

  private Table table;
  private int[] array = { 1, 5, 3, 4, 2 };

  @Before
  public void setUp() {
    table = new Table(array);
  }

  @Test
  public void checkLargest() {
    int result = table.largest(table);
    assertEquals(5, result);
  }

  @Test
  public void checkSmallest() {
    int result = table.smallest(table);
    assertEquals(1, result);
  }

  @Test
  public void checkIsSorted() {
    boolean result = table.isSorted(table);
    assertFalse(result);
  }
}
