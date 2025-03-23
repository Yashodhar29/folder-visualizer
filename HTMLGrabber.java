import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class HTMLGrabber {
    public static void main(String[] args) {
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();

        // Create JFrame
        JFrame frame = new JFrame("Grab Any HTML");
        frame.setSize(new Dimension((int) size.getWidth() - 40, (int) size.getHeight() - 40));
        frame.setLayout(new BorderLayout()); // Use BorderLayout for better control
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create input panel for URL and button
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JTextField url = new JTextField();
        url.setPreferredSize(new Dimension(frame.getWidth() - 200, 40));
        JButton grab = new JButton("Grab HTML");
        grab.setPreferredSize(new Dimension(100, 30));
        grab.setForeground(Color.WHITE);
        grab.setBackground(Color.BLACK);
        
        inputPanel.add(url);
        inputPanel.add(grab);

        // Create JEditorPane for HTML
        JEditorPane groundForHtml = new JEditorPane();
        groundForHtml.setContentType("text/html"); // Set to render HTML
        groundForHtml.setEditable(false); // Make it read-only

        // Create JScrollPane with JEditorPane
        JScrollPane scrollable = new JScrollPane(groundForHtml);
        scrollable.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight() - 100));

        // Add components to frame
        frame.add(inputPanel, BorderLayout.NORTH); // Input at top
        frame.add(scrollable, BorderLayout.CENTER); // Scrollable HTML in center

        // Action listener for grabbing HTML
        grab.addActionListener(l -> {
            try {
                String websiteUrl = url.getText();
                URL web = new URL(websiteUrl);

                HttpURLConnection connection = (HttpURLConnection) web.openConnection();
                connection.setRequestMethod("GET");

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder htmlContent = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    htmlContent.append(line).append("\n");
                }

                reader.close();
                connection.disconnect();

                // Set HTML content
                groundForHtml.setText(htmlContent.toString());
                System.out.println(htmlContent.toString());
                scrollable.add(groundForHtml);

            } catch (Exception a) {
                a.printStackTrace(); // Log errors for debugging
                groundForHtml.setText("<html><body><h1>Error: " + a.getMessage() + "</h1></body></html>");
            }
        });

        // Make frame visible
        frame.setVisible(true);
    }
}