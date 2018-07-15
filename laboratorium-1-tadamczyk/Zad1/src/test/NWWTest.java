package test;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.NWW;

public class NWWTest {
  private NWW test;

  @Before
  public void setup() {
    test = new NWW();
  }

  @Test
  public void shouldReturnCorrectValueWhenFirstArgIsZero() {
    assertEquals(0, test.nww(0, 1));
  }

  @Test
  public void shouldReturnCorrectValueWhenSecondArgIsZero() {
    assertEquals(0, test.nww(1, 0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldReturnIllegalArgumentExceptionWhenBothArgsAreEqualZero() {
    test.nww(0, 0);
  }

  @Test
  public void shouldReturnCorrectValueWhenBothArgsArePositive() {
    assertEquals(60, test.nww(60, 5));
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldReturnIllegalArgumentExceptionWhenFirstArgIsNegative() {
    test.nww(-1, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldReturnIllegalArgumentExceptionWhenSecondArgIsNegative() {
    test.nww(1, -2);
  }

  @After
  public void teardown() {
    test = null;
  }
}
