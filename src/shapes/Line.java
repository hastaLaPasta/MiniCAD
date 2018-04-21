package shapes;

import fileio.implementations.FileReader;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Line implements Visitable, Shape {
    private int xStart;
    private int yStart;
    private int xFinal;
    private int yFinal;
    private String hexColor;
    private int alpha;
    private Color rgba;

    public Line(final FileReader fileReader)
            throws IOException {
        xStart = fileReader.nextInt();
        yStart = fileReader.nextInt();
        xFinal = fileReader.nextInt();
        yFinal = fileReader.nextInt();
        hexColor = fileReader.nextWord();
        alpha = fileReader.nextInt();
        rgba = new Color(Color.decode(hexColor).getRed(),
                Color.decode(hexColor).getGreen(),
                Color.decode(hexColor).getBlue(), alpha);
    }

    public Line(final int xStart, final int yStart,
                final int xFinal, final int yFinal,
                final Color rgba) {
        this.xStart = xStart;
        this.yStart = yStart;
        this.xFinal = xFinal;
        this.yFinal = yFinal;
        this.rgba = rgba;
    }

    /**
     * Method for drawing line.
     * @param image
     */
    @Override
    public void draw(final BufferedImage image) {
        int x = xStart;
        int y = yStart;
        int deltaX = Math.abs(xFinal - xStart);
        int deltaY = Math.abs(yFinal - yStart);
        int s1 = ((int) Math.signum(xFinal - xStart));
        int s2 = ((int) Math.signum(yFinal - yStart));
        boolean interchanged;
        if (deltaY > deltaX) {
            int aux = deltaX;
            deltaX = deltaY;
            deltaY = aux;
            interchanged = true;
        } else {
            interchanged = false;
        }
        int error = 2 * deltaY - deltaX;
        for (int i = 0; i <= deltaX; i++) {
            if ((x <= image.getWidth() - 1 && y <= image.getHeight() - 1)
                    && (x >= 0 && y >= 0)) {
                image.setRGB(x, y, rgba.getRGB());
                while (error > 0) {
                    if (interchanged) {
                        x += s1;
                    } else {
                        y += s2;
                    }
                    error = error - 2 * deltaX;
                }
                if (interchanged) {
                    y += s2;
                } else {
                    x += s1;
                }
                error += 2 * deltaY;
            } else {
                while (error > 0) {
                    if (interchanged) {
                        x += s1;
                    } else {
                        y += s2;
                    }
                    error = error - 2 * deltaX;
                }
                if (interchanged) {
                    y += s2;
                } else {
                    x += s1;
                }
                error += 2 * deltaY;
            }
        }
    }

    @Override
    public void accept(final Visitor v, final BufferedImage image) {
        v.visit(this, image);
    }
}
