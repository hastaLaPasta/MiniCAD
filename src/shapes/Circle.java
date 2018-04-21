package shapes;

import fileio.implementations.FileReader;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Circle implements Visitable, Shape {
    private int xCenter;
    private int yCenter;
    private int radius;
    private String culoareHexLat;	//color of the edges
    private int alphaLat;
    private String culoareHexInt;	//color of the interior
    private int alphaInt;
    private Color rgbaLat;
    private Color rgbaInt;
    public Circle(final FileReader fileReader)
            throws IOException {
        xCenter = fileReader.nextInt();
        yCenter = fileReader.nextInt();
        radius = fileReader.nextInt();
        culoareHexLat = fileReader.nextWord();
        alphaLat = fileReader.nextInt();
        culoareHexInt = fileReader.nextWord();
        alphaInt = fileReader.nextInt();
        rgbaInt = new Color(Color.decode(culoareHexInt).getRed(),
                Color.decode(culoareHexInt).getGreen(),
                Color.decode(culoareHexInt).getBlue(), alphaInt);
        rgbaLat = new Color(Color.decode(culoareHexLat).getRed(),
                Color.decode(culoareHexLat).getGreen(),
                Color.decode(culoareHexLat).getBlue(), alphaLat);
    }
    private boolean condition(final BufferedImage image,
                              final int x, final int y) {
        if (x < image.getWidth() && x >= 0
                && y < image.getHeight() && y >= 0) {
            return true;
        }
        return false;
    }
    private void circlePixel(final BufferedImage image,
                             final int x, final int y) {
        if (condition(image, xCenter + x, yCenter + y)) {
            image.setRGB(xCenter + x, yCenter + y, rgbaLat.getRGB());
        }
        if (condition(image, xCenter - x, yCenter + y)) {
            image.setRGB(xCenter - x, yCenter + y, rgbaLat.getRGB());
        }
        if (condition(image, xCenter - x, yCenter - y)) {
            image.setRGB(xCenter - x, yCenter - y, rgbaLat.getRGB());
        }
        if (condition(image, xCenter + x, yCenter - y)) {
            image.setRGB(xCenter + x, yCenter - y, rgbaLat.getRGB());
        }
        if (condition(image, xCenter + y, yCenter + x)) {
            image.setRGB(xCenter + y, yCenter + x, rgbaLat.getRGB());
        }
        if (condition(image, xCenter - y, yCenter + x)) {
            image.setRGB(xCenter - y, yCenter + x, rgbaLat.getRGB());
        }
        if (condition(image, xCenter - y, yCenter - x)) {
            image.setRGB(xCenter - y, yCenter - x, rgbaLat.getRGB());
        }
        if (condition(image, xCenter + y, yCenter - x)) {
            image.setRGB(xCenter + y, yCenter - x, rgbaLat.getRGB());
        }
    }
    /**
     * Metoda pentru desenarea cercurilor.
     * @param image
     */
    @Override
    public void draw(final BufferedImage image) {
        int x = 0, y = radius;
        int d = 3 - 2 * radius;
        while (y >= x) {
            circlePixel(image, x, y);
            x++;
            if (d > 0) {
                y--;
                d = d + 4 * (x - y) + 10;
            } else {
                d = d + 4 * x + 6;
            }
            circlePixel(image, x, y);
        }
        floodFill(image, xCenter, yCenter,
                rgbaInt.getRGB(), rgbaLat.getRGB());
    }
    public static void floodFill(final BufferedImage image,
                                 final int xCentru, final int yCentru,
                                 final int newColor, final int contur) {
        int xleft = xCentru;
        while (xleft > 0) {
            if (image.getRGB(xleft - 1, yCentru) == contur) {
                break;
            }
            xleft--;
        }
        for (int i = xleft;
             i < image.getWidth()
                     && image.getRGB(i, yCentru) != contur;
             i++) {
            for (int j = yCentru; j < image.getHeight()
                    && image.getRGB(i, j) != contur;
                 j++) {
                image.setRGB(i, j, newColor);
            }
        }
        for (int i = xleft;
             i < image.getWidth()
                     && image.getRGB(i, yCentru) != contur;
             i++) {
            for (int j = yCentru; j >= 0
                    && image.getRGB(i, j) != contur;
                 j--) {
                image.setRGB(i, j, newColor);
            }
        }
    }

    @Override
    public void accept(final Visitor v, final BufferedImage image) {
        v.visit(this, image);
    }
}
