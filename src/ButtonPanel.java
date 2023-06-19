import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import static java.awt.Font.PLAIN;

public class ButtonPanel {
    JFrame frame;
    JButton runButton;
    JTextField textField;

    public ButtonPanel() {
        runButton = new JButton("RUN");
        textField = new JTextField();
        frame = new JFrame();

        frame.setLayout(new BorderLayout());
        JPanel panelForRunButton = new JPanel();
        panelForRunButton.setLayout(new BorderLayout());

        textField.setSize(100, 330);
        textField.setFont(new Font("Roboto", Font.PLAIN, 12));
        textField.setBorder(new LineBorder(Color.BLACK));

        panelForRunButton.add(textField, BorderLayout.CENTER);
        panelForRunButton.add(runButton, BorderLayout.EAST);
        frame.add(panelForRunButton, BorderLayout.NORTH);
        frame.setVisible(true);
        frame.setSize(700, 700);
    }
}
