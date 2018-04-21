package shapes;

import fileio.implementations.FileReader;

import java.io.IOException;

public final class SingletonPattern {
    private static Visitable[] shapes;
    private SingletonPattern() {
    }
    public static Visitable[] getShapes(final FileReader fileReader)
            throws  IOException {
        shapes = new Visitable[FactoryPattern.getNrShapes(fileReader)];
        FactoryPattern factoryPattern = new FactoryPattern();
        for (int i = 0; i < shapes.length; i++) {
            shapes[i] = factoryPattern.getShape(fileReader);
        }
        return shapes;
    }
}
