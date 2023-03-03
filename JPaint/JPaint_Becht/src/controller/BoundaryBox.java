package controller;

import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;


import model.ListContainer;
import model.shape.IShape;

public class BoundaryBox {

    private final double x;
    private final double y;
    private final double w;
    private final double h;
    private final Rectangle2D shape;

    public BoundaryBox(Point start, Point end) {

        double[] start1 = { start.getX(), start.getY() };
        double[] end1 = { end.getX(), end.getY() };

        // Switch x's if user drags from r->l

        this.w = Math.abs(end1[0] - start1[0]);
        this.x = start1[0];
        this.h = Math.abs(end1[1] - start1[1]);
        this.y = start1[1];
        this.shape = new Rectangle2D.Double(x, y, w, h);
    }

    public BoundaryBox(double x, double y, double w, double h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.shape = new Rectangle2D.Double(x, y, w, h);
    }

    public List<IShape> getSelected() {
        ArrayList<IShape> shapelist = ListContainer.getShapeList().getShapes();
        ArrayList<IShape> selected = new ArrayList<>();
        for (IShape shape : shapelist) {
            if (shape.intersects(this.shape)) {
                selected.add(shape);
           
            }
        }
        return selected;
    }

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public double getWidth() {
        return w;
    }
    public double getHeight() {
        return h;
    }

    public Rectangle2D getShape() {
        return shape;
    }

}