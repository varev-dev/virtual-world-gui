import java.awt.*;

public class Plant extends Organism {
    public Plant(int x, int y, int strength, int initiative, World world, Color color) {
        super(x, y, strength, initiative, world, color);
    }

    @Override
    public void action() {

    }

    @Override
    public void collision(Organism organism) {
        world.board[position.getY()][position.getX()] = null;
        world.organisms.remove(this);
    }

}
