package shapes;

import fileio.implementations.FileReader;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Polygon implements Visitable, Shape {
    private int nrPoints;
    private Coordinate[] puncte;
    private String culoareHexLat;
    private int alphaLat;
    private String culoareHexInt;
    private int alphaInt;
    private Color rgbaLat;
    private Color rgbaInt;
    private boolean[][] checked;
    public Polygon(final FileReader fileReader)
            throws IOException {
        nrPoints = fileReader.nextInt();
        puncte = new Coordinate[nrPoints];
        for (int i = 0; i < puncte.length; i++) {
            puncte[i] = new Coordinate(fileReader.nextInt(),
                    fileReader.nextInt());
        }
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

    /**
     * Metoda pentru desenarea poligoanelor.
     * @param image
     */
    @Override
    public void draw(final BufferedImage image) {
        Line[] linii = new Line[nrPoints];
        int sumaX = 0, sumaY = 0;
        for (int i = 0; i < linii.length - 1; i++) {
            linii[i] = new Line(puncte[i].x, puncte[i].y,
                    puncte[i + 1].x, puncte[i + 1].y, rgbaLat);
        }
        for (int i = 0; i < puncte.length; i++) {
            sumaX += puncte[i].x;
            sumaY += puncte[i].y;
        }
        linii[linii.length - 1] = new Line(puncte[puncte.length - 1].x,
                puncte[puncte.length - 1].y, puncte[0].x, puncte[0].y, rgbaLat);
        for (int i = 0; i < linii.length; i++) {
            linii[i].draw(image);
        }
        checked = new boolean[image.getWidth()][image.getHeight()];
        Diamond.floodFill(image, ((int) Math.round(sumaX / nrPoints)),
                ((int) Math.round(sumaY / nrPoints)),
                rgbaInt.getRGB(), rgbaLat.getRGB(), checked);
    }

    /**
     * Metoda pentru vizitarea poligoanelor.
     * @param v
     * @param image
     */
    @Override
    public void accept(final Visitor v, final BufferedImage image) {
        v.visit(this, image);
    }
}
