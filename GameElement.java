/**
 * Abstract Game element class that implements the Updatable interface.
 * 
 * @author aidanchadha
 * @version 3/30/2023
 * 
 *
 */
public abstract class GameElement implements Updatable, Drawable {
    protected Pose pose;
    protected Vector2D velocity;
    protected double radius;
    protected boolean destroyed = false;

    /**
     * Game element constructor.
     * 
     * @param pose provided pose.
     * @param velocity provided Vector2D.
     * @param radius provided radius.
     */
    public GameElement(Pose pose, Vector2D velocity, double radius) {
        this.pose = pose;
        this.velocity = new Vector2D(pose.getHeading(), velocity.getMagnitude());
        this.radius = radius;
    }

    /**
     * Update method.
     */
    public void update() {
        this.pose = this.pose.move(this.velocity);

        if (this.pose.getX() >= GameDriver.SCREEN_WIDTH) {
            this.pose = this.pose.newX(0);
        } else if (this.pose.getY() >= GameDriver.SCREEN_HEIGHT) {
            this.pose = this.pose.newY(0);
        } else if (this.pose.getX() <= 0) {
            this.pose = this.pose.newX(GameDriver.SCREEN_WIDTH);
        } else if (this.pose.getY() <= 0) {
            this.pose = this.pose.newY(GameDriver.SCREEN_HEIGHT);
        }
    }

    /**
     * Getter for the pose.
     * 
     * @return the pose.
     */
    public Pose getPose() {
        return this.pose;
    }

    /**
     * Getter for the velocity.
     * 
     * @return the velocity.
     */
    public Vector2D getVelocity() {
        return this.velocity;
    }

    /**
     * Getter for the radius.
     * 
     * @return the radius.
     */
    public double getRadius() {
        return this.radius;
    }

    /**
     * Getter for the destroyed attribute.
     * 
     * @return the destroyed attribute.
     */
    public boolean isDestroyed() {
        return this.destroyed;
    }

    /**
     * Setter for the destroyed attribute.
     * 
     * @param destroyed provided destroyed attribute.
     */
    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

    /**
     * Checks if two elements have collided with each other.
     * 
     * @param other the other element.
     * @return boolean value.
     */
    public boolean checkCollision(GameElement other) {
        double distanceX = Math.abs(other.getPose().getX() - this.getPose().getX());
        double distanceY = Math.abs(other.getPose().getY() - this.getPose().getY());
        if ((distanceX <= other.radius && distanceY <= other.radius)
                || (distanceX <= this.radius && distanceY <= this.radius)) {
            this.setDestroyed(true);
            other.setDestroyed(true);
            return true;
        }
        return false;
    }

}
