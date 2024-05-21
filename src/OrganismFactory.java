import java.util.Random;

public class OrganismFactory {
    private static final double ANIMALS_PERCENT = 0.35;
    private World world;

    public OrganismFactory(World world) {
        this.world = world;
    }

    public void fillBoardWithOrganisms() {
        var animalsToBeCreated = (int) (world.height * world.width * ANIMALS_PERCENT);
        Position position;
        Organism organism;
        var generator = new Random();
        int animalId = 0;
        while (animalsToBeCreated > 0) {
            position = Position.generateRandomPosition(world);
            animalId = generator.nextInt(5);

            if (world.board[position.getY()][position.getX()] != null)
                continue;

            switch (animalId) {
                case 1 -> organism = new Sheep(position.getX(), position.getY(), world);
                case 2 -> organism = new Fox(position.getX(), position.getY(), world);
                case 3 -> organism = new Turtle(position.getX(), position.getY(), world);
                case 4 -> organism = new Antelope(position.getX(), position.getY(), world);
                default -> organism = new Wolf(position.getX(), position.getY(), world);
            }

            world.addOrganism(organism, position.getX(), position.getY());
            animalsToBeCreated--;
        }
    }

    public Plant createNewPlantWithType(Plant plant, Position position) throws Exception {
        if (plant instanceof Guarana) {
            return new Guarana(position.getX(), position.getY(), plant.world);
        }

        throw new Exception("");
    }
}
