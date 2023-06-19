public class Test {
    public static void main(String[] args){
        ButtonPanel buttonPanel = new ButtonPanel();
        TextVisualization text = new TextVisualization(buttonPanel.getFrame(),buttonPanel.getButton());
        new ClearText(text.textArea,buttonPanel.getFrame());

    }
}
