
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
* Stores mixed numbers as integers. The properties are always kept as perfectly simplified fractions.
* The numerator and the digit must always have the same sign
*/
public class MixedNumber {

	public int digit;
	public int numerator;
	public int denominator = 1;

	/** 
	* @param data should be an optional integer followed by a space, then an optional fraction
	*/
	public MixedNumber(String data) {
		// Split it using regex with some optional groups
		String mixedRegex = "(-?\\d+) (-?\\d+)/(\\d+)";
		Pattern mixedPattern = Pattern.compile(mixedRegex);
		Matcher mixedMatcher = mixedPattern.matcher(data);

		String fractionRegex = "(-?\\d+)/(\\d+)";
		Pattern fractionPattern = Pattern.compile(fractionRegex);
		Matcher fractionMatcher = fractionPattern.matcher(data);

		String digitRegex = "(-?\\d+)";
		Pattern digitPattern = Pattern.compile(digitRegex);
		Matcher digitMatcher = digitPattern.matcher(data);

		String stringDigit;
		String stringNumerator;
		String stringDenominator;

		if (mixedMatcher.matches()) {
			stringDigit = mixedMatcher.group(1);
			stringNumerator = mixedMatcher.group(2);
			stringDenominator = mixedMatcher.group(3);
		} else if (fractionMatcher.matches()) {
			stringDigit = "0";
			stringNumerator = fractionMatcher.group(1);
			String potentialDenominator = fractionMatcher.group(2);
			if (potentialDenominator.equals("0")) {
				throw new IllegalArgumentException("The denominator of a fraction cannot be zero");
			}

			stringDenominator = potentialDenominator;
		} else if (digitMatcher.matches()) {
			stringDigit = digitMatcher.group(1);
			stringNumerator = "0";
			stringDenominator = "1";
		} else {
			throw new IllegalArgumentException("The argument " + data + " did not match any accepted formats for mixed numbers");
		}

		int newDigit = Integer.parseInt(stringDigit);
		int newNumerator = Integer.parseInt(stringNumerator);
		int newDenominator = Integer.parseInt(stringDenominator);

		if (newNumerator < 0 && newDigit != 0) {
			throw new IllegalArgumentException("The fraction numerator should have no sign in front of it, as it is assumed identical to the sign of the digit.");
		}

		if (newDigit < 0) {
			newNumerator = -Math.abs(newNumerator);
		}

		initializeNumber(newDigit, newNumerator, newDenominator);
	}

	public MixedNumber(int[] nums) {
		if (nums.length == 0) {
			return;
		} else if (nums.length == 1) {
			initializeNumber(nums[0], 0, 1);
		} else if (nums.length == 2) {
			initializeNumber(0, nums[0], nums[1]);
		} else if (nums.length == 3) {
			initializeNumber(nums[0], nums[1], nums[2]);
		}
	}

	public MixedNumber(int dig, int num, int den) {
		initializeNumber(dig, num, den);
	}

	public MixedNumber(ImproperFraction frac) {
		int dig = frac.numerator / frac.denominator;
		int num = frac.numerator % frac.denominator;
		int den = frac.denominator;

		initializeNumber(dig, num, den);
	}

	private void initializeNumber(int dig, int num, int den) {
		if (dig < 0 && num > 0) {
			throw new IllegalArgumentException("Cannot accept differing signs due to possible ambiguity. If both are negative, then use a negative sign in front of both.");
		}

		if (dig > 0 && num < 0) {
			// This one is quite odd. I do not really expect it, and I will not allow it as it is probably difficult to predict.
			throw new IllegalArgumentException("Cannot accept differing signs due to possible ambiguity. If both are negative, then use a negative sign in front of both.");
		}

		if (den == 0 && num == 0) {
			den = 1;
		} else if (den == 0) {
			throw new IllegalArgumentException("Fractions cannot have a denominator of zero.");
		}

		this.digit = dig;
		this.numerator = num;
		this.denominator = den;
	}

	public MixedNumber add(MixedNumber other) {
		// Do the math with improper fractions, since that eliminates many edge cases for negatives

		ImproperFraction res = new ImproperFraction(this).add(new ImproperFraction(other));

		return new MixedNumber(res);
	}


	@Override
	public String toString() {
		String res = "";

		if (digit == 0 && numerator == 0) {
			return "0";
		}

		if (digit == 0) {
			return this.numerator + "/" + this.denominator;
		}

		if (numerator == 0) {
			return digit + "";
		}

		return digit + " " + Math.abs(numerator) + "/" + denominator;
	}




	public static void main(String[] args) {
		MixedNumber m1 = new MixedNumber(1, 2, 3);
		MixedNumber m2 = new MixedNumber(-4, -1, 3);
		System.out.println(m1.add(m2));
	}
}