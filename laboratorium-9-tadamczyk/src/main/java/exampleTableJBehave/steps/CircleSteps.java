package exampleTableJBehave.steps;

import static org.junit.Assert.assertEquals;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import exampleTableJBehave.Circle;

public class CircleSteps {
	Circle circleTest;
	double result;

	@Given("I have new circle")
	public void givenIHaveNewCircle() {
		circleTest = new Circle();
	}

	@When("I set radius for circumfence <value1>")
	public void whenISetRadiusForCircumfenceValue1(@Named("value1") int val1) {
		result = circleTest.Circumfence(val1);
	}

	@Then("I get circumfence <resultValue>")
	public void thenIGetCircumfenceResultValue(@Named("resultValue") double resultValue) {
		assertEquals(resultValue, result, 0.1);
	}

	@When("I set radius for poleCircle <value1>")
	public void whenISetRadiusForPoleCricleValue1(@Named("value1") int val1) {
		result = circleTest.poleCircle(val1);
	}

	@Then("I get poleCircle <resultValue>")
	public void thenIGetPoleCircleResultValue(@Named("resultValue") double resultValue) {
		assertEquals(resultValue, result, 0.1);
	}
}