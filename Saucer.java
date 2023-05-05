import java.util.Random;

/**
 * Saucer class that extends enemy.
 * 
 * @author aidanchadha
 * @version 4/06/2023
 *
 */
public class Saucer extends Enemy {

    public static final int HALF_WIDTH = 10;
    public static final int HALF_HEIGHT = 5;
    public static final int SAUCER_SPEED = 2;
    public static final int SAUCER_POINTS = 400;
    public static final double SPAWN_PROB = .002;
    public static final double TURN_PROB = .05;

    /**
     * Saucer constructor.
     */
    public Saucer() {
        super(Saucer.SAUCER_SPEED, Saucer.HALF_WIDTH, Saucer.SAUCER_POINTS);
    }

    /**
     * Saucer draw method.
     */
    public void draw() {
        StdDraw.rectangle(this.pose.xPosition, this.pose.yPosition, HALF_WIDTH, HALF_HEIGHT);
    }

    /**
     * Saucer update method, does not wrap around the screen.
     */
    public void update() {
        Random r = new Random();
        int movementChoice = r.nextInt(100);
        if (movementChoice <= 4) {
            this.pose = this.pose.newHeading((2 * Math.PI) / GameDriver.GENERATOR.nextDouble());
            this.velocity = this.velocity.newHeading(this.pose.getHeading());
        }
        this.pose = this.pose.move(this.velocity);
        if (this.pose.xPosition > GameDriver.SCREEN_WIDTH || this.pose.yPosition > GameDriver.SCREEN_HEIGHT
                || this.pose.xPosition < 0 || this.pose.yPosition < 0) {
            this.setDestroyed(true);
        }
    }
}
