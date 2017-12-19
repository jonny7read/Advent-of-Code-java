package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import year2017.Task8;

public class Task8Test {

	@Test
	public void test() {
		Task8 task = new Task8();
		task.run("2017Task8TestInput.txt");

		assertEquals(1, task.getMaxAtEnd());
		assertEquals(10, task.getMaxEver());
	}

}
