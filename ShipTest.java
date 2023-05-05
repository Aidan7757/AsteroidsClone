import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Ship test class for testing the ship class.
 * 
 * @author aidanchadha
 * @version 4/06/2023
 *
 */
public class ShipTest {
    private Ship ship;

    /**
     * Creates a new ship before every test.
     */
    @BeforeEach
    public void setup() {
        ship = new Ship();
    }

    /**
     * Tests if the ship implements the drawable and updatable interface.
     */
    @Test
    public void testsImplementsOfInterface() {
        assertTrue(ship instanceof Drawable);
        assertTrue(ship instanceof Updatable);
    }

    /**
     * Tests the draw method of the ship class.
     */
    @Test
    public void testDrawMethod() {

        Point llPoint = transformPoseToScreen(ship.pose, new Point(-.5 * Ship.SHIP_HEIGHT, -.5 * Ship.SHIP_WIDTH));
        Point lrPoint = transformPoseToScreen(ship.pose, new Point(-.5 * Ship.SHIP_HEIGHT, .5 * Ship.SHIP_WIDTH));
        Point endPoint = transformPoseToScreen(ship.pose, new Point(.5 * Ship.SHIP_HEIGHT, 0));

        double[] xs = new double[3];
        double[] ys = new double[3];

        xs[0] = llPoint.getX();
        xs[1] = endPoint.getX();
        xs[2] = lrPoint.getX();
        ys[0] = llPoint.getY();
        ys[1] = endPoint.getY();
        ys[2] = lrPoint.getY();

        ship.draw();

    }

    /**
     * Tests the thrust method of the ship class.
     */
    @Test
    public void testThrustMethod() {
        double magnitude = ship.velocity.getMagnitude();
        double xPosition = ship.pose.xPosition;
        ship.thrust();
        assertEquals(magnitude + .1, ship.velocity.getMagnitude());
        assertEquals(xPosition, ship.pose.getX());
        ship.thrust();
        assertEquals(magnitude + .2, ship.velocity.getMagnitude());
        ship.update();

    }

    /**
     * Tests the left turn method.
     */
    @Test
    public void testLeftTurn() {
        double heading = ship.pose.getHeading();
        ship.turnLeft();
        ship.update();
        assertEquals(ship.pose.getHeading(), heading + .1);
    }

    /**
     * Tests the right turn method.
     */
    @Test
    public void testRightTurn() {
        double heading = ship.pose.getHeading();
        ship.turnRight();
        ship.update();
        assertEquals(ship.pose.getHeading(), heading - .1);
    }

    /**
     * Tests the constructor for the ship objects.
     */
    @Test
    public void testConstructor() {
        assertEquals(ship.pose.xPosition, GameDriver.SCREEN_WIDTH / 2);
        assertEquals(ship.pose.yPosition, GameDriver.SCREEN_HEIGHT / 2);
    }

    /**
     * Tests the update method for the ship class.
     */
    @Test
    public void testUpdate() {
        double yPosition = ship.pose.yPosition;
        double magnitude = ship.velocity.getMagnitude();

        ship.thrust();
        ship.update();

        assertEquals(yPosition + .1, ship.pose.yPosition);
        assertEquals(magnitude + .08, ship.velocity.getMagnitude());

    }

    /**
     * Aids in testing the draw method of the ship class.
     * 
     * @param pose the new point.
     * @param point the point given.
     * @return new point.
     */
    private static Point transformPoseToScreen(Pose pose, Point point) {
        double newX = point.getX() * Math.cos(pose.getHeading()) - point.getY() * Math.sin(pose.getHeading())
                + pose.getX();
        double newY = point.getX() * Math.sin(pose.getHeading()) + point.getY() * Math.cos(pose.getHeading())
                + pose.getY();
        return new Point(newX, newY);
    }
}
