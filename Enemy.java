/**
 * Abstract enemy class that extends the game element.
 * 
 * @author aidanchadha
 * @version 4/06/2023
 *
 */
public abstract class Enemy extends GameElement {
    protected int points;

    /**
     * Constructor for the enemy objects.
     * 
     * @param speed the speed of the enemy
     * @param radius radius of the enemy
     * @param points points worth of the enemy
     */
    public Enemy(double speed, double radius, int points) {
        super(new Pose(GameDriver.GENERATOR.nextDouble() * GameDriver.SCREEN_WIDTH,
                GameDriver.GENERATOR.nextDouble() * GameDriver.SCREEN_HEIGHT,
                (2 * Math.PI) / GameDriver.GENERATOR.nextDouble()),
                new Vector2D((2 * Math.PI) / GameDriver.GENERATOR.nextDouble(), speed), radius);
        this.points = points;
    }

    /**
     * Getter for the points.
     * 
     * @return the enemy points.
     */
    public int getPoints() {
        return this.points;
    }
}
