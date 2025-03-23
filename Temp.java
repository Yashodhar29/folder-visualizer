import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Temp {
    public static void main(String[] args) {
        // Create a JFrame
        JFrame frame = new JFrame("HTML from Website");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        // Create a JEditorPane
        JEditorPane editorPane = new JEditorPane();
        editorPane.setEditable(false);

        try {
            // Load HTML from a URL
            URL url = new URL("https://google.com");
            editorPane.setPage(url);
        } catch (Exception e) {
            editorPane.setText("<html><p>Error loading page: " + e.getMessage() + "</p></html>");
        }

        // Add to a JScrollPane
        JScrollPane scrollPane = new JScrollPane(editorPane);

        // Add to the frame
        frame.add(scrollPane, BorderLayout.CENTER);

        // Display the frame
        frame.setVisible(true);
    }
}