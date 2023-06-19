import javax.swing.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;


public class ReadTestClass {
    private JTextField textField;
    private final String interfaces = "se.umu.cs.unittest.TestClas";
    private boolean isTestInterface;
    private boolean setUp = false;
    private boolean tearDown = false;
    private String className;

    public ReadTestClass(JTextField textField){
        this.textField = textField;
        isTestInterface = false;
        className = textField.getName();
    }


    public void verifyCorrectClass() throws IllegalStateException {

        try {
            Class<?> clazz = Class.forName(className);
            Class<?> interFace = Class.forName(interfaces);
            boolean desiredInterface = interFace.isAssignableFrom(clazz);

            if(desiredInterface){
                isTestInterface = true;
                checkConstructor(className);
            }else{
                throw new RuntimeException("The interface isn't correct");
            }
        }catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }


    private void checkConstructor (String className){

        boolean hasParameter = false;

        try{
            Class<?> clazz = Class.forName(className);
            Constructor<?>[] constructors = clazz.getConstructors();

            for(Constructor<?> constructor: constructors){
                if(constructor.getParameterCount() == 0){
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
