package main;

import java.util.Arrays;

public class Sorting {

	public int[] table;

	public Sorting(int[] tab) {
		table = tab;
	}

	public void sortArray(char option) {
		if (table == null)
			throw new IllegalArgumentException();
		if (table.length == 0)
			throw new NullPointerException();

		if (option == 'M') {
			Arrays.sort(table);
		} else if (option == 'R') {
			Arrays.sort(table);
			int temp;
			for (int i = 0; i < table.length / 2; i++) {
				temp = table[i];
				table[i] = table[table.length - 1 - i];
				table[table.length - 1 - i] = temp;
			}
		} else
			throw new IllegalArgumentException();
	}

	public String showArray() {
		return Arrays.toString(table);
	}

	public boolean isSorted() {
		if (table.length == 0) {
			throw new IllegalArgumentException();
		}
		for (int i = 0; i < table.length - 1; i++) {
			if (table[i] > table[i + 1]) {
				return false;
			}
		}
		return true;
	}
}
