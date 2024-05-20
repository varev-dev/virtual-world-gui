import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class World extends JPanel {
    int turn = 0;
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
        setPreferredSize(new Dimension(width * 20, height * 20));
    }

    public void addOrganism(Organism organism, int x, int y) {
        board[y][x] = organism;
        organisms.add(organism);
    }

    public void makeTurn() {
        messages.clear();
        messages.add("Current turn: " + this.turn);
        Collections.sort(organisms);
        int lastTurnSize = organisms.size();
        for (int i = 0; i < organisms.size();) {
            Organism organism = organisms.get(i);
            if (organism.actionDelay > 0)
                organism.actionDelay--;
            if (organism.actionDelay == 0)
                organism.action();
            if (lastTurnSize > organisms.size()) {
                if (organism == organisms.get(i))
                    i++;
            }
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
