import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Bullet test class.
 * 
 * @author aidanchadha
 * @version 4/06/2023
 *
 */
public class BulletTest {
    private Bullet bullet;

    /**
     * Creates a bullet and a position before every test.
     */
    @BeforeEach
    public void setup() {
        Pose pose = new Pose(GameDriver.SCREEN_WIDTH / 2, GameDriver.SCREEN_HEIGHT / 2, Math.PI / 2);
        bullet = new Bullet(pose);
    }

    /**
     * Tests if the bullet implements drawable and updatable.
     */
    @Test
    public void testsImplementsOfInterface() {
        assertTrue(bullet instanceof Drawable);
        assertTrue(bullet instanceof Updatable);
    }

    /**
     * Tests the bullets draw method.
     */
    @Test
    public void testDrawMethod() {
        bullet.draw();
    }

    /**
     * Tests the bullets constructor.
     */
    @Test
    public void testConstructor() {
        assertEquals(bullet.pose.xPosition, GameDriver.SCREEN_WIDTH / 2);
        assertEquals(bullet.pose.yPosition, GameDriver.SCREEN_HEIGHT / 2);
        assertEquals(bullet.getCounter(), 20);
    }

    /**
     * Tests the bullets update method.
     */
    @Test
    public void testUpdate() {
        double yPosition = bullet.pose.yPosition;
        bullet.update();
        assertEquals(bullet.pose.yPosition, yPosition + 20);
        for (int i = 0; i < 25; i++) {
            bullet.update();
        }
        bullet.isDestroyed();
    }

}
