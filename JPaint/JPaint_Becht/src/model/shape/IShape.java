package model.shape;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import controller.BoundaryBox;

public interface IShape {
    public void draw(Graphics2D g2d);
    public BoundaryBox getBoundaryBox();
    public boolean intersects(Rectangle2D shape);
    public ShapeBuilder getBuilder();
    public void offSetX(double x);
    public void offSetY(double y);
}
