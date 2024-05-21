public enum Direction {
    WEST('A'),
    NORTHWEST('Q'),
    NORTH('W'),
    NORTHEAST('E'),
    EAST('D'),
    SOUTHEAST('C'),
    SOUTH('X'),
    SOUTHWEST('Z');

    private final char key;

    Direction(char key) {
        this.key = key;
    }

    static Direction getByKey(char key) {
        for (int i = 0; i < Direction.values().length; i++) {
            if (Character.toUpperCase(key) == Direction.values()[i].key)
                return Direction.values()[i];
        }

        return null;
    }

}
