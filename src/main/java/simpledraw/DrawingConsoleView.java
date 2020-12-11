/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpledraw;

/**
 * View pour afficher le nombre de figures géometriques
 *
 * @author Nathan FERRER
 */
public class DrawingConsoleView implements DrawingViewI {
    private final Drawing myDrawing;
    private int nbLines;
    private int nbCircles;

    public DrawingConsoleView(Drawing drawing) {
        myDrawing = drawing;
        myDrawing.addDrawingView(this);
    }

    @Override
    public void drawHasChanged(DrawingEvent e) {
        nbLines = 0;
        nbCircles = 0;
        ShapeVisitorI visitor = new ShapeVisitorI() {

            @Override
            public void visit(Line line) {
                nbLines++;
            }

            @Override
            public void visit(Circle circle) {
                nbCircles++;
            }

        };

        myDrawing.accept(visitor);

        System.out.println("Lines=" + nbLines + " Circles=" + nbCircles);

    }
}
