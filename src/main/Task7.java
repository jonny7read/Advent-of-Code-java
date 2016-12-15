package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * In this class I refer to <i>supernet</i> and <i>hypernet</i>, which are terminology defined by
 * the author of the challenge.<br>
 * <br>
 * Given an example input line of <tt>abcd[efgh]ijkl</tt>:
 * <ul>
 * <li>the character sequences <b>outside</b> the brackets are <i>supernet</i> sequences, i.e.
 * <tt>abcd</tt> and <tt>ijkl</tt></li>
 * <li>the character sequences <b>inside</b> the brackets are <i>hypernet</i> sequences, i.e.
 * <tt>efgh</tt></li>
 * </ul>
 * 
 * @author Jonny Read
 *
 */
public class Task7 {

	public static void main(String[] args) {
		new Task7(false);
	}

	public Task7(boolean isTest) {
		if (isTest) {
			ArrayList<String> part1Lines = getPart1TestLines();
			ArrayList<String> part2Lines = getPart2TestLines();

			System.out.println(part1Lines.size() + " lines for part 1");
			System.out.println(part2Lines.size() + " lines for part 2");

			ArrayList<String> tlsIPs = getTLSIPs(part1Lines);
			ArrayList<String> sslIPs = getSSLIPs(part2Lines);

			System.out.println("TSL IPs (part 1): " + tlsIPs.size());
			System.out.println("SSL IPs (part 2): " + sslIPs.size());
		} else {
			ArrayList<String> lines = getInput("Task7Input.txt");

			System.out.println(lines.size() + " lines read from file");

			// ArrayList<String> tlsIPs = getTLSIPs(lines);
			// System.out.println("TSL IPs (part 1): " + tlsIPs.size());

			ArrayList<String> sslIPs = getSSLIPs(lines);
			System.out.println("SSL IPs (part 2): " + sslIPs.size());
		}

	}

	private ArrayList<String> getPart1TestLines() {
		ArrayList<String> testLines = new ArrayList<String>();

		/* Part 1 */
		// provided examples
		testLines.add("abba[mnop]qrst"); // valid - supernet only
		testLines.add("abcd[bddb]xyyx"); // not valid - both
		testLines.add("aaaa[qwer]tyui"); // not valid - neither
		testLines.add("ioxxoj[asdfgh]zxcvbn"); // valid - supernet only (with extra characters)

		// created by me to test the only remaining case
		testLines.add("abcd[abba]efgh"); // not valid - hypernet only

		return testLines;
	}

	private ArrayList<String> getPart2TestLines() {
		ArrayList<String> testLines = new ArrayList<String>();

		// provided
		testLines.add("aba[bab]xyz2"); // valid - both
		testLines.add("xyx[xyx]xyx"); // not valid - no bab
		testLines.add("aaa[kek]eke"); // valid - both
		testLines.add("zazbz[bzb]cdb"); // valid - both

		// created by me to test remaining cases
		testLines.add("abc[def]ghi"); // not valid - neither

		return testLines;
	}

	private ArrayList<String> getInput(String fileName) {
		ClassLoader classLoader = getClass().getClassLoader();
		URL url = classLoader.getResource(fileName);
		File file = new File(url.getFile());
		Scanner scanner = null;
		ArrayList<String> lines = new ArrayList<String>();

		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(0);
		}

		while (scanner.hasNextLine()) {
			lines.add(scanner.nextLine());
		}

		return lines;
	}

	private ArrayList<String> getTLSIPs(ArrayList<String> lines) {
		ArrayList<String> validIPs = new ArrayList<>();
		for (String line : lines) {
			System.out.println(line);

			ArrayList<String> words = getWords(line);

			boolean supernetABBA = false;
			boolean hypernetABBA = false;

			for (int i = 0; i < words.size(); i++) {
				String abba = getABBA(words.get(i));
				if (abba != null) {
					if ((i + 1) % 2 == 0) {
						hypernetABBA = true;
						System.out.println("- hypernet: " + abba);
					} else {
						supernetABBA = true;
						System.out.println("- supernet: " + abba);
					}
				}
			}

			if (supernetABBA && !hypernetABBA) {
				validIPs.add(line);
			}
		}
		return validIPs;
	}

	private ArrayList<String> getWords(String line) {
		ArrayList<String> words = new ArrayList<String>();
		int latestPos = 0;
		while (true) {
			int openBracket = line.indexOf('[', latestPos);
			int closeBracket = line.indexOf(']', latestPos);

			if (words.isEmpty()) { // first supernet
				words.add(line.substring(0, openBracket));
				latestPos = openBracket;
			} else if (openBracket == -1) { // last supernet
				words.add(line.substring(closeBracket + 1));
				break;
			} else if (openBracket < closeBracket) { // hypernet
				words.add(line.substring(openBracket + 1, closeBracket));
				latestPos = closeBracket;
			} else { // middle supernet
				words.add(line.substring(closeBracket + 1, openBracket));
				latestPos = openBracket;
			}
		}
		return words;
	}

	/**
	 * Returns a substring of 4 letters from the given word, if:
	 * <ul>
	 * <li>the first letter is the same as the last letter</li>
	 * <li>the second letter is the same as the third letter</li>
	 * <li>the first letter is different to the second letter</li>
	 * </ul>
	 * 
	 * @param word
	 *            the string of letters to check
	 * @return the string of letters that matches the given rules, else null
	 */
	private String getABBA(String word) {
		String abba = null;
		char[] letters = word.toCharArray();
		for (int i = 0; i < letters.length - 3; i++) { // for each group of 4 consecutive letters
			boolean firstSameAsLast = letters[i] == letters[i + 3];
			boolean secondSameAsThird = letters[i + 1] == letters[i + 2];
			boolean firstDiffToSecond = letters[i] != letters[i + 1];

			if (firstSameAsLast && firstDiffToSecond && secondSameAsThird) {
				abba = word.substring(i, i + 4);
			}
		}
		return abba;
	}

	private ArrayList<String> getSSLIPs(ArrayList<String> lines) {
		ArrayList<String> validIPs = new ArrayList<String>();

		for (String line : lines) {
			ArrayList<String> words = getWords(line);

			boolean hasBAB = false;
			String aba = null;
			String bab = null;

			for (int i = 0; i < words.size(); i += 2) {
				aba = getABA(words.get(i));
				if (aba != null) {
					for (int j = 1; j < words.size(); j += 2) {
						bab = getBAB(words.get(j), aba);

						if (bab != null) {
							hasBAB = true;

							break;
						}
					}
					if (hasBAB) {
						break;
					}
				}
			}
			if (hasBAB) {
				validIPs.add(line);
			} else {
				System.out.println(line);
			}

		}

		return validIPs;
	}

	/**
	 * Returns a substring of 3 letters from the given word, if:
	 * <ul>
	 * <li>the first letter is the same as the last letter</li>
	 * <li>the first letter is different to the second letter</li>
	 * </ul>
	 * 
	 * @param word
	 *            the string of letters to check
	 * @return the string of letters that matches the given rules, or <tt>null</tt> if no match is
	 *         found.
	 */
	private String getABA(String word) {
		String aba = null;
		char[] letters = word.toCharArray();
		for (int i = 0; i < letters.length - 2; i++) {
			boolean firstSameAsLast = letters[i] == letters[i + 2];
			boolean firstDiffToSecond = letters[i] != letters[i + 1];

			if (firstSameAsLast && firstDiffToSecond) {
				aba = word.substring(i, i + 3);
			}
		}
		return aba;

	}

	/**
	 * Returns a substring of 4 letters from the given <tt>word</tt>, if:
	 * <ul>
	 * <li>the first and third letters match the given <tt>outsideLetter</tt></li>
	 * <li>the middle letter matches the given <tt>middleLetter</tt></li>
	 * </ul>
	 * 
	 * @param word
	 *            the string of letters to check
	 * @param aba
	 *            the previously found aba, so the letters can be checked
	 * @return the string of letters that matches the given rules
	 *
	 */
	private String getBAB(String word, String aba) {
		String bab = null;
		String toMatch = "" + aba.charAt(1) + aba.charAt(0) + aba.charAt(1);

		for (int i = 0; i < word.length() - 2; i++) {
			if (word.substring(i, i + 3).equals(toMatch)) {
				bab = word.substring(i, i + 3);
			}
		}

		return bab;
	}
}
