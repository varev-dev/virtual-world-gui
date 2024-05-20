import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    private World world;

    public Main() {
        setTitle("Virtual World Simulator - s198020");
        setSize(1200, 800);

        int width = Integer.parseInt(
                JOptionPane.showInputDialog(
                        this,
                        "Enter board width",
                        "Board setup",
                        JOptionPane.QUESTION_MESSAGE));
        int height = Integer.parseInt(
                JOptionPane.showInputDialog(
                        this,
                        "Enter board height",
                        "Board setup",
                        JOptionPane.QUESTION_MESSAGE));

        world = new World(width, height);
        var factory = new AnimalFactory(world);
        factory.fillBoardWithOrganisms();
        add(world);
        JButton nextTurnButton = new JButton("Next turn");
        nextTurnButton.addActionListener(e -> world.makeTurn());
        add(nextTurnButton, BorderLayout.SOUTH);
        JTextArea textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, this.getHeight() - nextTurnButton.getHeight())); // Ustawiamy preferowaną szerokość na 200 pikseli
        this.add(scrollPane, BorderLayout.EAST);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main frame = new Main();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
