import javax.swing.*;
import java.awt.*;

public class TextVisualization {
    private JTextArea textArea;
    private JScrollPane scrollPane;

    public TextVisualization() {
        textArea = new JTextArea();
        textArea.setFont(new Font("Arial", Font.BOLD, 14));
        textArea.setRows(10);
        textArea.setEditable(false);
        scrollPane = new JScrollPane(textArea); // This already adds textArea to scrollPane
        scrollPane.setPreferredSize(new Dimension(600, 600));
        //scrollPane.add(textArea);
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }
}
