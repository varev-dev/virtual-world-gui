import java.awt.*;

public class Belladonna extends Plant {
    public Belladonna(int x, int y, World world) {
        super(x, y, 99, world, new Color(120, 30, 160));
    }

    @Override
    public void collision(Organism organism) {
        if (!(organism instanceof Animal))
            return;

        world.messages.add(organism + " ate " + this + " and died.");
        world.board[organism.position.getY()][organism.position.getX()] = null;
        world.board[position.getY()][position.getX()] = null;
        world.organisms.remove(organism);
        world.organisms.remove(this);
    }

    @Override
    public String toString() {
        return "Belladonna";
    }
}
