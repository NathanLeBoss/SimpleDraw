package simpledraw;

import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * The tool to select, move and delete Shapes in the Drawing
 * @author Rémi Bastide
 * @version 1.0
 */

public class SelectionTool
	extends DrawingTool {
	private Shape mySelectedShape = null;
	private Point myLastPoint;

	public SelectionTool(DrawingController panel) {
		super(panel);
	}

	public void mousePressed(MouseEvent e) {
		Shape pickedShape = myPanel.getDrawing().pickShapeAt(e.getPoint());
		myLastPoint = e.getPoint();
		if(pickedShape != null) {
			myPanel.selectShape(pickedShape);
			myPanel.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
		}
		else {
			myPanel.clearSelection();
		}
	}

	public void mouseReleased(MouseEvent e) {
		mouseMoved(e);
	}

	public void mouseMoved(MouseEvent e) {
		Shape pickedShape = myPanel.getDrawing().pickShapeAt(e.getPoint());
		if (pickedShape != null) {
			myPanel.setCursor(Cursor.getPredefinedCursor(Cursor.
				HAND_CURSOR));
		} else {
			myPanel.setCursor(Cursor.getDefaultCursor());
		}
	}

	public void mouseDragged(MouseEvent e) {
		if (mySelectedShape != null) {
			mySelectedShape.translateBy(
				e.getX() - myLastPoint.x,
				e.getY() - myLastPoint.y
				);
			myLastPoint = e.getPoint();
        		myPanel.repaint();
		}
	}

	void draw(Graphics2D g) {
	}

}
