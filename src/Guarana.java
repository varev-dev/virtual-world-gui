import java.awt.*;

public class Guarana extends Plant {
    private final int value = 3;

    public Guarana(int x, int y, World world) {
        super(x, y, 0, world, new Color(187, 19, 98));
    }

    @Override
    public void collision(Organism organism) {
        organism.strength += value;
        super.collision(organism);
    }

}
