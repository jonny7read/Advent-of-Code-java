package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import year2017.Task9;

public class Task9Test {

	@Test
	public void test() {
		Task9 task = new Task9();

		assertEquals(1, task.getScore("{}"));
		assertEquals(6, task.getScore("{{{}}}"));
		assertEquals(5, task.getScore("{{},{}}"));
		assertEquals(16, task.getScore("{{{},{},{{}}}}"));
		assertEquals(1, task.getScore("{<a>,<a>,<a>,<a>}"));
		assertEquals(9, task.getScore("{{<ab>},{<ab>},{<ab>},{<ab>}}"));
		assertEquals(9, task.getScore("{{<!!>},{<!!>},{<!!>},{<!!>}}"));
		assertEquals(3, task.getScore("{{<a!>},{<a!>},{<a!>},{<ab>}}"));
	}

}
