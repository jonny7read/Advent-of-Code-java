package year2016;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Task5 {

	public static void main(String[] args) {
		new Task5();
	}

	public Task5() {
		String rawInput = "ugkcyxxp";
		int index = 0;
		String hash = "";
		String partAPassword = "";
		String partBPassword = "";

		while (index < 10) {
			// construct input
			String input = rawInput + index;

			hash = getHash(input);

			// check hex output for 5 leading zeros
			if (checkOutput(hash)) {
				partAPassword += hash.toCharArray()[5];
				if (partAPassword.length() == 8) {
					System.out.println(partAPassword);
					System.exit(0);
				}
			} else {
				index++;
			}
		}
	}

	private boolean checkOutput(String output) {
		return output.startsWith("00000");
	}

	private String getHash(String input) {
		MessageDigest md = null;
		try {
			// get digest
			md = MessageDigest.getInstance("MD5");

			// add input to digest
			md.update(input.getBytes());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			System.exit(0);
		}

		// generate hash
		byte[] hash = md.digest();

		// convert to hex
		String hexString = "";
		for (byte b : hash) {
			hexString += Integer.toHexString(b);
			if (hexString.length() >= 6) {
				break;
			}
		}
		System.out.println(hexString);
		return hexString;
	}
}
