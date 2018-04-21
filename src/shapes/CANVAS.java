package shapes;


import fileio.implementations.FileReader;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class CANVAS implements Visitable, Shape {
    private int height;
    private int length;
    private String hexColor;
    private int alpha;
    private Color rgba;

    public CANVAS(final FileReader fileReader)
            throws IOException {
        height = fileReader.nextInt();
        length = fileReader.nextInt();
        hexColor = fileReader.nextWord();
        alpha = fileReader.nextInt();
        rgba = new Color(Color.decode(hexColor).getRed(),
                Color.decode(hexColor).getGreen(),
                Color.decode(hexColor).getBlue(), alpha);
    }
    
    public int getHeight() {
        return height;
    }
    
    public int getLength() {
        return length;
    }
    /**
     * Drawing the canvas.
     * @param image
     */
    @Override
    public void draw(final BufferedImage image) {
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < height; j++) {
                image.setRGB(i, j, rgba.getRGB());
            }
        }
    }
    
    @Override
    public void accept(final Visitor v, final BufferedImage image) {
        v.visit(this, image);
    }
}
