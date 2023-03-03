package controller;

import model.interfaces.IDrawingStrategy;
import model.shape.ShapeInfo;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import drawing.DrawingStrategy;

public class DrawEllipseStrategy implements IDrawingStrategy {

      public void draw(Graphics2D g2d, ShapeInfo si) {
  
        si.shapeG2D = getShape(si);
        DrawingStrategy ds = DrawingStrategy.getStrategy(si);
        ds.draw(si, g2d);
      }

      @Override
      public Shape getShape(ShapeInfo si) {
        return new Ellipse2D.Double(si.x, si.y, si.width, si.height);
      }
    
}
