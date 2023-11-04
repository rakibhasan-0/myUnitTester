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
        scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(600, 600));

    }

    public JTextArea getTextArea() {
        return textArea;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }
}
