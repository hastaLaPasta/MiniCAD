package shapes;

import fileio.implementations.FileReader;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Rectangle implements Visitable, Shape {
    private int xStart;
    private int yStart;
    private int height;
    private int length;
    private String culoareHexLat;
    private int alphaLat;
    private String culoareHexInt;
    private int alphaInt;
    private Color rgbaLat;
    private Color rgbaInt;

    public Rectangle(final FileReader fileReader)
            throws IOException {
        xStart = fileReader.nextInt();
        yStart = fileReader.nextInt();
        height = fileReader.nextInt();
        length = fileReader.nextInt();
        culoareHexLat = fileReader.nextWord();
        alphaLat = fileReader.nextInt();
        culoareHexInt = fileReader.nextWord();
        alphaInt = fileReader.nextInt();
        rgbaLat = new Color(Color.decode(culoareHexLat).getRed(),
                Color.decode(culoareHexLat).getGreen(),
                Color.decode(culoareHexLat).getBlue(), alphaLat);
        rgbaInt = new Color(Color.decode(culoareHexInt).getRed(),
                Color.decode(culoareHexInt).getGreen(),
                Color.decode(culoareHexInt).getBlue(), alphaInt);
    }

    /**
     * Metoda pentru desenarea dreptunghiului.
     * @param image
     */
    @Override
    public void draw(final BufferedImage image) {
        for (int i = xStart; i < xStart + length; i++) {
            for (int j = yStart; j < yStart + height; j++) {
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
                } else if (j == yStart + height - 1) {
                    image.setRGB(i, j, rgbaLat.getRGB());
                    continue;
                }
                image.setRGB(i, j, rgbaInt.getRGB());
            }
        }
    }

    @Override
    public void accept(final Visitor v, final BufferedImage image) {
        v.visit(this, image);
    }
}
