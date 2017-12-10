package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import year2017.Task3;

public class Task3Test {

	@Test
	public void testPart1() {
		Task3 task = new Task3();

		assertEquals(0, task.getSteps(1));
		assertEquals(3, task.getSteps(12));
		assertEquals(2, task.getSteps(23));
		assertEquals(31, task.getSteps(1024));
	}

	@Test
	public void testPart2() {

	}

}
