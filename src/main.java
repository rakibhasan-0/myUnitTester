public class main {
    public static void main(String[] args){

        ButtonPanel buttonPanel = new ButtonPanel();
        TextVisualization text = new TextVisualization(buttonPanel.getFrame(), buttonPanel.getButton());
        ClearText clearText = new ClearText(buttonPanel.getFrame());

        RunButtonAction run = new RunButtonAction(buttonPanel.getTextField(),buttonPanel.getButton(),
                text.getTextArea());
        ClearText cText = new ClearText(buttonPanel.getFrame());
        ClearButtonAction cAction = new ClearButtonAction(cText.getClearButton(),run.getTextArea(),
                run.getResultMessages());

    }
}
