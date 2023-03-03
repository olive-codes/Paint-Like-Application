package drawing;

import model.ShapeShadingType;
import model.shape.ShapeInfo;

import java.awt.Graphics2D;
import java.util.HashMap;

//Static Factory Design Pattern

/**
 * <p>A {@link DrawingStrategy} is capable of drawing a {@code ShapeInfo}.</p>
 * 
 * To create a {@link DrawingStrategy} all you must do is extend {@link DrawingStrategy}
 * implement the {@link DrawingStrategy#draw(ShapeInfo, Graphics2D) } method and add the class to the
 * {@link DrawingStrategy#getStrategy(ShapeInfo)} method.
 */
public abstract class DrawingStrategy {

    private static final HashMap<ShapeShadingType, DrawingStrategy> lookup = new HashMap<>();

    static {
        lookup.put(ShapeShadingType.FILLED_IN, FilledInDrawingStrategy.INSTANCE);
        lookup.put(ShapeShadingType.OUTLINE, OutlineDrawingStrategy.INSTANCE);
        lookup.put(ShapeShadingType.OUTLINE_AND_FILLED_IN, OutlineAndFilledInDrawingStrategy.INSTANCE);
    }

    /**
     * Given a {@link ShapeInfo} to draw and a {@link Graphics2D} to draw on
     * renders the underlying shape.
     * @param si The {@link ShapeInfo} to draw
     * @param g2d The {@link Graphics2D} to draw on
     */
    public abstract void draw(ShapeInfo si, Graphics2D g2d);

    /**
     * Given a {@link ShapeInfo}, determines a {@link DrawingStrategy}
     * and returns it.
     * @param si A {@link ShapeInfo} object we would like to draw
     * @return A {@link DrawingStrategy} that can draw the {@link ShapeInfo}
     */
    public static DrawingStrategy getStrategy(ShapeInfo si) {
        if (!lookup.containsKey(si.shading)) 
            throw new IllegalArgumentException("Cannot find strategy for " + si.shading);
        return lookup.get(si.shading);
    }
}
