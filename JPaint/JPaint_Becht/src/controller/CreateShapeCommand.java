package controller;

import java.awt.Graphics2D;

import model.GraphicsSingleton;
import model.ListContainer;
import model.ShapeCollection;
import model.Commands.CommandHistory;
import model.interfaces.ICommand;
import model.interfaces.IUndoable;
import model.shape.IShape;

public class CreateShapeCommand implements ICommand, IUndoable {

  private IShape shape;


  public CreateShapeCommand(IShape shape) {
    this.shape = shape;
  }


  public void update() {
    ShapeCollection shapelist = ListContainer.getShapeList();
    for (IShape shape : shapelist.getShapes()) {
      Graphics2D g2d = GraphicsSingleton.getInstance().getG2D();
      shape.draw(g2d);
    }
  }

  public void execute() {
    CommandHistory.add(this);
  }

  @Override
  public void undo() {
    // CreateShapeCommand outline;
    // if (this.shapeInfo.outlineShape != null) {
    //   outline = this.shapeInfo.outlineShape;
    //   ListContainer.getShapeList().removeShape(outline);
    // }
    ListContainer.getShapeList().removeShape(this.shape);
  }

  @Override
  public void redo() {
    ListContainer.getShapeList().addShape(this.shape);
  }


  public IShape getShape() {
    return shape;
  }
}