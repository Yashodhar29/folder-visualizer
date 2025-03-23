import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class Main {

    static DefaultMutableTreeNode root;
    static JTree tree;
    static JFrame frame; // Made static for access in ActionListener

    public static void main(String[] args) {
        frame = new JFrame("Current Directory");
        frame.setSize(new Dimension(400, 500));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        // Initialize the tree with a placeholder root
        root = new DefaultMutableTreeNode("No directory selected");
        tree = new JTree(root);
        JScrollPane treeScrollPane = new JScrollPane(tree); // Wrap tree in scroll pane
        treeScrollPane.setPreferredSize(new Dimension(350, 400)); // Set size for visibility
        frame.add(treeScrollPane);

        JButton choose = new JButton("Choose Directory");
        choose.setPreferredSize(new Dimension(200, 30));
        choose.setBackground(Color.BLACK);
        choose.setForeground(Color.WHITE);

        choose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser("Select directory");
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                fileChooser.setAcceptAllFileFilterUsed(false);

                int result = fileChooser.showOpenDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    String folder = file.getAbsolutePath();

                    // Update the tree with the selected folder's hierarchy
                    root = new DefaultMutableTreeNode(file.getName());
                    addFolderContents(root, file); // Recursively build hierarchy
                    tree.setModel(new DefaultTreeModel(root)); // Update tree model

                    // Refresh the UI
                    frame.revalidate();
                    frame.repaint();
                }
            }
        });

        frame.add(choose);
        frame.setVisible(true);
    }

    // Recursively add folder contents to the tree
    private static void addFolderContents(DefaultMutableTreeNode node, File folder) {
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(file.getName());
                node.add(childNode);
                if (file.isDirectory()) {
                    addFolderContents(childNode, file); // Recurse into subdirectories
                }
            }
        }
    }
}