/**
 * Ship class that extends the game element.
 * 
 * @author aidanchadha
 * @version 4/06/2023
 *
 */
public class Ship extends GameElement {

    public static final int SHIP_WIDTH = 10;
    public static final int SHIP_HEIGHT = 20;
    public static final double SHIP_TURN = .1;
    public static final double SHIP_MOVE = .1;
    public static final double FRICTION = .02;

    /**
     * Constructor for the ship class.
     */
    public Ship() {
        super(new Pose(GameDriver.SCREEN_WIDTH / 2, GameDriver.SCREEN_HEIGHT / 2, Math.PI / 2.0),
                new Vector2D(Math.PI / 2.0, 0), Ship.SHIP_WIDTH);

    }

    /**
     * Turn left method, turns the ship .1 to the left.
     */
    public void turnLeft() {
        this.pose = this.pose.newHeading(this.pose.getHeading() + Ship.SHIP_TURN);
    }

    /**
     * Turn right method, turn the ship .1 to the right.
     */
    public void turnRight() {
        this.pose = this.pose.newHeading(this.pose.getHeading() - Ship.SHIP_TURN);
    }

    /**
     * Thrust method, adds .1 to the ships current magnitude in the direction of the heading.
     */
    public void thrust() {
        Vector2D newVector = new Vector2D(this.pose.getHeading(), .1);
        this.velocity = this.velocity.add(newVector);
        // this.velocity = new Vector2D(this.pose.getHeading(), this.velocity.getMagnitude() + Ship.SHIP_MOVE);
    }

    /**
     * Update method that updates the ships position according to its magnitude, and applies friction to the ship.
     */
    public void update() {
        super.update();
        if (this.velocity.getMagnitude() > 0) {
            this.velocity = this.velocity.newMagnitude(Math.max(0, this.velocity.getMagnitude() - Ship.FRICTION));
        }
    }

    /**
     * Draws the ship as a triangle.
     */
    public void draw() {
        GameUtils.drawPoseAsTriangle(this.pose, Ship.SHIP_WIDTH, Ship.SHIP_HEIGHT);
    }

}
