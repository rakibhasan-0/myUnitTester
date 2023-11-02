package se.umu.cs.unittest;

public class MyInt {
	private int value;

	public MyInt() {
		value = 0;
	}

	public void increment() {
		value++;
	}

	public void decrement() {
		value--;
	}

	public void add(int addValue) {
		value += addValue;
	}

	public void subtract(int subValue) {
		value -= subValue;
	}

	public void multiply(int factor) {
		value *= factor;
	}

	public void divide(int divisor) {
		if (divisor == 0) {
			throw new ArithmeticException("Division by zero");
		}
		value /= divisor;
	}

	public void reset() {
		value = 0;
	}

	public int value() {
		return value;
	}

	public void setNegative(int value) {
		if (value < 0) {
			throw new IllegalArgumentException("Negative values not allowed");
		}
	}
}
