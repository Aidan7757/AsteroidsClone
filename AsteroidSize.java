import java.util.Random;

/**
 * Enum of the asteroid sizes with a size of small, medium, and large.
 * 
 * @author aidanchadha
 * @version 4/06/2023
 *
 */
public enum AsteroidSize {

    SMALL(10, 200), MEDIUM(20, 100), LARGE(30, 50);

    private int radius;
    private int points;

    /**
     * Constructor of the asteroid size.
     * 
     * @param radius radius of the asteroid.
     * @param points points of the asteroid.
     */
    private AsteroidSize(int radius, int points) {
        this.radius = radius;
        this.points = points;
    }

    /**
     * Getter for the asteroid radius.
     * 
     * @return the asteroid radius.
     */
    public int getRadius() {
        return this.radius;
    }

    /**
     * Getter for the asteroid points.
     * 
     * @return the asteroid points.
     */
    public int getPoints() {
        return this.points;
    }

    /**
     * Randomnly picks an asteroid between small, medium, and large, 50% chance of being large, 25% for medium and
     * small.
     * 
     * @return the asteroid chosen.
     */
    public static AsteroidSize randomSize() {
        Random r = new Random();
        double chosenAsteroid = r.nextDouble();
        if (chosenAsteroid <= .50) {
            return AsteroidSize.LARGE;
        } else if (chosenAsteroid <= .75) {
            return AsteroidSize.MEDIUM;
        } else {
            return AsteroidSize.SMALL;
        }
    }
}
