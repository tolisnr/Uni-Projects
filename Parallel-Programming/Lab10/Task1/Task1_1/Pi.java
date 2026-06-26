public class Pi {

	public Pi() {
	}

	public double compute(int n) {
		double step = 1.0 / n;
		double sum = 0.0;
		for (long i = 0; i < n; i++) {
			double x = (i + 0.5) * step;
			sum += 4.0 / (1.0 + x * x);
		}
		double pi = sum * step;
		return pi;
	}
}
