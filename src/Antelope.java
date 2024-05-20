import java.awt.*;
import java.util.Random;

public class Antelope extends Animal {
    public Antelope(int x, int y, World world) {
        super(x, y, 4, 4, world, new Color(150, 75, 0));
        super.moveSize = 2;
    }

    @Override
    public void collision(Organism organism) {
        if (organism instanceof Antelope) {
            try {
                this.moveSize = 1;
                Direction direction = getRandomPossibleDirection(false, false);
                Position pos = Position.generatePosition(this, direction);
                this.moveSize = 2;
                if (position.compareTo(pos) == 0)
                    return;

                world.addOrganism(new Antelope(pos.getX(), pos.getY(), world), pos.getX(), pos.getY());
                world.messages.add(pos + " " + this + " have just been born.");
            } catch (Exception e) {
                world.messages.add(position + " " + this + " cannot give birth.");
            }
            return;
        }

        var random = new Random();

        if (random.nextInt(2) == 0) {
            super.moveSize = 1;
            action();
            super.moveSize = 2;
            return;
        }
        super.collision(organism);
    }

    @Override
    public String toString() {
        return "Antelope";
    }
}
