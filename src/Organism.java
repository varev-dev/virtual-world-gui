import java.awt.*;

public abstract class Organism implements Comparable<Organism> {
    static final int DELAY = 5;
    protected int actionDelay = 0;
    protected Position position;
    protected int strength;
    protected int initiative;
    protected int age;
    protected World world;
    protected Color color;

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
}
