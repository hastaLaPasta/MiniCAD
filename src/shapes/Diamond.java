package shapes;

import fileio.implementations.FileReader;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

public class Diamond implements Visitable, Shape {
    private int xCenter;
    private int yCenter;
    private int horisontallyDiagonal;
    private int verticallyDiagonal;
    private String hexColorMargin;
    private int alphaLat;
    private String hexColorInterior;
    private int alphaInt;
    private Color rgbaLat;
    private Color rgbaInt;
    private boolean[][] checked;
    public Diamond(final FileReader fileReader)
            throws IOException {
        xCenter = fileReader.nextInt();
        yCenter = fileReader.nextInt();
        horisontallyDiagonal = fileReader.nextInt();
        verticallyDiagonal = fileReader.nextInt();
        hexColorMargin = fileReader.nextWord();
        alphaLat = fileReader.nextInt();
        hexColorInterior = fileReader.nextWord();
        alphaInt = fileReader.nextInt();
        rgbaInt = new Color(Color.decode(hexColorInterior).getRed(),
                Color.decode(hexColorInterior).getGreen(),
                Color.decode(hexColorInterior).getBlue(), alphaInt);
        rgbaLat = new Color(Color.decode(hexColorMargin).getRed(),
                Color.decode(hexColorMargin).getGreen(),
                Color.decode(hexColorMargin).getBlue(), alphaLat);
    }
    /**
     * Metoda pentru desenarea rombului.
     * @param image
     */
    @Override
    public void draw(final BufferedImage image) {
        int semiDiagHoriz = ((int) Math.round(horisontallyDiagonal / 2));
        int semiDiagVert = ((int) Math.round(verticallyDiagonal / 2));
        Line line1 = new Line(xCenter - semiDiagHoriz,
                yCenter, xCenter,
                yCenter + semiDiagVert, rgbaLat);
        Line line2 = new Line(xCenter,
                yCenter + semiDiagVert, xCenter + semiDiagHoriz,
                yCenter, rgbaLat);
        Line line3 = new Line(xCenter + semiDiagHoriz,
                yCenter, xCenter,
                yCenter - semiDiagVert, rgbaLat);
        Line line4 = new Line(xCenter,
                yCenter - semiDiagVert, xCenter - semiDiagHoriz,
                yCenter, rgbaLat);
        line1.draw(image);
        line2.draw(image);
        line3.draw(image);
        line4.draw(image);
        checked = new boolean[image.getWidth()][image.getHeight()];
        Circle.floodFill(image, xCenter, yCenter,
                rgbaInt.getRGB(), rgbaLat.getRGB());
    }
    public static void floodFill(final BufferedImage image,
                                 final int xCentru, final int yCentru,
                                 final int newColor, final int contur,
                                 final boolean[][] checked) {
        LinkedList<Coordinate> queue = new LinkedList<Coordinate>();
        queue.add(new Coordinate(xCentru, yCentru));
        while (!queue.isEmpty()) {
            Coordinate p = queue.remove();
            int xleft = p.x;
            while (xleft > 0) {
                if (image.getRGB(xleft - 1, p.y) == contur) {
                    break;
                }
                xleft--;
            }
            for (int i = xleft;
                 i < image.getWidth()
                         && image.getRGB(i, p.y) != contur;
                 i++) {
                image.setRGB(i, p.y, newColor);
                if (p.y < 0) {
                    break;
                }
                if (p.y + 1 == image.getHeight() - 1) {
                    image.setRGB(i, p.y + 1, newColor);
                    continue;
                }
                if (image.getRGB(i, p.y - 1) != contur
                        && !checked[i][p.y - 1]) {
                    checked[i][p.y - 1] = true;
                    queue.add(new Coordinate(i, p.y - 1));
                }
                if (image.getRGB(i, p.y + 1) != contur
                        && !checked[i][p.y + 1]) {
                    checked[i][p.y + 1] = true;
                    queue.add(new Coordinate(i, p.y + 1));
                }
            }
        }
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                checked[i][j] = false;
            }
        }
    }
    
    @Override
    public void accept(final Visitor v, final BufferedImage image) {
        v.visit(this, image);
    }

}
