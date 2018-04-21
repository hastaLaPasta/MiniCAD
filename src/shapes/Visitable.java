package shapes;

import java.awt.image.BufferedImage;

public interface Visitable {
    void accept(Visitor v, BufferedImage image);
}
