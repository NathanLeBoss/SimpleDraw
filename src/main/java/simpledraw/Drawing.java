package simpledraw;

import java.util.List;
import java.util.LinkedList;

import java.awt.Point;

/**
 * @author Nathan FERRER
 * @author Mathilde MALHERBE
 */
public class Drawing {
    /**
     * A drawing is a collection of shapes
     */
    private List<Shape> myShapes = new LinkedList<Shape>();
    private final List<DrawingViewI> myViews = new LinkedList<DrawingViewI>();

    public Drawing() {
    }

    public void accept(ShapeVisitorI visitor) {
        for (Shape s : myShapes) {
            s.accept(visitor);
        }
    }

    public void addDrawingView(DrawingViewI view) {
        myViews.add(view);
        view.drawHasChanged(new DrawingEvent(this));
    }

    /**
     * Add a shape to the Drawing
     *
     * @param s The Shape to add
     **/
    public void addShape(Shape s) {
        myShapes.add(s);
        notifyViews(new DrawingEvent(this));
    }

    /**
     * Delete a shape from the Drawing
     *
     * @param s The Shape to delete
     **/
    public void deleteShape(Shape s) {
        myShapes.remove(s);
        notifyViews(new DrawingEvent(this));
    }

    public void deleteAllShapes() {
        myShapes.clear();
        notifyViews(new DrawingEvent(this));
    }

    public void translateShapeBy(Shape shape, int dx, int dy) {
        shape.translateBy(dx, dy);
        notifyViews(new DrawingEvent(this));
    }

    /**
     * Determines whether the given Point lies whithin a Shape
     *
     * @param p The Point to test
     * @return A Shape selected by this Point or null if no Shape is there
     **/
    public Shape pickShapeAt(Point p) {
        Shape result = null;
        for (Shape s : myShapes)
            if (s.isPickedBy(p)) {
                result = s;
                break;
            }
        return result;
    }

    private void notifyViews(DrawingEvent e) {
        for (DrawingViewI view : myViews) {
            view.drawHasChanged(e);
        }
    }

}
