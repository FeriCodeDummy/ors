import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
// import org.mariuszgromada.math.mxparser.Expression;
public class example {

	public static void main(String[] args) {
		handler();
	}

	private static String calculate(String expression) {

		// Expression exp = new Expression(expression);
		// expressinon = String.valueOf(exp.calculate());

		return expression;
	}

	private static void handler() {

		while (true) {
			System.out.print("operacija: ");
			Scanner s = new Scanner(System.in);

			switch (s.next()) {
				case "rocno":
					Scanner d = new Scanner(System.in);
					String racun = d.nextLine();
					// 3 + 2^(2 * 8)
					System.out.println(calculate(racun));
					break;
				case "datoteka":
					System.out.print("Datoteka: ");
					Scanner e = new Scanner(System.in);
					String name = e.next();
					try {
						BufferedReader csvReader = new BufferedReader(new FileReader(name));
						String row = csvReader.readLine();

						ArrayList<String> data = new ArrayList<>();
						while (row != null) {
							data.add(row);
							row = csvReader.readLine();
						}
						for (String string : data) {
							System.out.println(string + "=" + calculate(string));
						}
					} catch (IOException r) {
						System.out.println(String.valueOf(r));
					}
					break;

				case "rocnaLogika": // Logična vrata
					break;
				case "rocnoStevil": // Pretvarjanje števil
					break;
				case "autoStevila":
					System.out.print("Datoteka: ");
					Scanner f = new Scanner(System.in);
					String ime = f.next();
					try {
						BufferedReader csvReader = new BufferedReader(new FileReader(ime));
						String row = csvReader.readLine();
						// "a b c" -> .split("") {"a"," ","b", " ", "c"}
						ArrayList<String[]> data = new ArrayList<>();
						while (row != null) {
							data.add(row.split(" "));
							row = csvReader.readLine();
						}
						for (String[] string : data) {
							System.out.println(convertSystem(string[0],string[1],string[2]));
						}
					} catch (IOException r) {
						System.out.println(String.valueOf(r));
					}
					break;


			}

		}

	}

	private static String convertSystem(String from, String expression, String to) {

		switch (from) {
			case "DEC":
				switch (to) {
					case "DEC": // Če sta from in to isti vrni število
						return expression;

					case "BIN":
						return Integer.toBinaryString(Integer.valueOf(expression)); // število v desetiškem sistemu funkcija Integer.toBinaryString pretvori v niz dvojiškega sistem

					case "HEX":
						return Integer.toHexString(Integer.valueOf(expression)); // število v desetiškem sistemu funkcija Integer.toHexString pretvori v niz šestnajstiškega sistema

					case "OCT":
						return Integer.toOctalString(Integer.valueOf(expression)); // število v desetiškem sistemu funkcija Integer.toOctalString pretvori v niz osmiškega sistem

				}
			case "BIN":  // Če sta from in to isti vrni število
				switch (to) {
					case "BIN":
						return expression;

					case "DEC": // parseInt(niz, številskki sistem) spremeni vrednost v desetiški sistem, ki ga nato podamo v zgornje funkcije
						return String.valueOf(Integer.parseInt(expression, 2));

					case "HEX":
						return Integer.toHexString(Integer.parseInt(expression, 2));

					case "OCT":
						return Integer.toOctalString(Integer.parseInt(expression, 2));
				}

			case "OCT":  // Če sta from in to isti vrni število
				switch (to) {
					case "OCT":
						return expression;

					case "DEC":
						return String.valueOf(Integer.parseInt(expression, 8));

					case "HEX":
						return Integer.toHexString(Integer.parseInt(expression, 8));

					case "BIN":
						return Integer.toBinaryString(Integer.parseInt(expression, 8));
				}

			case "HEX":  // Če sta from in to isti vrni število
				switch (to) {
					case "HEX":
						return expression;

					case "DEC":
						return String.valueOf(Integer.parseInt(expression, 16));

					case "BIN":
						return Integer.toBinaryString(Integer.parseInt(expression, 16));

					case "OCT":
						return Integer.toOctalString(Integer.parseInt(expression, 16));
				}

			default:
				// Prišlo je do neke nove napake, ki se načeloma ne bi smela pojaviti
				return "Unknown error";

		}

	}

	private static boolean isbin(String number) { // Preveri če je število dvojiško
		try{
			Integer.parseInt(number, 2);
			return true;
		} catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}

	private static String logicnaCrata(String num1, String num2, String operacija) {
		if (isbin(num1) && isbin(num2)) {
			String[] values = refomatBinaryNumber(num1, num2); // Enako dolgi
			num1 = values[0];
			num2 = values[1];
			String[] sArr1 = num1.split("");
			String[] sArr2 = num2.split("");

			// Arr[0] = št.1
			// Arr[1] = št2

			String result = "";
			switch (operacija) {
				case "OR":
					for (int i = 0; i < sArr1.length; i++) {
						if (sArr1[i].equals("1") || sArr2[i].equals("1")) {
							result += "1";
						} else {
							result += "0";
						}
					}
					break;

				case "AND":
					for (int i = 0; i < sArr1.length; i++) {
						if (sArr1[i].equals("1") && sArr2[i].equals("1")) {
							stringBuilder.append("1");
						} else {
							stringBuilder.append("0");
						}
					}
					break;

				case "NOR":
					for (int i = 0; i < sArr1.length; i++) {
						if (sArr1[i].equals("1") || sArr2[i].equals("1")) {
							stringBuilder.append("0");
						} else {
							stringBuilder.append("1");
						}
					}
					break;

				case "NAND":
					for (int i = 0; i < sArr1.length; i++) {
						if (sArr1[i].equals("1") && sArr2[i].equals("1")) {
							stringBuilder.append("0");
						} else {
							stringBuilder.append("1");
						}
					}
					break;
				case "XOR":
					for (int i = 0; i < sArr1.length; i++) {
						if ((sArr1[i].equals("1") && sArr2[i].equals("0")) || (sArr1[i].equals("0") && sArr2[i].equals("1"))) {
							stringBuilder.append("1");
						} else {
							stringBuilder.append("0");
						}
					}
					break;


				case "XNOR":

					for (int i = 0; i < sArr1.length; i++) {
						if ((sArr1[i].equals("1") && sArr2[i].equals("0")) || (sArr1[i].equals("0") && sArr2[i].equals("1"))) {
							stringBuilder.append("0");
						} else {
							stringBuilder.append("1");
						}
					}
					break;

				case "NOT":
					for (String s : sArr1) {
						if (s.equals("1")) {
							stringBuilder.append("0");
						} else {
							stringBuilder.append("1");
						}
					}
					break;
				default:
					stringBuilder.append("Error somehow");
					break;
			}

			return result;

		} else {
			return "Not binary number";
		}
	}

	private static String[] refomatBinaryNumber(String num1, String num2) { // Če je eno število daljše od drugega, tistemu ki je krajše doda na prvo mesto toliko '0' da sta enako dolga

		if (num1.split("").length < num2.split("").length) {
			StringBuilder sb = new StringBuilder();
			sb.append(num1);
			for (int i = 0; i < num2.split("").length - num1.split("").length; i++) {
				sb.insert(0, "0");
			}
			return new String[]{sb.toString(), num2};

		} else if (num1.split("").length > num2.split("").length) {
			StringBuilder sb = new StringBuilder();
			sb.append(num1);
			for (int i = 0; i < num1.split("").length - num2.split("").length; i++) {
				sb.insert(0, "0");
			}
			return new String[]{num1, sb.toString()};
		}
		return new String[]{num1, num2};
	}
}