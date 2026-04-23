import javax.swing.*;
import java.awt.*;

/**
 * Main class
 * Creates the SwingUtilities invoke later
 * Initializes the GamePanel object and adds sprites
 *
 * @author Purdue CS
 * @version April 10, 2026
 */

public class Main {

    // Main method for the program
    public static void main(String[] args) {
        // Run the GUI
        SwingUtilities.invokeLater(() -> createGUI());
    }

    /*
     * Declares the invokeLater()
     * Sets up the JFrame and GamePanel
     * Adds sprites to the GamePanel
     */
    private static void createGUI() {
        SwingUtilities.invokeLater(() -> {
            // Initialize JFrame
            JFrame frame = new JFrame("Sprite Example");

            // Initialize GamePanel to be a dark green
            GamePanel panel = new GamePanel(Color.decode("#2f7a30"));
            panel.setPreferredSize(new Dimension(800, 600));

            // Add 3 PixelSprites to the GamePanel
            // Courtesy of Veronica Choulga
            JButton startButton = new JButton("Start");
            JButton restartButton = new JButton("Restart");

            frame.add(startButton, BorderLayout.NORTH);
            frame.add(restartButton, BorderLayout.SOUTH);
            
            startButton.addActionListener(e -> {
                panel.startGame();
                startButton.setEnabled(false);
                panel.requestFocusInWindow();
            });

            restartButton.addActionListener(e -> {
                frame.dispose();
                createGUI();
            });

            // Set up the frame parameters and make visible
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(panel, BorderLayout.CENTER);
            frame.pack();
            frame.setResizable(false);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
