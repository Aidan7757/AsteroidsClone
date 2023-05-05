/**
 * Star class that implements the drawable interface.
 * 
 * @author aidanchadha
 * @version 3/30/2023
 *
 */
public class Star implements Drawable {
    public static final int STAR_RADIUS = 1;
    private Point location;

    /**
     * Star constructor.
     * 
     * @param x x value of the location of the star.
     * @param y y value of the location of the star.
     */
    public Star(double x, double y) {
        this.location = new Pose(x, y, 0);
    }

    /**
     * Getter for the Stars location.
     * 
     * @return the location.
     */
    public Point getLocation() {
        return this.location;
    }

    /**
     * Draw method.
     */
    public void draw() {
        // TODO Auto-generated method stub
        StdDraw.filledCircle(this.location.xPosition, this.location.yPosition, Star.STAR_RADIUS);
    }
}
