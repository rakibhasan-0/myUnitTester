import javax.swing.*;
import java.util.concurrent.ExecutionException;

public class TestRunnerWorker extends SwingWorker<String, Void> {
    private final String className;
    private JTextArea textArea;
    private final TestRunner testRunner;

    private String testResults;

    public TestRunnerWorker(String className, JTextArea textArea) {
        this.className = className;
        this.textArea = textArea;
        this.testRunner = new TestRunner(); // Assuming TestRunner has a no-arg constructor
    }

    @Override
    protected String doInBackground() throws Exception {
        // This code is executed in a background thread.
        return testRunner.runTests(className);
    }

    @Override
    protected void done() {
        // This method is executed in the Swing event dispatch thread
        // after the background work has been completed.
        try {
            testResults = get(); // Get the result from doInBackground()
            textArea.setText(testResults); // Ensure this textArea is the same that's added to your frame/panel.
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            showErrorMessage("Test execution was interrupted: " + e.getMessage());
            e.printStackTrace();
        } catch (ExecutionException e) {
            showErrorMessage("An error occurred during test execution: " + e.getCause().getMessage());
            e.getCause().printStackTrace();
        }
    }

    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

}

