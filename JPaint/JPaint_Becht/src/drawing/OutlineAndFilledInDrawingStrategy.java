package drawing;

import java.awt.Graphics2D;

import model.shape.ShapeInfo;

public class OutlineAndFilledInDrawingStrategy extends DrawingStrategy {

    public static final DrawingStrategy INSTANCE = new OutlineAndFilledInDrawingStrategy();

    private OutlineAndFilledInDrawingStrategy () {}

    @Override
    public void draw(ShapeInfo si, Graphics2D g2d) {
        FilledInDrawingStrategy.INSTANCE.draw(si, g2d);
        OutlineDrawingStrategy.INSTANCE.draw(si, g2d);
    }


    
}
