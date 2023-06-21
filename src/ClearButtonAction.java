import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClearButtonAction implements ActionListener {
    private final JButton clearButton;
    private JTextArea textArea;
    private String resultMessages;

    public ClearButtonAction(JButton clearButton, JTextArea textArea, String resultMessages) {
        this.clearButton = clearButton;
        this.textArea = textArea;
        this.resultMessages = resultMessages;
        clearButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(clearButton)) {
            textArea.setText(""); // Clear the text in the JTextArea
            resultMessages = ""; // Clear the result messages
        }
    }


}
