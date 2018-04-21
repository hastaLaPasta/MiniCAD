package shapes;

import java.awt.image.BufferedImage;

public class MyVisitor implements Visitor {
    /**
     * Drawing Canvas using Visitor Pattern.
     * @param canvas
     * @param image
     */
    @Override
    public void visit(final CANVAS canvas, final BufferedImage image) {
        canvas.draw(image);
    }

    /**
     * Drawing lines.
     * @param line
     * @param image
     */
    @Override
    public void visit(final Line line, final BufferedImage image) {
        line.draw(image);
    }

    /**
     * Drawing squares.
     * @param square
     * @param image
     */
    @Override
    public void visit(final Square square, final BufferedImage image) {
        square.draw(image);
    }

    /**
     * Drawing rectangle.
     * @param rectangle
     * @param image
     */
    @Override
    public void visit(final Rectangle rectangle, final BufferedImage image) {
        rectangle.draw(image);
    }

    /**
     * Drawing circles.
     * @param circle
     * @param image
     */
    @Override
    public void visit(final Circle circle, final BufferedImage image) {
        circle.draw(image);
    }

    /**
     * Drawing triangles.
     * @param triangle
     * @param image
     */
    @Override
    public void visit(final Triangle triangle, final BufferedImage image) {
        triangle.draw(image);
    }

    /**
     * Drawing diamonds.
     * @param diamond
     * @param image
     */
    @Override
    public void visit(final Diamond diamond, final BufferedImage image) {
        diamond.draw(image);
    }

    /**
     * Drawing polygons.
     * @param polygon
     * @param image
     */
    @Override
    public void visit(final Polygon polygon, final BufferedImage image) {
        polygon.draw(image);
    }
}
