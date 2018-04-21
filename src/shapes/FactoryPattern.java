package shapes;

import fileio.implementations.FileReader;

import java.io.IOException;

public class FactoryPattern {
    /**
     * Reads number of shapes.
     * @param fileReader
     * @return
     * @throws IOException
     */
    public static int getNrShapes(final FileReader fileReader)
            throws IOException {
        return fileReader.nextInt();
    }

    /**
     * Shapes creation.
     * @param fileReader
     * @return
     * @throws IOException
     */
    public Visitable getShape(final FileReader fileReader)
            throws IOException {
        String type = fileReader.nextWord();
        if (type.equalsIgnoreCase("CANVAS")) {
            return new CANVAS(fileReader);
        } else if (type.equalsIgnoreCase("LINE")) {
            return new Line(fileReader);
        } else if (type.equalsIgnoreCase("SQUARE")) {
            return new Square(fileReader);
        } else if (type.equalsIgnoreCase("RECTANGLE")) {
            return new Rectangle(fileReader);
        } else if (type.equalsIgnoreCase("CIRCLE")) {
            return new Circle(fileReader);
        } else if (type.equalsIgnoreCase("TRIANGLE")) {
            return new Triangle(fileReader);
        } else if (type.equalsIgnoreCase("DIAMOND")) {
            return new Diamond(fileReader);
        } else if (type.equalsIgnoreCase("POLYGON")) {
            return new Polygon(fileReader);
        }
        return null;
    }
}
