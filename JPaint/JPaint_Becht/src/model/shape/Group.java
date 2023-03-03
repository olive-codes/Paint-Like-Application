package model.shape;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;
import java.util.List;


import java.awt.Stroke;


import java.awt.BasicStroke;
import java.awt.Color;

import controller.BoundaryBox;
import model.ClipBoard;

public class Group implements IShape {

    private final List<IShape> groupedElements;

    public Group(List<IShape> elements) {
        this.groupedElements = new LinkedList<>(elements);
    }

    @Override
    public void draw(Graphics2D g2d) {
        for (IShape s : this.groupedElements) {
            s.draw(g2d);
        }
        BoundaryBox box = this.getBoundaryBox();
        // TODO: make the right g2d modificaitons.
        if (ClipBoard.INSTANCE.getSelected().contains(this)) {
            Stroke dash = new BasicStroke(3, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND,
                    0, new float[]{15}, 5);
                g2d.setStroke(dash);
            g2d.setColor(Color.BLACK);
            g2d.draw(box.getShape());
        }
    }

    @Override
    public BoundaryBox getBoundaryBox() {
        double minX, maxX, minY, maxY;
        minX = minY = Double.POSITIVE_INFINITY;
        maxX = maxY = Double.NEGATIVE_INFINITY;
        for (IShape s : this.groupedElements) {
            BoundaryBox inner = s.getBoundaryBox();
            minX = Math.min(inner.getX(), minX);
            minY = Math.min(inner.getY(), minY);
            maxX = Math.max(inner.getX() + inner.getWidth(), maxX);
            maxY = Math.max(inner.getY() + inner.getHeight(), maxY);
        }
        return new BoundaryBox(minX, minY, maxX - minX, maxY - minY);
    }

    @Override
    public boolean intersects(Rectangle2D shape) {
        for (IShape s : this.groupedElements) {
            if (s.intersects(shape)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public ShapeBuilder getBuilder() {
        return new GroupBuilder(groupedElements);
    }

    @Override
    public void offSetX(double x) {
        for (IShape shape : this.groupedElements) {
            shape.offSetX(x);
        }
        
    }

    @Override
    public void offSetY(double y) {
        for (IShape shape : this.groupedElements) {
            shape.offSetY(y);
        }
        
    }

    public List<IShape> getShapes() {
        return groupedElements;
    }

}
