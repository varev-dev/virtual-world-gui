import java.awt.*;
import java.util.Random;

public abstract class Organism implements Comparable<Organism> {
    static final int DELAY = 5;
    protected int actionDelay = 0;
    protected Position position;
    protected int strength;
    protected int initiative;
    protected int age;
    protected World world;
    protected Color color;
    protected boolean[] checked;
    protected int moveSize=1;

    public Organism(int x, int y, int strength, int initiative, World world, Color color) {
        position = new Position(x, y);
        this.strength = strength;
        this.initiative = initiative;
        this.world = world;
        this.color = color;
        this.age = 0;
        if (world.turn != 0)
            actionDelay = 1;
    }

    public abstract void action();
    public abstract void collision(Organism organism);

    public void paint(Graphics graphics) {
        graphics.setColor(color);
        graphics.fillRect(position.getX() * 20, position.getY() * 20, 20, 20);
    }

    @Override
    public int compareTo(Organism organism) {
        if (this.initiative != organism.initiative)
            return organism.initiative - this.initiative;

        return organism.age - this.age;
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
