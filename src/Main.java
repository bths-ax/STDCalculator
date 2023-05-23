import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Calculator calc = new Calculator();

		while (true) {
			System.out.print("Add value (STOP to stop): ");
			String value = scanner.nextLine();
			if (value.toUpperCase().equals("STOP")) break;
			calc.add(Double.parseDouble(value));
		}

		double median = calc.calculateMedian();
		double mean = calc.calculateMean();
		double std = calc.calculateStd();

		System.out.println();
		System.out.println("Median: " + median);
		System.out.println("Mean: " + mean);
		System.out.println("STD: " + std);
		System.out.println();

		while (true) {
			System.out.print("Percentage of data within [A, B] (STOP to stop): ");
			String input = scanner.nextLine();
			if (input.toUpperCase().equals("STOP")) break;
			int sepIdx = input.indexOf(",");
			double left = parseExpression(input.substring(0, sepIdx).trim(), mean, std);
			double right = parseExpression(input.substring(sepIdx + 1).trim(), mean, std);
			double percent = calc.calculatePercentWithin(left, right) * 100;
			System.out.println(String.format("Percentage of data within [%.2f, %.2f]: %.2f%%", left, right, percent));
			System.out.println();
		}
	}

	public static double parseExpression(String expr, double mean, double std) {
		Pattern pat = Pattern.compile("STD\\((-?\\d+)\\)");
		Matcher mat = pat.matcher(expr.toUpperCase());
		if (mat.matches()) {
			return mean + std * Double.parseDouble(mat.group(1));
		} else {
			return Double.parseDouble(expr);
		}
	}
}
