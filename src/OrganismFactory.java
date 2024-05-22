import java.util.Random;

public class OrganismFactory {
    private final double ANIMALS_PERCENT = 0.2;
    private final double PLANTS_PERCENT = 0.3;
    private World world;

    public OrganismFactory(World world) {
        this.world = world;
    }

    public void fillBoardWithOrganisms() {
        var animalsToBeCreated = (int) (world.height * world.width * ANIMALS_PERCENT);
        Position position;
        Organism organism;
        var generator = new Random();

        while (animalsToBeCreated > 0) {
            position = Position.generateRandomPosition(world);
            int animalId = generator.nextInt(5);

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

        var plantsToBeCreated = (int) (world.height * world.width * PLANTS_PERCENT);

        while (plantsToBeCreated > 0) {
            position = Position.generateRandomPosition(world);
            int plantId = generator.nextInt(5);

            if (world.board[position.getY()][position.getX()] != null)
                continue;

            switch (plantId) {
                case 1 -> organism = new Grass(position.getX(), position.getY(), world);
                case 2 -> organism = new Sonchus(position.getX(), position.getY(), world);
                case 3 -> organism = new Hogweed(position.getX(), position.getY(), world);
                case 4 -> organism = new Belladonna(position.getX(), position.getY(), world);
                default -> organism = new Guarana(position.getX(), position.getY(), world);
            }

            world.addOrganism(organism, position.getX(), position.getY());
            plantsToBeCreated--;
        }
    }

    public Plant createNewPlantWithType(Plant plant, Position position) throws Exception {
        if (plant instanceof Guarana) {
            return new Guarana(position.getX(), position.getY(), world);
        } else if (plant instanceof Grass) {
            return new Grass(position.getX(), position.getY(), world);
        } else if (plant instanceof Sonchus) {
            return new Sonchus(position.getX(), position.getY(), world);
        } else if (plant instanceof Belladonna) {
            return new Belladonna(position.getX(), position.getY(), world);
        }

        throw new Exception("");
    }
}
