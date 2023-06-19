import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;

public class TextVisualization implements ActionListener {
    JTextArea textArea;
    JFrame frame;
    JButton runButton;

    public TextVisualization(JFrame frame, JButton button) {

        this.frame = frame;
        runButton = button;

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setFont(new Font("Arial", Font.BOLD, 12));
        textArea.setRows(10); // Setting the number of visible rows
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        // Set the preferred size of the JScrollPane
        scrollPane.setPreferredSize(new Dimension(20, 50));
        panel.add(scrollPane); // Add the scrollPane to the panel

        // Create a rounded yellow border for the pane
        panel.setBorder(new EmptyBorder(0, 0, 80, 0));

        frame.add(panel, BorderLayout.CENTER);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    JTextArea getTextArea() {
        return textArea;
    }

}
