import java.awt.*;
import java.util.Random;

public class Plant extends Organism {
    public Plant(int x, int y, int strength, World world, Color color) {
        super(x, y, strength, 0, world, color);
    }

    @Override
    public void action() {
        var random = new Random();

        if (random.nextInt(3) != 0)
            return;

        try {
            Direction direction = super.getRandomPossibleDirection(false, false);
            Position pos = Position.generatePosition(this, direction);

            OrganismFactory factory = new OrganismFactory(world);
            world.addOrganism(factory.createNewPlantWithType(this, pos), pos.getX(), pos.getY());
        } catch (Exception ignored) {}
    }

    @Override
    public void collision(Organism organism) {
        world.messages.add(organism + " ate " + this);

        world.board[organism.position.getY()][organism.position.getX()] = null;
        organism.position = position;
        world.board[position.getY()][position.getX()] = organism;

        world.organisms.remove(this);
    }

}
