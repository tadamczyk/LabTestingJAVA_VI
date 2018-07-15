package lab5;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class BinSearchTest {

	@Test
	public void TestNrOne() {
		int expected = 2;
		int[] tab = { 1, 2, 3, 4, 5 };
		assertEquals(expected, BinSearch.binarySearch(tab, 3));
	}

	@Test
	public void TestNrTwo() {
		int expected = -1;
		int[] tab = {};
		assertEquals(expected, BinSearch.binarySearch(tab, 1));
	}

	@Test
	public void TestNrThree() {
		int expected = 2;
		int[] tab = { -4, -3, -2, -1, 0 };
		assertEquals(expected, BinSearch.binarySearch(tab, -2));
	}

	@Test
	public void TestNrFour() {
		int expected = 2;
		int[] tab = { -4, -2, 0, 2, 4 };
		assertEquals(expected, BinSearch.binarySearch(tab, 0));
	}
}