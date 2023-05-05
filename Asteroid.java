/**
 * Asteroid class that extends the enemy abstract class.
 * 
 * @author aidanchadha
 * @version 4/06/2023
 *
 */
public class Asteroid extends Enemy {
    public static final int ASTEROID_SPEED = 1;

    /**
     * Asteroid constructor.
     * 
     * @param size the size of the
     */
    public Asteroid(AsteroidSize size) {
        super(ASTEROID_SPEED, size.getRadius(), size.getPoints());
    }

    /**
     * Draws the asteroids.
     */
    public void draw() {
        StdDraw.setPenRadius(.002);
        StdDraw.circle(this.pose.xPosition, this.pose.yPosition, this.getRadius());
    }
}
