import javax.swing.*;
import java.awt.*;

public class TabbedPaneSimpleExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Tabbed Pane Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        // Create JTabbedPane
        JTabbedPane tabbedPane = new JTabbedPane();

        // Tab 1: FlowLayout (side-by-side)
        JPanel tab1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        tab1.add(new JButton("Button"));
        tab1.add(new JLabel("Beside Button"));
        tabbedPane.addTab("FlowLayout Tab", tab1);

        // Tab 2: BoxLayout (stacked vertically)
        JPanel tab2 = new JPanel();
        tab2.setLayout(new BoxLayout(tab2, BoxLayout.Y_AXIS));
        JButton button = new JButton("Button");
        JLabel label = new JLabel("Below Button");
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        tab2.add(button);
        tab2.add(label);
        tabbedPane.addTab("BoxLayout Tab", tab2);

        // Add tabbed pane to frame
        frame.add(tabbedPane);
        frame.setVisible(true);
    }
}