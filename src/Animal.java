import java.awt.*;
import java.util.Random;

public abstract class Animal extends Organism {
    protected boolean[] checked;
    public int moveSize = 1;

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

    protected Direction getRandomPossibleDirection(boolean canBeOccupied, boolean canBeStronger) throws NoDirectionException, PositionException {
        var generator = new Random();

        while (!isEveryDirectionChecked(checked)) {
            int random = generator.nextInt(8);

            if (checked[random]) continue;
            checked[random] = true;

            Position newPos;
            try {
                newPos = Position.generatePosition(this, Direction.values()[random]);
            } catch (PositionException e) {
                continue;
            }

            if (position.compareTo(newPos) == 0)
                continue;

            if (world.board[newPos.getY()][newPos.getX()] != null) {
                if (!canBeOccupied)
                    continue;

                if (!canBeStronger && world.board[newPos.getY()][newPos.getX()].strength > this.strength)
                    continue;
            }

            return Direction.values()[random];
        }

        throw new NoDirectionException("No possible moves");
    }

    private boolean isEveryDirectionChecked(boolean[] checked) {
        for (boolean b : checked)
            if (!b) return false;
        return true;
    }
}
