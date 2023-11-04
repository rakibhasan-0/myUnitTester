import javax.swing.*;
import java.awt.*;



public class UnitTestGUI {
    private final JFrame frame;
    private final RunButtonPanel runButtonPanel;
    private final TextVisualization textVisualization;

    public UnitTestGUI() {
        frame = createMainFrame();
        runButtonPanel = new RunButtonPanel();
        ClearButtonPanel clearButtonPanel = new ClearButtonPanel(frame);
        textVisualization = new TextVisualization();

        // Set up action handlers
        TextUIHandler textHandler = new TextUIHandler(textVisualization.getTextArea(), clearButtonPanel.getClearButton());
        RunActionHandler runHandler = new RunActionHandler(runButtonPanel.getTextField(), textHandler);

        // Add action listeners to buttons
        runButtonPanel.getRunButton().addActionListener(runHandler);
        clearButtonPanel.getClearButton().addActionListener(textHandler);

        // Set up the UI layout
        frame.add(runButtonPanel.getPanel(), BorderLayout.NORTH);
        frame.add(textVisualization.getScrollPane(), BorderLayout.CENTER);

        // Display the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);
        frame.setVisible(true);
    }

    private JFrame createMainFrame() {
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);
        return frame;
    }

}
