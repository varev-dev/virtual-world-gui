import java.awt.*;

public class Grass extends Plant {
    public Grass(int x, int y, World world) {
        super(x, y, 0, world, Color.GREEN);
    }

    @Override
    public String toString() {
        return "Grass";
    }
}
