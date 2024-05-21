import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Main extends JFrame {
    private World world;
    private Human human;
    private final JTextArea textArea;

    public Main() {
        setTitle("Virtual World Simulator - s198020");
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setFocusable(true);

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
        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);

        scrollPane.setPreferredSize(new Dimension(400, this.getHeight() - buttonPanel.getHeight() - skillButton.getHeight()));

        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        sidePanel.add(buttonPanel);
        sidePanel.add(scrollPane);
        add(sidePanel, BorderLayout.EAST);

        nextTurnButton.addActionListener(
                e -> {
                    makeTurn();
                    requestFocusInWindow();
                }
        );

        skillButton.addActionListener(
                e ->  {
                    human.useSkill();
                    textArea.append(world.messages.get(world.messages.size() - 1) + "\n");
                    requestFocusInWindow();
                }
        );

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (world == null || human == null)
                    return;

                if (!world.organisms.contains(human))
                    return;

                Direction direction = Direction.getByKey(e.getKeyChar());

                if (direction != null) {
                    human.setDirection(direction);
                    makeTurn();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                human.setDirection(null);
            }
        });

        addWindowFocusListener(new java.awt.event.WindowAdapter() {
            public void windowGainedFocus(java.awt.event.WindowEvent e) {
                requestFocusInWindow();
            }
        });

        resetButton.addActionListener(e -> {
            confWorld();
            textArea.setText("");
            requestFocusInWindow();
        });
    }

    void makeTurn() {
        if (world == null)
            return;
        world.makeTurn();
        repaint();
        for (String message : world.messages) {
            textArea.append(message + "\n");
        }
        world.messages = new ArrayList<>();
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
