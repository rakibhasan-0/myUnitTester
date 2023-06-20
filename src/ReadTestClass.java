import javax.swing.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;


public class ReadTestClass {
    private JTextField textField;
    private final String interfaces = "se.umu.cs.unittest.TestClas";
    private boolean isTestInterface;
    private boolean setUp = false;
    private boolean tearDown = false;
    private final String className;


    public ReadTestClass(JTextField textField){
        this.textField = textField;
        isTestInterface = false;
        className = textField.getText();
    }


    public void verifyCorrectClass() throws IllegalStateException {
        try {
            Class<?> clazz = Class.forName(className);
            System.out.println("The class name is " + className);
           // Class<?> interFace = Class.forName(interfaces);
           // boolean desiredInterface = interFace.isAssignableFrom(clazz);

            checkConstructor(className);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Failed to load class: " + className, e);
        }
    }



    private void checkConstructor (String className){

        boolean hasParameter = false;

        try{
            // In that reading class I was skipping interface checking.
            // Because I was getting up-expecting errors messages.

            Class<?> clazz = Class.forName(className);
            Constructor<?>[] constructors = clazz.getConstructors();

            for(Constructor<?> constructor: constructors){
                if(constructor.getParameterCount() > 0){
                    hasParameter = true;
                    break;
                }
            }

            if(hasParameter){
                throw new IllegalArgumentException("The constructor has parameters");
            }

        }catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }



    public boolean checkIfSetUpAndTearDownExists (){
        try {
            Class<?> clazz = Class.forName(this.className);
            Method[] methods = clazz.getDeclaredMethods();

            for(Method method : methods){
                if(method.getName().equals("setUp") && method.getParameterCount() == 0){
                    setUp = true;
                }
                else if(method.getName().equals("tearDown") && method.getParameterCount() == 0){
                    tearDown = true;
                }
            }

            return setUp && tearDown;
        }catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }



    public String getClassName(){
        return className;
    }

}
