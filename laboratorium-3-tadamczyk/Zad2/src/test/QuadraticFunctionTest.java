package test;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;

import org.junit.Before;
import org.junit.Test;

import main.QuadraticFunction;

public class QuadraticFunctionTest {
	private static final double epsilon = 0.1;

	static BufferedReader rdr;
	String line;

	private QuadraticFunction q;
	private double a;
	private double b;
	private double c;
	private double x1;
	private double x2;

	@Test(expected = IllegalArgumentException.class)
	public void checkDeltaNegative() {
		q = QuadraticFunction.of(1, -5, 7);
		assertEquals(0, q.getX1(), epsilon);
	}

	@Test
	public void checkDeltaZero() {
		q = QuadraticFunction.of(a, b, c);
		assertEquals(x1, q.getX1(), epsilon);
	}

	@Test
	public void checkFirstX() {
		q = QuadraticFunction.of(a, b, c);
		assertEquals(x1, q.getX1(), epsilon);
	}

	@Test
	public void checkLinear() {
		q = QuadraticFunction.of(a, b, c);
		assertEquals(x1, q.getX1(), epsilon);
	}

	@Test
	public void checkSecondX() {
		q = QuadraticFunction.of(a, b, c);
		assertEquals(x2, q.getX2(), epsilon);
	}

	@Test
	public void checkZeros() {
		q = QuadraticFunction.of(0, 0, 0);
		assertEquals(Double.NaN, q.getX1(), epsilon);
	}

	@Before
	public void readFile() throws Exception {
		rdr = new BufferedReader(new FileReader("src/test/wyniki.txt"));
		while ((line = rdr.readLine()) != null) {
			if (line.startsWith("#")) {
				continue;
			}
			StringTokenizer st = new StringTokenizer(line);
			if (!st.hasMoreTokens() || st.countTokens() != 5) {
				continue;
			}
			a = Double.valueOf(st.nextToken());
			b = Double.valueOf(st.nextToken());
			c = Double.valueOf(st.nextToken());
			x1 = Double.valueOf(st.nextToken());
			x2 = Double.valueOf(st.nextToken());
		}
	}
}
