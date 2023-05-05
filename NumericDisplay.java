/**
 * Numeric display class that implements the drawable interface.
 * 
 * @author aidanchadha
 * @version 3/30/2023
 *
 */
public class NumericDisplay implements Drawable {

    private String prefix;
    private int value;
    private Point location;

    /**
     * Numeric display constructor.
     * 
     * @param xPos Provided x position.
     * @param yPos Provided y position.
     * @param prefix Provided prefix.
     * @param value Provided value.
     */
    public NumericDisplay(int xPos, int yPos, String prefix, int value) {
        this.prefix = prefix;
        this.value = value;
        this.location = new Point(xPos, yPos);
    }

    /**
     * Draw method.
     */
    public void draw() {
        StdDraw.textLeft(this.location.xPosition, this.location.yPosition, this.prefix + this.value);
    }

    /**
     * Getter for the numeric displays point.
     * 
     * @return the location.
     */
    public Point getLocation() {
        return this.location;
    }

    /**
     * Getter for the value.
     * 
     * @return the value.
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Setter for the value.
     * 
     * @param value Provided value.
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Getter for the prefix.
     * 
     * @return the prefix.
     */
    public String getPrefix() {
        return this.prefix;
    }

}
