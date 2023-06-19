public class Test {
    public static void main(String[] args){
        ButtonPanel buttonPanel = new ButtonPanel();
        TextVisualization text = new TextVisualization(buttonPanel.getFrame(),buttonPanel.runButton);
        new ClearText(text.textArea,buttonPanel.getFrame());

    }
}
