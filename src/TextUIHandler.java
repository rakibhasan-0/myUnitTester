import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextUIHandler implements ActionListener, TestResultListener {
    private final JTextArea textArea;
    private final JButton clearButton;
    private TestResultListener testResultListener;
    public TextUIHandler(JTextArea textArea, JButton clearButton) {
        this.textArea = textArea;
        this.clearButton = clearButton;
        this.clearButton.addActionListener(this);
    }

    // Handles the clear button action
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == clearButton) {
            textArea.setText("");
        }
    }

    // Handles the update of the text area when a test is complete
    @Override
    public void onTestComplete(String testResults) {
        textArea.setText(testResults);
    }

    // Handles the update of the text area when an error occurs
    @Override
    public void onTestError(String errorMessage) {
        JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
