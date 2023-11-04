import se.umu.cs.unittest.TestClass;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestRunner {
    private String resultMessages;
    private int successCount = 0;
    private int failureCount = 0;
    private int exceptionCount = 0;



    public String runTests(String className){
        try {
            reset();
            Class<?> testClass = Class.forName(className);
            if (!TestClass.class.isAssignableFrom(testClass)) {
                throw new IllegalArgumentException("Class does not implement TestClass interface");
            }
            if (!hasZeroArgumentConstructor(testClass)) {
                throw new IllegalArgumentException("The class must have a no-argument constructor");
            }

            TestClass testInstance = (TestClass) testClass.getDeclaredConstructor().newInstance();
            Method setUpMethod = testClass.getMethod("setUp");
            Method tearDownMethod = testClass.getMethod("tearDown");

            executeTestMethods(testClass, testInstance, setUpMethod, tearDownMethod);
            updateText();

        } catch (Exception e) {
            handleException(e);
        }
        return resultMessages;
    }


    private void handleException(Exception e) {
        if (e instanceof ClassNotFoundException) {
            showErrorMessage("It is not a valid Class");
        } else if (e instanceof IllegalArgumentException) {
            showErrorMessage("The constructor has arguments");
        } else if (e instanceof IllegalAccessException) {
            showErrorMessage("setUp or tearDown Methods cannot be null");
        } else if (e instanceof NoSuchMethodException) {
            showErrorMessage("There is no such method");
        } else if (e instanceof InvocationTargetException) {
            showErrorMessage("An error occurred inside the test method: " + e.getCause().getMessage());
        } else if (e instanceof InstantiationException) {
            showErrorMessage("Unable to instantiate the test class. Ensure it is not abstract and has a no-argument constructor.");
        } else {
            showErrorMessage("An unexpected error occurred: " + e.getMessage());
        }
    }

    private void executeTestMethods(Class<?> clazz, TestClass instance, Method setUpMethod, Method tearDownMethod) throws Exception {
        for (Method method : clazz.getMethods()) {
            if (isTestMethod(method)) {
                if (setUpMethod != null) setUpMethod.invoke(instance);
                invokeTestMethod(instance, method);
                if (tearDownMethod != null) tearDownMethod.invoke(instance);
            }
        }
    }

    private void updateText() {
        String summary = String.format("%d tests succeeded\n%d tests failed\n%d tests caused exceptions\n",
                successCount, failureCount, exceptionCount);
        resultMessages += "\n\n\n" + summary;
    }

    private void invokeTestMethod(Object instance, Method method) {
        try {
            boolean result = (boolean) method.invoke(instance);
            processTestResult(method.getName(), result);
        } catch (InvocationTargetException e) {
            processTestException(method.getName(), e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private void processTestResult(String methodName, boolean result) {
        if (result) {
            resultMessages += methodName + ": SUCCESS\n";
            successCount++;
        } else {
            resultMessages += methodName + ": FAIL\n";
            failureCount++;
        }
    }


    private boolean isTestMethod(Method method) {
        return method.getName().startsWith("test") &&
                method.getParameterCount() == 0 &&
                method.getReturnType() == boolean.class;
    }

    private boolean hasZeroArgumentConstructor(Class<?> clazz) {
        return clazz.getConstructors().length > 0 && clazz.getConstructors()[0].getParameterCount() == 0;
    }


    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void processTestException(String methodName, Exception ex) {
        Throwable cause = (ex.getCause() != null) ? ex.getCause() : ex;
        resultMessages += methodName + ": EXCEPTION - " + cause.getClass().getSimpleName() + "\n";
        exceptionCount++;
    }

    private void reset() {
        resultMessages = "";
        successCount = 0;
        failureCount = 0;
        exceptionCount = 0;
    }

}

