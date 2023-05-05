/**
 * Bullet class that extends game element and implements updatable and drawable.
 * 
 * @author aidanchadha
 * @version 4/06/2023
 *
 */
public class Bullet extends GameElement {

    public static final double BULLET_RADIUS = 1.5;
    public static final int BULLET_SPEED = 20;
    public static final int BULLET_DURATION = 20;
    private int counter;

    /**
     * Constructor for the bullet class.
     * 
     * @param pose the current ship's pose.
     */
    public Bullet(Pose pose) {
        super(pose, new Vector2D(pose.getHeading(), Bullet.BULLET_SPEED), Bullet.BULLET_RADIUS);
        this.counter = Bullet.BULLET_DURATION;
    }

    /**
     * The update method that updates the position according to the magnitude and decrements the counter to make sure
     * the bullet is destroyed after 20 time steps.
     */
    public void update() {
        if (this.counter > 0) {
            super.update();
            this.counter--;
        } else {
            this.setDestroyed(true);
        }
    }

    /**
     * Draws the bullet as a circle.
     */
    public void draw() {
        StdDraw.filledCircle(this.pose.xPosition, this.pose.yPosition, BULLET_RADIUS);
    }

    /**
     * Getter for the counter.
     * 
     * @return the bullet counter.
     */
    public int getCounter() {
        return this.counter;
    }
}
