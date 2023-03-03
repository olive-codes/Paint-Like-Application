package controller;

import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;

import drawing.DrawingStrategy;
import model.interfaces.IDrawingStrategy;
import model.shape.ShapeInfo;

public class DrawTriangleStrategy implements IDrawingStrategy{

    
      public void draw(Graphics2D g2d, ShapeInfo si) {
        si.shapeG2D = getShape(si);
        DrawingStrategy ds = DrawingStrategy.getStrategy(si);
        ds.draw(si, g2d);
        
      }

      @Override
      public Shape getShape(ShapeInfo si) {
        int x = (int) si.x; // the first click
        int y = (int) si.y; // the first click
        int x2 = (int) si.x2; // where the mouse released
        int y2 = (int) si.y2; // where the mouse released

        int [] tri_x = {x, x2, x};
        int [] tri_y = {y, y2, y2};
        return new Polygon(tri_x, tri_y, 3);
      }
}