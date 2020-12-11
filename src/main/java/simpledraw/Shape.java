package simpledraw;

import java.awt.Point;

/**
 * @author Mathilde MALHERBE
 */
public abstract class Shape {

    abstract public void accept(ShapeVisitorI visitor);


    /**
     * Translates this shape
     *
     * @param dx delta x
     * @param dy delta y
     */
    abstract public void translateBy(int dx, int dy);

    /**
     * Determines if the given point is inside this shape
     *
     * @param p the point to test
     * @return true if <code>p</code> inside the shape, false otherwise
     */
    abstract public boolean isPickedBy(Point p);
}
