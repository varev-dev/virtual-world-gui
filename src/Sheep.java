import java.awt.*;

public class Sheep extends Animal {
    public Sheep(int x, int y, World world) {
        super(x, y, 4, 4, world, Color.PINK);
    }

    @Override
    public void collision(Organism organism) {
        if (organism instanceof Sheep) {
            try {
                Direction direction = getRandomPossibleDirection(false, false, Sheep.class);
                Position pos = Position.generatePosition(this, direction);

                if (position.compareTo(pos) == 0)
                    return;

                world.addOrganism(new Sheep(pos.getX(), pos.getY(), world), pos.getX(), pos.getY());
                world.messages.add("Sheep " + pos + " have just been born.");
            } catch (Exception e) {
                world.messages.add("Sheep " + position + " cannot give birth.");
            }
        }
    }
}
