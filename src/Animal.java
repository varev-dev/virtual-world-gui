import java.awt.*;
import java.util.Random;

public abstract class Animal extends Organism {

    public Animal(int x, int y, int strength, int initiative, World world, Color color) {
        super(x, y, strength, initiative, world, color);
        checked = new boolean[Direction.values().length];
    }

    @Override
    public void action() {
        checked = new boolean[Direction.values().length];
        try {
            Direction direction = getRandomPossibleDirection(true, true);
            Position newPosition = Position.generatePosition(this, direction);
            Organism organism = world.board[newPosition.getY()][newPosition.getX()];

            if (organism == null) {
                world.board[position.getY()][position.getX()] = null;
                world.board[newPosition.getY()][newPosition.getX()] = this;
                this.position = newPosition;
                return;
            }

            organism.collision(this);
        } catch (NoDirectionException | PositionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void collision(Organism organism) {
        String message = this.position.toString() + " ";
        if (this.strength > organism.strength) {
            message += this + " ate " + organism;
            world.board[organism.position.getY()][organism.position.getX()] = null;
            world.organisms.remove(organism);
        } else {
            message += organism + " ate " + this;
            world.board[position.getY()][position.getX()] = null;
            world.organisms.remove(this);
        }

        world.messages.add(message);
    }
}
