import fileio.implementations.FileReader;
import shapes.CANVAS;
import shapes.MyVisitor;
import shapes.SingletonPattern;
import shapes.Visitable;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public final class Main {
    private Main() {
    }
    public static void main(final String[] args)
            throws IOException {
        FileReader fileSystem = new FileReader(args[0]);
        Visitable[] shapes = SingletonPattern.getShapes(fileSystem);
        BufferedImage bufferedImage = new BufferedImage(((CANVAS) shapes[0]).getLatime(),
                ((CANVAS) shapes[0]).getInaltime(), BufferedImage.TYPE_INT_ARGB);
        MyVisitor myVisitor = new MyVisitor();
        for (Visitable c : shapes) {
            c.accept(myVisitor, bufferedImage);
        }
        ImageIO.write(bufferedImage, "PNG", new File("drawing.png"));
        fileSystem.close();
    }
}
