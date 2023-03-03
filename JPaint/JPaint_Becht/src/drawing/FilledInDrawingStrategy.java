package drawing;

import java.awt.Color;
import java.awt.Graphics2D;

import model.shape.ShapeInfo;

public class FilledInDrawingStrategy extends DrawingStrategy {

    /* This is the singleton pattern. We only ever want ONE instance of FilledInDrawingStrategy */
    public static final DrawingStrategy INSTANCE = new FilledInDrawingStrategy();
    
    /* Prevents anyone outside of this class from constructing it */
    private FilledInDrawingStrategy() {}

    @Override
    public void draw(ShapeInfo si, Graphics2D g2d) {
        Color primaryColor = si.primaryColor.getColor();
        g2d.setColor(primaryColor);
        g2d.fill(si.shapeG2D);
    }
    
}
