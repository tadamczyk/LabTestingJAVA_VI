package exampleTableJBehave.steps;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.model.ExamplesTable;

import exampleTableJBehave.Power;

public class PowerSteps {
	ExamplesTable table, values;
	ArrayList<String> numbers, powersLine;
	Power power;

	@Given("the numbers: $ranksTable")
	public void givenTheNumbersnumber(ExamplesTable ranksTable) {
		this.table = ranksTable;
		this.numbers = toNumbers(ranksTable);
		System.out.println("Numbers are: " + numbers);
	}

	private ArrayList<String> toNumbers(ExamplesTable table) {
		ArrayList<String> result = new ArrayList<String>();
		for (Map<String, String> row : table.getRows()) {
			result.add(row.get("left"));
			result.add(row.get("right"));
		}
		return result;
	}

	@Then("powers are: $table")
	public void thenPowersAndSizeIsSizePowers(ExamplesTable table) {
		power = new Power();
		this.values = table;
		this.powersLine = toPowers(values);
		System.out.print(powersLine);

		String[] stringArray = stringLineToStringArray();
		List<Integer> powerList = stringArrayToIntArray(stringArray);
		int left = Integer.parseInt(numbers.get(0));
		int right = Integer.parseInt(numbers.get(1));
		List<Integer> result = power.powers(left, right);

		assertThat(powerList, is(result));
	}

	private ArrayList<String> toPowers(ExamplesTable table) {
		ArrayList<String> result = new ArrayList<String>();
		for (Map<String, String> row : table.getRows()) {
			result.add(row.get("powers"));
		}
		return result;
	}

	private String[] stringLineToStringArray() {
		String Array[] = powersLine.get(0).split(", ");
		return Array;
	}

	private List<Integer> stringArrayToIntArray(String[] stringArray) {
		List<Integer> powerList = new ArrayList<>();
		for (String a : stringArray) {
			powerList.add(Integer.parseInt(a));
		}
		return powerList;
	}
}