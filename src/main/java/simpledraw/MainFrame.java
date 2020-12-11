package simpledraw;

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class MainFrame extends JFrame {
    JToggleButton mySelectButton = new JToggleButton("Selection");
    JButton myClearButton = new JButton("Effacer");
    JToggleButton myLineButton = new JToggleButton("Ligne");
    JToggleButton myCircleButton = new JToggleButton("Cercle");

    Drawing myDrawing = new Drawing();

    DrawingController myDrawingPanel = new DrawingController(myDrawing);
    DrawingConsoleView myDrawingConsoleView = new DrawingConsoleView(myDrawing);

    /**
     * Construct the frame
     */
    public MainFrame() {
        enableEvents(AWTEvent.WINDOW_EVENT_MASK);
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Component initialization
     */
    private void jbInit() throws Exception {
        getContentPane().setLayout(new BorderLayout());
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        mySelectButton.setSelected(true);
        mySelectButton.setToolTipText("Selectionner la forme. CTRL+Selection pour grouper.");
        myCircleButton.setToolTipText("Faire un cercle.");
        myLineButton.setToolTipText("Faire une ligne.");
        myClearButton.setToolTipText("Effacer toutes les formes.");

        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.add(mySelectButton, null);
        buttonPanel.add(myClearButton, null);
        buttonPanel.add(myLineButton, null);
        buttonPanel.add(myCircleButton, null);
        getContentPane().add(myDrawingPanel, BorderLayout.CENTER);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(mySelectButton);
        buttonGroup.add(myLineButton);
        buttonGroup.add(myCircleButton);

        setSize(new Dimension(400, 300));
        setTitle("SimpleDraw");

        mySelectButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        myDrawingPanel.activateSelectionTool();
                    }
                }
        );

        myLineButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        myDrawingPanel.activateLineTool();
                    }
                }
        );

        myCircleButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        myDrawingPanel.activateCircleTool();
                    }
                }
        );

        myClearButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        myDrawing.deleteAllShapes();
                    }
                }
        );
    }

    /**
     * Overridden so we can exit when window is closed
     */
    @Override
    protected void processWindowEvent(WindowEvent e) {
        super.processWindowEvent(e);
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            System.exit(0);
        }
    }
}
