import java.util.ArrayList;

public class Calculator {
	private static double EPSILON = 1e-9; // thanks ieee 754!

	private ArrayList<Double> data;

	public Calculator() {
		this.data = new ArrayList<Double>();
	}

	public ArrayList<Double> getData() {
		return data;
	}

	public void add(double value) {
		// Keep the data sorted - binary search sorting
		int lower = 0;
		int upper = data.size();
		while (lower < upper) {
			int middle = (lower + upper) / 2;
			if (data.get(middle) > value)
				upper = middle;
			else
				lower = middle + 1;
		}
		data.add(lower, value);
	}

	public boolean remove(double value) {
		for (int i = 0; i < data.size(); i++) {
			if (Math.abs(data.get(i) - value) < EPSILON) {
				data.remove(i);
				return true;
			}
		}
		return false;
	}

	public double calculateMedian() {
		if (data.size() == 0) return 0;
		if (data.size() % 2 == 0) {
			double left = data.get(data.size() / 2 - 1);
			double right = data.get(data.size() / 2);
			return (left + right) / 2;
		} else {
			return data.get(data.size() / 2);
		}
	}

	public double calculateMean() {
		if (data.size() == 0) return 0;
		double sum = 0;
		for (double num : data)
			sum += num;
		return sum / data.size();
	}

	public double calculateStd() {
		if (data.size() == 0) return 0;
		double mean = calculateMean();
		double sum2 = 0;
		for (double num : data)
			sum2 += Math.pow(num - mean, 2);
		return Math.sqrt(sum2 / data.size());
	}

	public double calculatePercentWithin(double left, double right) {
		int count = 0;
		for (double num : data)
			if (left - EPSILON < num && num < right + EPSILON)
				count++;
		return (double)count / data.size();
	}

	public String toString() {
		return data.toString();
	}
}
