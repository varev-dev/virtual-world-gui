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
    public static Position generatePosition(Animal animal, Direction direction) throws PositionException {
        Position position = new Position(animal.position.x, animal.position.y);

        if (direction.name().contains(Direction.WEST.name())) {
            if (position.x - animal.moveSize >= 0) position.x -= animal.moveSize;
        } else if (direction.name().contains(Direction.EAST.name())) {
            if (position.x + animal.moveSize < animal.world.width) position.x += animal.moveSize;
        }

        if (direction.name().contains(Direction.NORTH.name())) {
            if (position.y - animal.moveSize >= 0) position.y -= animal.moveSize;
        } else if (direction.name().contains(Direction.SOUTH.name())) {
            if (position.y + animal.moveSize < animal.world.height) position.y += animal.moveSize;
        }

        if (position.x >= animal.world.width || position.x < 0 || position.y >= animal.world.height || position.y < 0)
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
