package test;

import static org.junit.Assert.*;

import org.junit.Test;

import main.Calculator;

public class CalculatorTest {
  private Calculator calc = new Calculator();

  @Test
  public void shouldReturnCorrectValueAfterAddingTwoArgs() {
    assertEquals(3, calc.add(1, 2));
  }

  @Test
  public void shouldReturnCorrectValueAfterSubtractionTwoArgs() {
    assertEquals(-1, calc.sub(1, 2));
  }

  @Test
  public void shouldReturnCorrectValueAfterMultipliactionTwoArgs() {
    assertEquals(2, calc.multi(1, 2));
  }

  @Test(expected = ArithmeticException.class)
  public void shouldReturnArithmeticExceptionAfterDividingByZero() {
    calc.div(1, 0);
  }

  @Test
  public void shouldReturnCorrectValueAfterDividingTwoArgs() {
    assertEquals(2, calc.div(2, 1));
  }

  @Test
  public void shouldReturnTruthWhenFirstArgIsGreaterThanSecondArg() {
    assertEquals(true, calc.greater(2, 1));
  }
  
  @Test
  public void shouldReturnTruthWhenFirstArgIsLessThanSecondArg() {
    assertEquals(true, calc.less(1, 2));
  }
  
  @Test
  public void shouldReturnTruthWhenFirstArgIsEqualsSecondArg() {
    assertEquals(true, calc.equality(1, 1));
  }
}
