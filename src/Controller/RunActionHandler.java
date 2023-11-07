package Controller;

import Model.TestResultListener;
import Model.TestRunnerWorker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RunActionHandler implements ActionListener {
    private final JTextField textField;
    private final TestResultListener resultListener;

    public RunActionHandler(JTextField textField, TestResultListener resultListener) {
        this.textField = textField;
        this.resultListener = resultListener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String className = textField.getText().trim();
        TestRunnerWorker worker = new TestRunnerWorker(className, resultListener);
        worker.execute();
    }
}
