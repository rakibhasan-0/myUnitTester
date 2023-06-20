import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class ButtonPanel {
    private JFrame frame;
    private JButton runButton;
    private JButton resetButton; // Added reset button
    private JTextField textField;

    public ButtonPanel() {
        frame = new JFrame();
        runButton = new JButton("RUN");
        textField = new JTextField();

        setupFrame();
        setupButtonPanel();

        frame.setVisible(true);
        frame.setSize(500, 600);
    }

    private void setupFrame() {
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void setupButtonPanel() {
        JPanel panelForRunButton = new JPanel(new BorderLayout());

        textField.setPreferredSize(new Dimension(100, 30));
        textField.setFont(new Font("Montserrat", Font.PLAIN, 12));
        textField.setBorder(new LineBorder(Color.BLACK));

        panelForRunButton.add(textField, BorderLayout.CENTER);
        panelForRunButton.add(runButton, BorderLayout.EAST);

        frame.add(panelForRunButton, BorderLayout.NORTH);
    }

    public JFrame getFrame() {
        return frame;
    }

    public JButton getButton() {
        return runButton;
    }

    public JTextField getTextField() {
        return textField;
    }
}
