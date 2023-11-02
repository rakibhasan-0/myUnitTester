import javax.swing.*;
import java.awt.*;

public class UnitTester {
    private final JFrame frame;
    private final ButtonPanel buttonPanel;
    private final TextVisualization textVisualization;
    private final ClearText clearText;

    private final TestExecutionPanel testExecutionPanel;

    public UnitTester() {
        frame = createMainFrame();
        buttonPanel = new ButtonPanel();
        textVisualization = new TextVisualization();

        frame.add(buttonPanel.getPanel(), BorderLayout.NORTH);
        frame.add(textVisualization.getScrollPane(), BorderLayout.CENTER);

        frame.setVisible(true);

        clearText = new ClearText(frame);

         testExecutionPanel= new TestExecutionPanel(
                 buttonPanel.getRunButton(),
                 textVisualization.getTextArea(),
                 clearText.getClearButton(),
                 buttonPanel.getTextField()
        );

    }

    private JFrame createMainFrame() {
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);
        return frame;
    }

}
