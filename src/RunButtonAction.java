import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class RunButtonAction implements ActionListener {
    private JButton runButton;
    private ReadTestClass readClass;
    private String className;
    private String resultMessages;
    private int successCount;
    private int failureCount;
    private int exceptionCount;

    public RunButtonAction (JTextField textField, JButton runButton) {

        this.runButton = runButton;
        this.runButton.addActionListener(this);
        this.readClass = new ReadTestClass(textField);

        this.className = readClass.getClassName();
        this.resultMessages = "";

        this.successCount = 0;
        this.failureCount = 0;
        this.exceptionCount = 0;

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(runButton)){
            readClass.verifyCorrectClass();
            try {
                Class<?> clazz = Class.forName(className);
                Object instance = clazz.getDeclaredConstructor().newInstance();
                Method setUpMethod = findMethod(clazz, "setUp");
                Method tearDownMethod = findMethod(clazz, "tearDown");

                for (Method method : clazz.getMethods()) {
                    if (isTestMethod(method)) {
                        if (setUpMethod != null && tearDownMethod != null) {
                            setUpMethod.invoke(instance);
                        }
                        invokeTestMethod(instance, method);
                        if (setUpMethod != null && tearDownMethod != null) {
                            tearDownMethod.invoke(instance);
                        }
                    }
                }
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
        resultMessages += methodName + ": FAIL Generated exception: " + ex.getMessage() + "\n";
        exceptionCount++;
    }



    public String getResultMessages(){
        return resultMessages;
    }



    public int getFailureCount(){
        return failureCount;
    }



    public int getSuccessCount(){
        return successCount;
    }



    public int getExceptionCount(){
        return exceptionCount;
    }


}
