import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

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

        JButton nextTurnButton = new JButton("Next turn");
        JTextArea textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);

        factory.fillBoardWithOrganisms();
        add(world);

        scrollPane.setPreferredSize(new Dimension(400, this.getHeight() - nextTurnButton.getHeight()));
        add(scrollPane, BorderLayout.EAST);

        nextTurnButton.addActionListener(
                e -> {
                    world.makeTurn();
                    for (String message : world.messages) {
                        textArea.append(message + "\n");
                    }
                    world.messages = new ArrayList<>();
                }
        );
        add(nextTurnButton, BorderLayout.SOUTH);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main frame = new Main();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
