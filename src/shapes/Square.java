package shapes;

import fileio.implementations.FileReader;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Square implements Visitable, Shape {
    private int xStart;
    private int yStart;
    private int length;
    private String hexColorMargin;
    private int alphaLat;
    private String hexColorInterior;
    private int alphaInt;
    private Color rgbaLat;
    private Color rgbaInt;

    public Square(final FileReader fileReader)
            throws IOException {
        xStart = fileReader.nextInt();
        yStart = fileReader.nextInt();
        length = fileReader.nextInt();
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
     * Desenarea patratului.
     * @param image
     */
    @Override
    public void draw(final BufferedImage image) {
        for (int i = xStart; i < xStart + length; i++) {
            for (int j = yStart; j < yStart + length; j++) {
                if (i >= image.getWidth()) {
                    break;
                } else if (j >= image.getHeight()) {
                    break;
                }
                if (i == xStart) {
                    image.setRGB(i, j, rgbaLat.getRGB());
                    continue;
                } else if (j == yStart) {
                    image.setRGB(i, j, rgbaLat.getRGB());
                    continue;
                } else if (i == xStart + length - 1) {
                    image.setRGB(i, j, rgbaLat.getRGB());
                    continue;
                } else if (j == yStart + length - 1) {
                    image.setRGB(i, j, rgbaLat.getRGB());
                    continue;
                }
                image.setRGB(i, j, rgbaInt.getRGB());
            }
        }
    }

    /**
     * Metoda prin care se viziteaza patratul.
     * @param v
     */
    @Override
    public void accept(final Visitor v, final BufferedImage image) {
        v.visit(this, image);
    }
}
