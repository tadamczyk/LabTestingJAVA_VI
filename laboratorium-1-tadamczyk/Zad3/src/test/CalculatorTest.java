/* W przypadku pisania testów assertEquals dla zmiennych typu double nale¿y okreœiæ
 * przybli¿enie dla porównywanych wartoœci.
 */

package test;

import static org.junit.Assert.*;

import org.junit.Test;

import main.Calculator;

public class CalculatorTest {
  private Calculator calc = new Calculator();
  private static double epsilon = 1E-10;

  @Test
  public void shouldReturnCorrectValueAfterAddingTwoArgs() {
    assertEquals(4.0, calc.add(1.01, 2.99), epsilon);
  }

  @Test
  public void shouldReturnCorrectValueAfterSubtractionTwoArgs() {
    assertEquals(0, calc.sub(1.99, 1.99), epsilon);
  }

  @Test
  public void shouldReturnCorrectValueAfterMultipliactionTwoArgs() {
    assertEquals(3.75, calc.multi(1.5, 2.5), epsilon);
  }

  @Test
  public void shouldReturnCorrectValueAfterDividingTwoArgs() {
    assertEquals(5.0, calc.div(2.5, 0.5), epsilon);
  }

  @Test
  public void shouldReturnTruthWhenFirstArgIsGreaterThanSecondArg() {
    assertEquals(true, calc.greater(1.000000001, 1.0));
  }
  
  @Test
  public void shouldReturnTruthWhenFirstArgIsLessThanSecondArg() {
    assertEquals(true, calc.less(1.0, 1.000000001));
  }
  
  @Test
  public void shouldReturnTruthWhenFirstArgIsEqualsSecondArg() {
    assertEquals(true, calc.equality(0.000000001, 0.000000001));
  }
}
