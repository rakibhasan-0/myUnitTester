import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClearText {
    private JTextArea textArea;
    private JButton clearButton;

    public ClearText(JTextArea textArea, JFrame frame) {
        this.textArea = textArea;
        JPanel clearPanel = new JPanel();
        clearButton = new JButton("CLEAR");
        clearPanel.setLayout(new FlowLayout(FlowLayout.TRAILING));
        clearPanel.add(clearButton);
        clearPanel.setBorder(new EmptyBorder(12, 0, 0, 0)); // Add an empty border at the bottom
        frame.add(clearPanel, BorderLayout.SOUTH);
    }

    public void clearText() {
        textArea.setText("");
    }

    public JButton getClearButton() {
        return clearButton;
    }




}
