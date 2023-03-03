package controller;

import java.awt.Graphics2D;

import drawing.DrawingStrategy;

import java.awt.Rectangle;
import java.awt.Shape;

import model.interfaces.IDrawingStrategy;
import model.shape.ShapeInfo;

public class DrawRectangleStrategy implements IDrawingStrategy {


  public void draw(Graphics2D g2d, ShapeInfo si) {
    si.shapeG2D = getShape(si);
    DrawingStrategy ds = DrawingStrategy.getStrategy(si);
    ds.draw(si, g2d);
  }

  @Override
  public Shape getShape(ShapeInfo si) {
    int x = (int) si.x;
    int y = (int) si.y;
    int w = (int) si.width;
    int h = (int) si.height;
    return new Rectangle(x, y, w, h);
  }
}