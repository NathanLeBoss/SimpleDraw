package simpledraw;

public interface ShapeVisitor {
    void visit(Line line);
    void visit(Circle circle);
}
