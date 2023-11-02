import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestExecutionPanel implements ActionListener {
    private final JButton runButton;
    private final JTextArea textArea;
    private TestRunnerWorker worker;
    private String resultMessages;

    private JButton clearButton;

    private JTextField textField;

    public TestExecutionPanel(JButton runButton, JTextArea textArea, JButton clearButton, JTextField textField) {
        this.runButton = runButton;
        this.textArea = textArea;
        this.clearButton = clearButton;
        this.textField = textField;

        this.runButton.addActionListener(this);
        this.clearButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == runButton) {
            String className = textField.getText().trim();
            try {
                worker = new TestRunnerWorker(className, textArea);
                worker.execute();
            } catch (Exception ex) {
                showErrorMessage("Error running tests: " + ex.getMessage());
            }
        }
        if (e.getSource().equals(clearButton)) {
            textArea.setText("");
            resultMessages = "";
        }
    }

    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public String getResultMessages(){
        return resultMessages;
    }

}

