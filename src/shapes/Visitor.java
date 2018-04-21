package shapes;

import java.awt.image.BufferedImage;

public interface Visitor {
    void visit(CANVAS canvas, BufferedImage image);
    void visit(Line line, BufferedImage image);
    void visit(Square square, BufferedImage image);
    void visit(Rectangle rectangle, BufferedImage image);
    void visit(Circle circle, BufferedImage image);
    void visit(Triangle triangle, BufferedImage image);
    void visit(Diamond diamond, BufferedImage image);
    void visit(Polygon polygon, BufferedImage image);
}
