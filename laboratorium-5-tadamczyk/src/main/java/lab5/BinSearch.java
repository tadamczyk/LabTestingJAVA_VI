package lab5;

public class BinSearch {
	public static int binarySearch(int[] nums, int check) {
		if (nums.length == 0)
			return -1;
		int highest = nums.length - 1;
		int lower = 0;
		while (highest >= lower) {
			int guess = (lower + highest) / 2;
			if (nums[guess] > check) {
				highest = guess - 1;
			} else if (nums[guess] < check) {
				lower = guess + 1;
			} else {
				return guess;
			}
		}
		return -1;
	}
}