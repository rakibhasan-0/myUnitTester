import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ClearText {
    private final JButton clearButton;

    public ClearText(JFrame frame) {
        JPanel clearPanel = new JPanel();
        clearButton = new JButton("CLEAR");
        clearPanel.setLayout(new FlowLayout(FlowLayout.TRAILING));
        clearPanel.add(clearButton);
        clearPanel.setBorder(new EmptyBorder(12, 0, 0, 0)); // Add an empty border at the bottom
        frame.add(clearPanel, BorderLayout.SOUTH);
    }


    public JButton getClearButton() {
        return clearButton;
    }


}
