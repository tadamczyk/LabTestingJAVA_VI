package test;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;

import org.junit.Test;

import main.QuadraticFunction;

public class QuadraticFunctionTest1 {
	private static final double epsilon = 0.1;

	static BufferedReader rdr;
	String line;

	private QuadraticFunction q;

	@Test(expected = IllegalArgumentException.class)
	public void checkDeltaNegative() {
		q = QuadraticFunction.of(1, -3, 7);
		assertEquals(0, q.getX1(), epsilon);
	}

	@Test
	public void checkZeros() {
		q = QuadraticFunction.of(0, 0, 0);
		assertEquals(Double.NaN, q.getX1(), epsilon);
	}
}
