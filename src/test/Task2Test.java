package test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import year2017.Task2;

public class Task2Test {

	private Task2 task2;

	@Before
	public void setup() {
		task2 = new Task2();
	}

	@Test
	public void test() {
		List<String> input = new ArrayList<String>();
		input.add("5	1	9	5");
		input.add("7	5	3");
		input.add("2	4	6	8");

		int totalSum = task2.getTotalSum(input, 1);

		assertEquals(18, totalSum);
	}

}
