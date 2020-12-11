package simpledraw;

import java.awt.Point;

/**
 * A circle
 *
 * @author Nathan FERRER
 **/

public class Circle extends Shape {
    private Point myCenter;
    private int myRadius;

    /**
     * Construct a Circle
     *
     * @param center The center of the circle
     * @param radius The radius of the circle
     **/
    public Circle(Point center, int radius) {
        myCenter = center;
        myRadius = radius;
    }

    public Point getCenter() {
        return myCenter;
    }

    public int getRadius() {
        return myRadius;
    }

    @Override
    public void translateBy(int dx, int dy) {
        myCenter.translate(dx, dy);
    }

    @Override
    public boolean isPickedBy(Point p) {
        return (Math.abs(myCenter.distance(p) - myRadius) <= 2);
    }

    @Override
    public void accept(ShapeVisitorI visitor) {
        visitor.visit(this);
    }

}