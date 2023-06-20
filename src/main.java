public class main {
    public static void main(String[] args){
        ButtonPanel buttonPanel = new ButtonPanel();
        TextVisualization text = new TextVisualization(buttonPanel.getFrame(), buttonPanel.getButton());
        ClearText clearText = new ClearText(text.textArea, buttonPanel.getFrame());

        RunButtonAction run = new RunButtonAction(buttonPanel.getTextField(),buttonPanel.getButton());
    }
}
