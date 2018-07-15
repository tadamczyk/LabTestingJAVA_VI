import static org.assertj.core.api.Assertions.*;

import java.io.BufferedReader;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class QuadraticFunctionTest {
	@Parameters
	public static Collection<Object[]> data() {
		Object[][] c = { { 1, 0, -4, -2, 2, 4 }, { 0, 1, -2, 0.5, 0.5, 1 }, { 1, 4, 3, -3, -1, 2 }};
		return Arrays.asList(c);
	}

	static BufferedReader rdr;
	String line;

	private QuadraticFunction q;
	private double a;
	private double b;
	private double c;
	private double x1;
	private double x2;
	private double delta;

	public QuadraticFunctionTest(double a, double b, double c, double x1, double x2, double delta) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.x1 = x1;
		this.x2 = x2;
		this.delta = delta;
	}
	
	@Test
	public void checkDelta() {
		q = QuadraticFunction.of(a, b, c);
		assertThat(q.getDelta()).isCloseTo(delta, within(0.1)).isNotNaN();
	}

	@Test
	public void checkFirstX() {
		q = QuadraticFunction.of(a, b, c);
		assertThat(q.getX1()).isEqualTo(x1).isNotNaN();
	}

	@Test
	public void checkSecondX() {
		q = QuadraticFunction.of(a, b, c);
		assertThat(q.getX2()).isEqualTo(x2).isNotNaN();
	}

	@Test
	public void checkLinear() {
		q = QuadraticFunction.of(a, b, c);
		assertThat(q.getX1()).isEqualTo(x1);
	}

	@Test
	public void checkZeros() {
		q = QuadraticFunction.of(0, 0, 0);
		assertThat(q.getX2()).isNaN();
	}
}
