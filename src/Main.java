import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Main extends JFrame {
    private World world;
    private Human human;

    public Main() {
        setTitle("Virtual World Simulator - s198020");
        setSize(1200, 800);

        confWorld();

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton nextTurnButton = new JButton("Next turn");
        JButton saveButton = new JButton("Save");
        JButton loadButton = new JButton("Load");
        JButton resetButton = new JButton("Reset");
        JButton skillButton = new JButton("Use skill");
        buttonPanel.add(nextTurnButton);
        buttonPanel.add(resetButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);
        buttonPanel.add(skillButton);
        JTextArea textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);

        scrollPane.setPreferredSize(new Dimension(400, this.getHeight() - buttonPanel.getHeight() - skillButton.getHeight()));

        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        sidePanel.add(buttonPanel);
        sidePanel.add(scrollPane);
        add(sidePanel, BorderLayout.EAST);

        nextTurnButton.addActionListener(
                e -> {
                    world.makeTurn();
                    for (String message : world.messages) {
                        textArea.append(message + "\n");
                    }
                    world.messages = new ArrayList<>();
                }
        );

        resetButton.addActionListener(e -> {confWorld(); textArea.setText("");});
    }

    void confWorld() {
        if (world != null)
            remove(world);
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
        human = new Human(width / 2, height / 2, world);
        world.addOrganism(human, width / 2, height / 2);

        var factory = new OrganismFactory(world);
        factory.fillBoardWithOrganisms();

        add(world);
        revalidate();
        world.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main frame = new Main();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
