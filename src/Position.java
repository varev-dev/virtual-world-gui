import java.util.Random;

public class Position implements Comparable<Position> {
    private int x, y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(x: " + x + "; y: " + y + ")";
    }
    public static Position generatePosition(Organism organism, Direction direction) throws PositionException {
        Position position = new Position(organism.position.x, organism.position.y);

        if (direction.name().contains(Direction.WEST.name())) {
            if (position.x - organism.moveSize >= 0) position.x -= organism.moveSize;
        } else if (direction.name().contains(Direction.EAST.name())) {
            if (position.x + organism.moveSize < organism.world.width) position.x += organism.moveSize;
        }

        if (direction.name().contains(Direction.NORTH.name())) {
            if (position.y - organism.moveSize >= 0) position.y -= organism.moveSize;
        } else if (direction.name().contains(Direction.SOUTH.name())) {
            if (position.y + organism.moveSize < organism.world.height) position.y += organism.moveSize;
        }

        if (position.x >= organism.world.width || position.x < 0 || position.y >= organism.world.height || position.y < 0)
            throw new PositionException("Unable to move further." + direction.name());

        return position;
    }

    public static Position generateRandomPosition(World world) {
        var generator = new Random();
        return new Position(generator.nextInt(world.width), generator.nextInt(world.height));
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public int compareTo(Position o) {
        return (this.x == o.x && this.y == o.y) ? 0 : 1;
    }
}
