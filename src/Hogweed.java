import java.awt.*;

public class Hogweed extends Plant {
    public Hogweed(int x, int y, World world) {
        super(x, y, 10, world, new Color(160, 199, 85));
    }

    @Override
    public void action() {
        for (int i = 0; i < Direction.values().length; i++) {
            try {
                Position position = Position.generatePosition(this, Direction.values()[i]);
                Organism organism = world.board[position.getY()][position.getX()];

                if (organism == null)
                    continue;

                if (!(organism instanceof Animal))
                    return;

                world.board[position.getY()][position.getX()] = null;
                world.organisms.remove(organism);
            } catch (Exception ignore) {}
        }
    }

    @Override
    public String toString() {
        return "Hogweed";
    }
}
