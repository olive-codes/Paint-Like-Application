package model;

import java.awt.Graphics2D;

import controller.CreateShapeCommand;
import model.shape.IShape;
import view.gui.PaintCanvas;

import java.util.ArrayList;

public class ShapeCollection {

  private ArrayList<IShape> shapeList;
  private ArrayList<CreateShapeCommand> observers = new ArrayList<CreateShapeCommand>();

  public ShapeCollection() {
    shapeList = new ArrayList<IShape>();
  }

  public void addShape(IShape shape) {
    shapeList.add(shape);
    notifyObservers();
  }

  public void removeShape(IShape shape) {
    shapeList.remove(shape);
    notifyObservers();
  }


  public void replaceShape(IShape shape, IShape new_shape) {
    shapeList.remove(shape);
    shapeList.add(new_shape);
    notifyObservers();
  }

  public void clearShapes() {
    shapeList.clear();
    notifyObservers();
  }

  public void updateCanvas() {
    notifyObservers();
  }

  public void printList() {
    for (IShape cs : shapeList) {
      System.out.println("shape: " + cs);
    }

  }

  //@Override
  public void registerObserver(CreateShapeCommand observer) {
    observers.add(observer);
  }


  //@Override
  public void notifyObservers() {
    for (CreateShapeCommand observer : observers) {
      observer.update();
      PaintCanvas.INSTANCE.repaint();
      
    }
  }

  public void draw(Graphics2D g2d) {
    for (IShape shape : shapeList) {
      shape.draw(g2d);
    }
  }

  public ArrayList<IShape> getShapes() {
    return shapeList;
  }

}