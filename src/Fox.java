import java.awt.*;

public class Fox extends Animal {
    public Fox(int x, int y, World world) {
        super(x, y, 3, 7, world, Color.ORANGE);
    }

    @Override
    public void action() {
        checked = new boolean[Direction.values().length];

        try {
            Direction direction = getRandomPossibleDirection(true, false, Fox.class);
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
        if (organism instanceof Fox) {
            try {
                Direction direction = getRandomPossibleDirection(false, false, Fox.class);
                Position pos = Position.generatePosition(this, direction);

                if (position.compareTo(pos) == 0)
                    return;

                world.addOrganism(new Fox(pos.getX(), pos.getY(), world), pos.getX(), pos.getY());
                world.messages.add("Fox " + pos + " have just been born.");
            } catch (Exception e) {
                world.messages.add("Fox " + position + " cannot give birth.");
            }
        }
    }


}
