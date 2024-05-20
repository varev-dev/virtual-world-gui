import java.awt.*;
import java.util.Random;

public class Turtle extends Animal {
    public Turtle(int x, int y, World world) {
        super(x, y, 2, 1, world, new Color(138, 154, 91));
    }

    @Override
    public void action() {
        var random = new Random();

        if (random.nextInt(4) == 0)
            super.action();
    }

    @Override
    public void collision(Organism organism) {
        if (organism instanceof Turtle) {
            try {
                Direction direction = getRandomPossibleDirection(false, false);
                Position pos = Position.generatePosition(this, direction);

                if (position.compareTo(pos) == 0)
                    return;

                world.addOrganism(new Turtle(pos.getX(), pos.getY(), world), pos.getX(), pos.getY());
                world.messages.add(pos + " " + this + " have just been born.");
            } catch (Exception e) {
                world.messages.add(position + " " + this + " cannot give birth.");
            }
            return;
        }

        if (organism.strength < 5)
            return;

        super.collision(organism);
    }

    @Override
    public String toString() {
        return "Turtle";
    }
}
