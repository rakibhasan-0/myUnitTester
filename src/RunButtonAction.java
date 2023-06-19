import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RunButtonAction implements ActionListener {
    private JButton runButton;
    private  ReadTestClass readClass;
    private String className;


    public RunButtonAction (JTextField textField, JButton runButton){
        this.runButton = runButton;
        runButton.addActionListener(this);
        ReadTestClass readClass = new ReadTestClass(textField);
        className = readClass.getClassName();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource().equals(runButton)){
            readClass.verifyCorrectClass();
            if(readClass.checkIfSetUpAndTearDownExists()){
                try {
                    Class<?> clazz = Class.forName(className);


                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }else{

            }
        }

    }



}
