

public class ImproperFraction {

	public int numerator;
	public int denominator = 1;

	public ImproperFraction(MixedNumber m) {
		this(m.digit * m.denominator + m.numerator, m.denominator);
	}

	public ImproperFraction(int num, int den) {
		this.numerator = num;
		this.denominator = den;
	}

	public ImproperFraction add(ImproperFraction other) {
		int sharedDenominator = other.denominator * this.denominator;
		int newNumerator = this.numerator * other.denominator + other.numerator * this.denominator;

		int gcd = greatestCommonDenominator(newNumerator, sharedDenominator);

		return new ImproperFraction(newNumerator / gcd, sharedDenominator / gcd);
	}

	private int greatestCommonDenominator(int... nums) {
		if (nums.length == 0) {
			throw new IllegalArgumentException("Cannot find greatest common demoninator of zero numbers.");
		}

		if (nums.length == 1) {
			return Math.abs(nums[0]);
		}

		int maxPossible = Math.abs(nums[0]);
		for (int i = 1; i < nums.length; i++) {
			maxPossible = Math.min(Math.abs(nums[i]), maxPossible);
		}

		for (int divisor = maxPossible; divisor > 1; divisor--) {
			boolean allDivisible = true;

			for (int num : nums) {
				if (num % divisor != 0) {
					allDivisible = false;
				}
			}

			if (allDivisible)
				return divisor;
		}

		return 1;
	}
}