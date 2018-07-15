package lab8.steps;

import static org.assertj.core.api.Assertions.*;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import lab8.Calculator;

public class MySteps {
  Calculator calc;
  int a, b, result, expected;
  boolean resultBoolean, expectedBoolean;

  @Given("I add two integer numbers")
  public void givenIAmAddTwoIntegerNumbers() {
    calc = new Calculator();
    a = 2;
    b = 1;
    expected = 3;
  }

  @When("I add them")
  public void whenIAddThem() {
    result = calc.add(a, b);
  }

  @Then("I get correct result after adding")
  public void thenIGetCorrectResultAfterAdding() {
    assertThat(result).isEqualTo(expected);
  }

  @Given("I subtract two integer numbers")
  public void givenIAmSubtractTwoIntegerNumbers() {
    calc = new Calculator();
    a = 2;
    b = 1;
    expected = 1;
  }

  @When("I subtract them")
  public void whenISubtractThem() {
    result = calc.sub(a, b);
  }

  @Then("I get correct result after subtracting")
  public void thenIGetCorrectResultAfterSubtracting() {
    assertThat(result).isEqualTo(expected);
  }

  @Given("I multiply two integer numbers")
  public void givenIAmMultiplyTwoIntegerNumbers() {
    calc = new Calculator();
    a = 2;
    b = 1;
    expected = 2;
  }

  @When("I multiply them")
  public void whenIMultiplyThem() {
    result = calc.multi(a, b);
  }

  @Then("I get correct result after multiplying")
  public void thenIGetCorrectResultAfterMultiplying() {
    assertThat(result).isEqualTo(expected);
  }

  @Given("I divide two integer numbers")
  public void givenIAmDivideTwoIntegerNumbers() {
    calc = new Calculator();
    a = 2;
    b = 1;
    expected = 2;
  }

  @When("I divide them")
  public void whenIDivideThem() {
    result = calc.div(a, b);
  }

  @Then("I get correct result after dividing")
  public void thenIGetCorrectResultAfterDividing() {
    assertThat(result).isEqualTo(expected);
  }

  @Given("I compare two integer numbers")
  public void givenIAmCompareTwoIntegerNumbers() {
    calc = new Calculator();
    a = 2;
    b = 1;
    expectedBoolean = true;
  }

  @When("I compare them")
  public void whenICompareThem() {
    resultBoolean = calc.greater(a, b);
  }

  @Then("I get first number is greater than second")
  public void thenIGetFirstNumberIsGreaterThanSecond() {
    assertThat(resultBoolean).isEqualTo(expectedBoolean);
  }

  @Given("I compare two numbers")
  public void givenIAmCompareTwoNumbers() {
    calc = new Calculator();
    a = 2;
    b = 1;
    expectedBoolean = false;
  }

  @When("I compare that numbers")
  public void whenICompareThatNumbers() {
    resultBoolean = calc.less(a, b);
  }

  @Then("I get first number is less than second")
  public void thenIGetFirstNumberIsLessThanSecond() {
    assertThat(resultBoolean).isEqualTo(expectedBoolean);
  }
}