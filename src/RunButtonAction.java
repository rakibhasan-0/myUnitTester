import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RunButtonAction implements ActionListener {
    private final JButton runButton;
    private String className;
    private String resultMessages;
    private final JTextField textField;
    private int successCount;
    private int failureCount;
    private int exceptionCount;
    private JTextArea textArea;

    public RunButtonAction(JTextField textField, JButton runButton, JTextArea textArea) {
        this.runButton = runButton;
        this.textField = textField;
        this.textArea = textArea;
        this.successCount = 0;
        this.failureCount = 0;
        this.exceptionCount = 0;
        this.resultMessages = " ";
        this.runButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(runButton)) {
            reset();
            ReadTestClass readClass = new ReadTestClass(textField);
            this.className = readClass.getClassName();
            System.out.println("class name: " + className);
            readClass.verifyCorrectClass();

            try {
                Class<?> clazz = Class.forName(className);
                Object instance = clazz.getDeclaredConstructor().newInstance();
                Method setUpMethod = findMethod(clazz, "setUp");
                Method tearDownMethod = findMethod(clazz, "tearDown");

                for (Method method : clazz.getMethods()) {
                    if (isTestMethod(method)) {
                        System.out.println(method.getName());
                        if (setUpMethod != null && tearDownMethod != null) {
                            setUpMethod.invoke(instance);
                        }
                        invokeTestMethod(instance, method);
                        if (setUpMethod != null && tearDownMethod != null) {
                            tearDownMethod.invoke(instance);
                        }
                    }
                }

                resultMessages = resultMessages + "\n\n\n" + successCount + " tests succeeded\n"
                        + failureCount + " tests failed\n" + exceptionCount + " tests failed because of exception\n";
                textArea.setText(resultMessages);

            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException |
                     InvocationTargetException | NoSuchMethodException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    private Method findMethod(Class<?> clazz, String methodName) throws NoSuchMethodException {
        try {
            return clazz.getMethod(methodName);
        } catch (NoSuchMethodException ex) {
            return null;
        }
    }

    private boolean isTestMethod(Method method) {
        return method.getName().startsWith("test") &&
                method.getParameterCount() == 0 &&
                method.getReturnType() == boolean.class;
    }

    private void invokeTestMethod(Object instance, Method method) {
        try {
            boolean result = (boolean) method.invoke(instance);
            processTestResult(method.getName(), result);
        } catch (Exception ex) {
            processTestException(method.getName(), ex);
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

    private void processTestException(String methodName, Exception ex) {
        Throwable cause = ex.getCause();
        resultMessages += methodName + ": FAIL Generated a " + cause.getClass().getSimpleName() + "\n";
        exceptionCount++;
    }

    public void reset() {
        resultMessages = "";
        successCount = 0;
        failureCount = 0;
        exceptionCount = 0;
        textArea.setText("");
    }

    public String getResultMessages() {
        return resultMessages;
    }

    public JTextArea getTextArea() {
        return textArea;
    }
}
