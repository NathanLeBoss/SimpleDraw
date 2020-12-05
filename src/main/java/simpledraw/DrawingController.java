package simpledraw;

import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;
import java.util.Set;
import javax.swing.*;

public class DrawingController extends JComponent implements DrawingView, KeyListener, MouseListener, MouseMotionListener {

	private final Drawing myDrawing;

	private final Set<Shape> mySelectedShapes = new HashSet<Shape>();
	private DrawingTool myDrawingTool;

	public DrawingController(Drawing drawing) {
		myDrawing = drawing;
		setBackground(java.awt.Color.white);
		myDrawingTool = new SelectionTool(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);
		myDrawing.addDrawingView(this);
	}

	public Drawing getDrawing() {
		return this.myDrawing;
	}

	@Override
	public void drawHasChanged(DrawingEvent e) {
		repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		final Graphics2D g2 = (Graphics2D) g;
		RenderingHints qualityHints = new RenderingHints(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHints(qualityHints);
	}

	public boolean isSelected(Shape s) {
		return mySelectedShapes.contains(s);
	}

	public void selectShape(Shape s) {
		mySelectedShapes.add(s);
		repaint();
	}

	public void unselectShape(Shape s) {
		mySelectedShapes.remove(s);
		repaint();
	}

	public void clearSelection() {
		mySelectedShapes.clear();
		repaint();
	}

	public void addShape(Shape shape) {
		clearSelection();
		myDrawing.addShape(shape);
		selectShape(shape);
	}

	public void deleteSelection() {
		for (Shape s : mySelectedShapes)
			myDrawing.deleteShape(s);
	}

	public void translateSelectionBy(int dx, int dy) {
		for (Shape s : mySelectedShapes)
			myDrawing.translateShapeBy(s, dx, dy);
	}

	void activateSelectionTool() {
		myDrawingTool = new SelectionTool(this);
	}

	void activateCircleTool() {
		myDrawingTool = new CircleTool(this);
		clearSelection();
		repaint();
	}

	void activateLineTool() {
		myDrawingTool = new LineTool(this);
		clearSelection();
		repaint();
	}

	public void draw(Circle circle, Graphics2D graphics) {
		graphics.setColor(
				isSelected(circle) ?
						Color.red :
						Color.black
		);
		Point center = circle.getCenter();
		int radius = circle.getRadius();
		graphics.drawOval(center.x - radius,
				center.y - radius,
				radius * 2,
				radius * 2
		);
	}

	public void draw(Line line, Graphics2D graphics) {
		graphics.setColor(
				isSelected(line) ?
						Color.red :
						Color.black
		);
		graphics.drawLine(line.getStart().x,
				line.getStart().y,
				line.getEnd().x,
				line.getEnd().y);
	}

	/* Mouse and Key functions */

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyChar() == KeyEvent.VK_DELETE) {
			deleteSelection();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		myDrawingTool.mouseClicked(e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		myDrawingTool.mousePressed(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		myDrawingTool.mouseReleased(e);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		myDrawingTool.mouseEntered(e);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		myDrawingTool.mouseExited(e);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		myDrawingTool.mouseDragged(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		myDrawingTool.mouseMoved(e);
	}



}
