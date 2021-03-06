package simpledraw;

import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;

/**
 * @author Mathilde MALHERBE
 * @author Nathan FERRER
 */
public class SelectionTool extends DrawingTool {
    private Point myLastPoint;

    public SelectionTool(DrawingController panel) {
        super(panel);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Shape pickedShape = myPanel.getDrawing().pickShapeAt(e.getPoint());
        myLastPoint = e.getPoint();
        if (pickedShape != null) {
            myPanel.selectShape(pickedShape);
            myPanel.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
        } else {
            myPanel.clearSelection();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseMoved(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Shape pickedShape = myPanel.getDrawing().pickShapeAt(e.getPoint());
        if (pickedShape != null) {
            myPanel.setCursor(Cursor.getPredefinedCursor(Cursor.
                    HAND_CURSOR));
        } else {
            myPanel.setCursor(Cursor.getDefaultCursor());
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Point point = e.getPoint();
        myPanel.translateSelectionBy(
                (int) Math.floor(point.getX() - myLastPoint.x),
                (int) Math.floor(point.getY() - myLastPoint.y)
        );
        myLastPoint = point;
    }

    @Override
    void draw(Graphics2D g) {
    }

}
