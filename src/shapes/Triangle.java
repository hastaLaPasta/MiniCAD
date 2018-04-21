package shapes;

import fileio.implementations.FileReader;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Triangle implements Shape, Visitable {
    private int xPoint1;
    private int yPoint1;
    private int xPoint2;
    private int yPoint2;
    private int xPoint3;
    private int yPoint3;
    private String culoareHexLat;
    private int alphaLat;
    private String culoareHexInt;
    private int alphaInt;
    private Color rgbaLat;
    private Color rgbaInt;
    private Line lineP1;
    private Line lineP2;
    private Line lineP3;
    private boolean[][] checked;
    static final int NR_POINTS = 3;
    public Triangle(final FileReader fileReader)
            throws IOException {
        xPoint1 = fileReader.nextInt();
        yPoint1 = fileReader.nextInt();
        xPoint2 = fileReader.nextInt();
        yPoint2 = fileReader.nextInt();
        xPoint3 = fileReader.nextInt();
        yPoint3 = fileReader.nextInt();
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
        lineP1 = new Line(xPoint1, yPoint1, xPoint2, yPoint2, rgbaLat);
        lineP2 = new Line(xPoint2, yPoint2, xPoint3, yPoint3, rgbaLat);
        lineP3 = new Line(xPoint3, yPoint3, xPoint1, yPoint1, rgbaLat);
    }

    @Override
    public void draw(final BufferedImage image) {
        lineP1.draw(image);
        lineP2.draw(image);
        lineP3.draw(image);
        int xCentruGreutate = ((int) Math.round(
                (xPoint1 + xPoint2 + xPoint3) / NR_POINTS));
        int yCentruGreutate = ((int) Math.round(
                    (yPoint1 + yPoint2 + yPoint3) / NR_POINTS));
        checked = new boolean[image.getWidth()][image.getHeight()];
        Diamond.floodFill(image, xCentruGreutate,
                yCentruGreutate, rgbaInt.getRGB(), rgbaLat.getRGB(), checked);
    }

    @Override
    public void accept(final Visitor v, final BufferedImage image) {
        v.visit(this, image);
    }
}
