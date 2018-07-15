package test;

import static org.junit.Assert.*;

import org.junit.Test;

import main.QuadraticFunction;

public class QuadraticFunctionTest {
	double epsilon = 1E-10;

	@Test
	public void returnX1WhenExistsTwoX() {
		assertEquals(4, QuadraticFunction.of(-1.0, 3.0, 4.0).getX1(), epsilon);
	}

	@Test
	public void returnX2WhenExistsTwoX() {
		assertEquals(-1, QuadraticFunction.of(-1.0, 3.0, 4.0).getX2(), epsilon);
	}

	@Test
	public void returnX1WhenExistOneX() {
		assertEquals(-1.0, QuadraticFunction.of(1.0, 2.0, 1.0).getX1(), epsilon);
	}

	@Test
	public void returnX2WhenExistOneX() {
		assertEquals(-1.0, QuadraticFunction.of(1.0, 2.0, 1.0).getX2(), epsilon);
	}

	@Test
	public void returnX1() {
		assertEquals(0, QuadraticFunction.of(2.0, 0.0, 0.0).getX1(), epsilon);
	}

	@Test
	public void returnX2() {
		assertEquals(0, QuadraticFunction.of(0.0, 2.0, 0.0).getX2(), epsilon);
	}

	@Test
	public void returnNegativeInfinityWhenDeltaIsLessThanZero() {
		assertEquals(Double.NEGATIVE_INFINITY, QuadraticFunction.of(0.0, 0.0, 2.0).getX1(), epsilon);
	}

	@Test
	public void returnCorrectValueWhenFunctionIsLinear() {
		assertEquals(-1, QuadraticFunction.of(0.0, 2.0, 2.0).getX2(), epsilon);
	}

	@Test(expected = IllegalArgumentException.class)
	public void returnNull() {
		assertEquals(null, QuadraticFunction.of(1.0, -4.0, 10.0));
	}
}
