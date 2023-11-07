package View;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class RunButtonPanel {
    private JPanel panelForRunButton;
    private JButton runButton;
    private JTextField textField;

    public RunButtonPanel() {
        panelForRunButton = new JPanel(new BorderLayout());
        runButton = new JButton("RUN");
        textField = new JTextField();
        setupButtonPanel();
    }

    private void setupButtonPanel() {
        textField.setPreferredSize(new Dimension(100, 30));
        textField.setFont(new Font("Montserrat", Font.PLAIN, 12));
        textField.setBorder(new LineBorder(Color.BLACK));
        panelForRunButton.add(textField, BorderLayout.CENTER);
        panelForRunButton.add(runButton, BorderLayout.EAST);
    }

    public JPanel getPanel() {
        return panelForRunButton;
    }

    public JButton getRunButton() {
        return runButton;
    }

    public JTextField getTextField() {
        return textField;
    }
}