
package simpledraw;

/**
 * @author Mathilde MALHERBE
 */
public interface ShapeVisitorI {
    void visit(Line line);
    void visit(Circle circle);
}
