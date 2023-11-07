package Model;

import Model.TestRunner;

import javax.swing.*;
import java.util.concurrent.ExecutionException;

public class TestRunnerWorker extends SwingWorker<String, Void> {
    private final String className;
    private final TestRunner testRunner;
    private TestResultListener listener;
    private String testResults;

    public TestRunnerWorker(String className, TestResultListener listener) {
        this.className = className;
        this.listener = listener;
        this.testRunner = new TestRunner();
    }

    @Override
    protected String doInBackground() throws Exception {
        return testRunner.runTests(className);
    }

    @Override
    protected void done() {
        try {
            testResults = get();
            listener.onTestComplete(testResults);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            listener.onTestError("Test execution was interrupted: " + e.getMessage());

        } catch (ExecutionException e) {
            listener.onTestError("An error occurred during test execution: " +
                    e.getCause().getMessage());
        }
    }

}

