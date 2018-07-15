package test;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.NWD;

public class NWDTest {
  private NWD test;

  @Before
  public void setup() {
    test = new NWD();
  }

  @Test
  public void shouldReturnCorrectValueWhenFirstArgIsZero() {
    assertEquals(1, test.nwd(0, 1));
  }

  @Test
  public void shouldReturnCorrectValueWhenSecondArgIsZero() {
    assertEquals(1, test.nwd(1, 0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldReturnIllegalArgumentExceptionWhenBothArgsAreEqualZero() {
    test.nwd(0, 0);
  }

  @Test
  public void shouldReturnCorrectValueWhenBothArgsArePositive() {
    assertEquals(6, test.nwd(60, 18));
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldReturnIllegalArgumentExceptionWhenFirstArgIsNegative() {
    test.nwd(-1, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldReturnIllegalArgumentExceptionWhenSecondArgIsNegative() {
    test.nwd(1, -2);
  }

  @After
  public void teardown() {
    test = null;
  }
}
