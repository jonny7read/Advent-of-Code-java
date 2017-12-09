package year2016;

public class Task2 {

	public static void main(String[] args) {
		String input[] = new String[5];
		input[0] = "LLULLLRLDLLLRLUURDDLRDLDURULRLUULUDDUDDLLLURRLDRRLDRRRLDUDLRDLRRDLLDUDUDUDRLUDUUDLLLRDURUDUULUDLRDUUUDUUDURLDUULLRDLULDUURUDRDDLDRLURLRURRDUURLRLUURURUUULLRLLULRUURLULURDLLRRUDLUDULDRDRLRULUURRDRULLRUUUDLRLDLUURRRURDLUDDRRUDRLUDRDLLLLLRULLDUDRLRRDDULDLRUURRRRRLDLDLRDURDRUUURDLRDDDDULURRRRDUURLULLLDLRULRDULRUDLRRLRDLLRLLLUDDLRDRURDDLLLLDUDRDLRURRDLRDDDLDULDRLRULUUDRRRUUULLLURRDDUULURULDURRLLULLDRURUUULRLRDRRUDRDRRDURRUUUULDRDDDUDLDDURLLRR";
		input[1] = "LDLRRRUURDLDDRLRRDLLULRULLLUDUUDUDLRULLDRUDRULLDULURDRDDLRURDDULLLLDLRDRDRDDURLURLURLUDRDDRDULULUDDRURRDLLDUURDRDDLRLLURRDLRDDULDLULURDRDLUDRRUUDULLULURRDUDRUUUDRULDLDURLRRUDURLDLRRUURRRURDLUDRLDUDRRUDUURURUDDUUDRDULRDLUDRRRLDRURLLRDDDLUDRDUDURDDDRRDDRRRLLRRDDLDDLRUURRURDLLDRLRRDLLUDRRRURURLRDRLLRLRLRULLRURLDLRRULLRRRDULUUULDRDLLURDDLDLRDRLUUDLLUDDLDRRLDLRUDRUDLLUURLLULURUDUDRLULLUDRURDDLDLDDUDLRDDRRURLRLLUDDUDRUURRURRULDRLDDRLLRRLDDURRDLDULLLURULLLRUURLRRRRUUULRLLLURRLRLRUDRDUUUDUUUDDLULLDLLLLDLDRULDRUUULDDDLURLDLRLULRUDDDDURDDLU";
		input[2] = "RURLURRDLDULLULDDDLRUULLUURLRUDRUDRRUDDLDDDDRRDLRURLRURLDDDUDDUURRDRULDRRRULRDRDDLRUDULRLURDUUDRRLDLRDRURDLDRRRRDRURUUDDDLLRDRDUDUDUDLLULURULRRLRURUULUULDDDDURULRULLRUDUURLURDUDLUDLUDRLLDUUDUULRLRLUUDRDULDULRURDRRRULRUDLRURDDULUDULLRLRURURUULLULDRURLLRRUUDDUUURRDLURUURULRDRRDDUDULRDDLUDLURURUURDRULLRDDLLRDDLDRDUDRRDLUURRLRLUURRULUDURLDDRLLURRDDDLDDRURULLDDRLUDDLRLURDUDULLRDULLLDLLUDDRUDRUDDUUDRDRULRL";
		input[3] = "RLRDRDULULUDLUDRDRLUDLDLLUDURULDDDUDLRURLLRLRLDLDRLDURDLRRURLULLULURLLDRRDRLUDRLRDLLULRULURRURURUULRDUDLLRDLRRRRRLUURDRRRDLRUDLLDLLDLRUUUDLLLDDDLRDULLRUUDDRLDDURRRDLRLRLDDDDLRDRULLUURUUDRRLLRLLRDDLLRURRRRDRULRRLLRLLLRLDRRLDDDURRURLDURUURRLRLRLDRURULLRLRUDLDUURDLLRLDLURUUUDLLRDRDDDDDDRLDRRRLRRRRURUDLDDRDLLURUDLRRLDDDLUDUDUULRDULULUDDULUUDLLLLRLDDUUULRLRDULURDURRRURRULURRRDRDLDDURDLURUDURRRDDRLRLUDLUDDLUULLDURLURDDUDDLRUUUDRLLDRURL";
		input[4] = "ULUDLLUDDULRUURDRURDUDUDLUURDDDRRLUDURURDRURRLDRDURLRLLRRDDRRDRRRUULURUDURUDULRRRRDDLDURRLRRDUDDDRLLLULDRLRLURRDUURDURRRURRDLUDUDDRLDLURRRDDRLLRDRDDRDURRRRLURRLUDDURRULRUDUDULDRUDDRULLUUULDURRRLDRULLURULLRUDLDUDDLDULDLUUDRULULDLLDRULLRUULDUDUUDRLRRLDLUULUDLLDDRLRRDDLLURURDULRRDDRURDRLRLULDLDURULLUUUDURURDLDUDDDDUUULUDLUURRULLDLRLURDLURLRLDDURRLDDRRRDUUULLUULDLLDLLDDRLRRUDLULDRLULDULULRRLRULUUURURUUURDUUDDURLLUDDRLRDDLUURRUULRDLDDRLULUULRDRURLUURDRDUURUDLRR";

		String code = "";
		String position = "5";
		for (String moveset : input) {
			for (char move : moveset.toCharArray()) {
				position = getPosition(position, move);
			}
			code += position;
		}
		System.out.println("Code: " + code);
	}

	private static String getPosition(String position, char move) {
		switch (position) {
			case "1":
				switch (move) {
					case 'U':
						return position;
					case 'L':
						return position;
					case 'R':
						return position;
					case 'D':
						return "3";
				}
			case "2":
				switch (move) {
					case 'U':
						return position;
					case 'L':
						return position;
					case 'R':
						return "3";
					case 'D':
						return "6";
				}
			case "3":
				switch (move) {
					case 'U':
						return "1";
					case 'L':
						return "2";
					case 'R':
						return "4";
					case 'D':
						return "7";
				}
			case "4":
				switch (move) {
					case 'U':
						return position;
					case 'L':
						return "3";
					case 'R':
						return position;
					case 'D':
						return "8";
				}
			case "5":
				switch (move) {
					case 'U':
						return position;
					case 'L':
						return position;
					case 'R':
						return "6";
					case 'D':
						return position;
				}
			case "6":
				switch (move) {
					case 'U':
						return "2";
					case 'L':
						return "5";
					case 'R':
						return "7";
					case 'D':
						return "A";
				}
			case "7":
				switch (move) {
					case 'U':
						return "3";
					case 'L':
						return "6";
					case 'R':
						return "8";
					case 'D':
						return "B";
				}
			case "8":
				switch (move) {
					case 'U':
						return "4";
					case 'L':
						return "7";
					case 'R':
						return "9";
					case 'D':
						return "C";
				}
			case "9":
				switch (move) {
					case 'U':
						return position;
					case 'L':
						return "8";
					case 'R':
						return position;
					case 'D':
						return position;
				}
			case "A":
				switch (move) {
					case 'U':
						return "6";
					case 'L':
						return position;
					case 'R':
						return "B";
					case 'D':
						return position;
				}
			case "B":
				switch (move) {
					case 'U':
						return "7";
					case 'L':
						return "A";
					case 'R':
						return "C";
					case 'D':
						return "D";
				}
			case "C":
				switch (move) {
					case 'U':
						return "8";
					case 'L':
						return "B";
					case 'R':
						return position;
					case 'D':
						return position;
				}
			case "D":
				switch (move) {
					case 'U':
						return "B";
					case 'L':
						return position;
					case 'R':
						return position;
					case 'D':
						return position;
				}
		}
		return position;
	}

}
