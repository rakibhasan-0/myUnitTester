package Controller;

import Model.TestResultListener;

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


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == clearButton) {
            textArea.setText("");
        }
    }


    @Override
    public void onTestComplete(String testResults) {
        textArea.setText(testResults);
    }


    @Override
    public void onTestError(String errorMessage) {
        JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
