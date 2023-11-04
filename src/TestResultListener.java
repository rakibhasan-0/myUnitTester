public interface TestResultListener {
    void onTestComplete(String testResults);
    void onTestError(String errorMessage);
}
