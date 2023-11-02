import se.umu.cs.unittest.MyInt;
import se.umu.cs.unittest.TestClass;

public class Test2 implements TestClass {
    private MyInt myInt;

    public Test2() {
    }

    public void setUp() {
        // Initialize MyInt with a specific value for testing
        myInt = new MyInt();
    }

    public void tearDown() {
        // Clean up resources or reset states if necessary
        myInt = null;
    }

    // Test adding a value
    public boolean testAddValue() {
        myInt.add(5);
        return myInt.value() == 15;
    }

    // Test subtracting a value
    public boolean testSubtractValue() {
        myInt.subtract(3);
        return myInt.value() == 7;
    }

    // Test multiply a value
    public boolean testMultiplyValue() {
        myInt.multiply(2);
        return myInt.value() == 20;
    }

    // Test dividing a value
    public boolean testDivideValue() {
        myInt.divide(2);
        return myInt.value() == 5;
    }

    // Test that should fail due to division by zero
    public boolean testFailingDivideByZero() {
        myInt.divide(0);
        return false; // should not reach here
    }


    // Test that should fail due to invalid argument
    public boolean testFailingInvalidArgument() {
        myInt.setNegative(-1);
        return false;
    }

    // Test reset value
    public boolean testResetValue() {
        myInt.reset();
        return myInt.value() == 0;
    }
}
