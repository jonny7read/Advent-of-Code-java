package test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import year2017.Task10;

public class Task10Test {

	private static Task10 task;

	@BeforeClass
	public static void setup() {
		task = new Task10();
	}

	@Test
	public void testIncreaseAndWrap() {
		assertEquals(0, task.increaseAndWrap(1, 2, 3));
		assertEquals(3, task.increaseAndWrap(1, 2, 4));
		assertEquals(2, task.increaseAndWrap(3, 4, 5));
		assertEquals(3, task.increaseAndWrap(4, 6, 7));

		System.out.println(0b00000011 ^ 0b00000010);
	}

}
