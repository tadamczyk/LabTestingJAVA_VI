package test;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.junit.Before;
import org.junit.Test;

import main.Largest;

public class TestLargestDataFile {
	static BufferedReader rdr;
	String line;

	private boolean checkHash(String line) {
		if (line.startsWith("#")) {
			return true;
		}
		return false;
	}

	private boolean checkLineStatus(String line) {
		if (checkHash(line)) {
			return true;
		}
		StringTokenizer st = new StringTokenizer(line);
		if (checkMoreTokens(st)) {
			return true;
		}
		return false;
	}

	private boolean checkMoreTokens(StringTokenizer st) {
		if (!st.hasMoreTokens()) {
			return true;
		}
		return false;
	}

	private int[] convertArrayToInts(ArrayList<Integer> argumentList) {
		int[] arguments = new int[argumentList.size()];
		for (int i = 0; i < argumentList.size(); i++) {
			arguments[i] = argumentList.get(i).intValue();
		}
		return arguments;
	}

	private ArrayList<Integer> createArrayList(StringTokenizer st) {
		ArrayList<Integer> argumentList = new ArrayList<Integer>();
		while (st.hasMoreTokens()) {
			argumentList.add(Integer.valueOf(st.nextToken()));
		}
		return argumentList;
	}

	@Before
	public void setUp() throws FileNotFoundException {
		rdr = new BufferedReader(new FileReader("src/test/test.txt"));
	}

	@Test
	public void testFromFile() throws Exception {
		while ((line = rdr.readLine()) != null) {
			if (checkLineStatus(line)) {
				continue;
			}
			StringTokenizer st = new StringTokenizer(line);
			String val = st.nextToken();
			int expected = Integer.valueOf(val).intValue();
			ArrayList<Integer> argumentList = createArrayList(st);
			assertEquals(expected, Largest.largest(convertArrayToInts(argumentList)));
		}
	}
}
