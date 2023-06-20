import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class RunButtonAction implements ActionListener {
    private final JButton runButton;
    //private final ReadTestClass readClass;
    private String className;
    private String resultMessages;
    private final JTextField textField;
    private int successCount;
    private int failureCount;
    private int exceptionCount;

    public RunButtonAction (JTextField textField, JButton runButton) {

        this.runButton = runButton;
        this.runButton.addActionListener(this);
        this.resultMessages = "";
        this.textField = textField;

        this.successCount = 0;
        this.failureCount = 0;
        this.exceptionCount = 0;

    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(runButton)){
            ReadTestClass readClass = new ReadTestClass(textField);
            this.className = readClass.getClassName();
            System.out.println("class name" +className);
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
            }catch (ClassNotFoundException | IllegalAccessException | InstantiationException |
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
            System.out.println("current success rates"+ successCount);
        } else {
            resultMessages += methodName + ": FAIL\n";
            failureCount++;
            System.out.println("current fail rates"+ failureCount);
        }
    }



    private void processTestException(String methodName, Exception ex) {
        resultMessages += methodName + ": FAIL Generated exception: " + ex.getMessage() + "\n";
        exceptionCount++;
        System.out.println("Exception count" + exceptionCount);
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
