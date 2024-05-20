import java.awt.*;

public class Wolf extends Animal {
    public Wolf(int x, int y, World world) {
        super(x, y, 9, 5, world, Color.BLACK);
    }

    @Override
    public void collision(Organism organism) {
        if (organism instanceof Wolf) {
            try {
                Direction direction = getRandomPossibleDirection(false, false, Wolf.class);
                Position pos = Position.generatePosition(this, direction);

                if (position.compareTo(pos) == 0)
                    return;

                world.addOrganism(new Wolf(pos.getX(), pos.getY(), world), pos.getX(), pos.getY());
                world.messages.add("Wolf " + pos + " have just been born.");
            } catch (Exception e) {
                world.messages.add("Wolf " + position + " cannot give birth.");
            }
        }
    }
}
