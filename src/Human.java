import java.awt.*;

public class Human extends Animal {
    private Direction direction;

    public Human(int x, int y, World world) {
        super(x, y, 5, 4, world, new Color(155, 30, 30));
    }

    public void useSkill() {
        if (actionDelay == 0) {
            actionDelay = DELAY;
            world.messages.add(this + " is now Immortal");
        }
        else world.messages.add(this + " have to wait " + actionDelay + " more turns to use skill");
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public void action() {
        try {
            if (direction == null)
                return;

            Position newPos = Position.generatePosition(this, direction);

            if (newPos.compareTo(position) == 0)
                return;

            Organism organism = world.board[newPos.getY()][newPos.getX()];

            if (organism == null) {
                world.board[position.getY()][position.getX()] = null;
                world.board[newPos.getY()][newPos.getX()] = this;
                this.position = newPos;
                return;
            }

            organism.collision(this);
        } catch (PositionException e) {
            world.messages.add(this + " tried to move out of the board.");
        }
    }

    @Override
    public void collision(Organism organism) {
        if (actionDelay == 0) {super.collision(organism);}
        else world.messages.add(this + " survive " + organism + " attack because of special ability");
    }

    @Override
    public String toString() {
        return "Human";
    }

}
