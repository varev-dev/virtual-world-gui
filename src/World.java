import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class World extends JPanel implements Serializable {
    static final int FIELD_SIZE = 20;
    int turn = 1;
    protected int width;
    protected int height;
    protected Organism[][] board;
    protected List<Organism> organisms;
    protected List<String> messages;

    public World(int width, int height) {
        this.width = width;
        this.height = height;
        this.board = new Organism[height][width];
        this.organisms = new ArrayList<>();
        this.messages = new ArrayList<>();
        setPreferredSize(new Dimension(width * FIELD_SIZE, height * FIELD_SIZE));

        World world = this;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX() / FIELD_SIZE;
                int y = e.getY() / FIELD_SIZE;

                if (board[y][x] != null)
                    return;

                String[] items = {"Wolf", "Sheep", "Antelope", "Turtle", "Fox"};

                String selectedItem = (String) JOptionPane.showInputDialog(
                        getParent(), "Select an animal:", "Selection",
                        JOptionPane.PLAIN_MESSAGE, null, items, items[0]
                );

                board[y][x] = new Wolf(x, y, world);
                organisms.add(board[y][x]);
                repaint();
            }
        });
    }

    public void addOrganism(Organism organism, int x, int y) {
        board[y][x] = organism;
        organisms.add(organism);
    }

    public void makeTurn() {
        messages.clear();
        messages.add("Current turn: " + this.turn);
        Collections.sort(organisms);
        List<Organism> copy = List.copyOf(organisms);
        for (int i = 0; i < copy.size();) {
            Organism organism = copy.get(i);

            if (!organisms.contains(organism)) {
                i++;
                continue;
            }

            if (organism.actionDelay > 0) {
                organism.actionDelay--;
                i++;
                continue;
            }
            organism.action();
            i++;
        }
        turn++;
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (board[y][x] == null) {
                    g.setColor((x+y) % 2 == 1 ? Color.LIGHT_GRAY : Color.GRAY);
                    g.fillRect(x * 20, y * 20, 20, 20);
                    continue;
                }
                board[y][x].paint(g);
            }
        }
    }
}
