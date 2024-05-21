import java.awt.*;

public class Sonchus extends Plant {
    final int actions = 3;

    public Sonchus(int x, int y, World world) {
        super(x, y, 0, world, new Color(150, 150, 0));
    }

    @Override
    public void action() {
        for (int i = 0; i < actions; i++) {
            super.action();
        }
    }

    @Override
    public String toString() {
        return "Sonchus";
    }
}
