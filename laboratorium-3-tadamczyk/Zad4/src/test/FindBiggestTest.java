package test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import main.FindBiggest;

@RunWith(Parameterized.class)
public class FindBiggestTest {
	@Parameters
	public static Collection<int[]> testData() {
		int[][] data = new int[][] { { 1, 0, -4, -2, 2, 2 }, { 1, 1, 1, 1, 1, 1 }, { 6, 4, 8, 4, 8 }, { 2, 2 } };
		return Arrays.asList(data);
	}

	int[] values;

	FindBiggest findBiggestFunction;

	public FindBiggestTest(int... values) {
		this.values = values;
	}

	@Test
	public void findBiggest() {
		findBiggestFunction = new FindBiggest(values);
		assertEquals(findBiggestFunction.biggest, findBiggestFunction.findBiggest());
	}
}
