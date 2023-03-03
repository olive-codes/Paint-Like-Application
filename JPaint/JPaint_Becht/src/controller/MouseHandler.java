package controller;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import model.ClipBoard;
import model.ListContainer;
import model.MouseMode;
import model.ShapeCollection;
import model.Commands.MoveCommand;
import model.persistence.ApplicationState;
import model.shape.IShape;
import model.shape.ShapeInfo;
import view.interfaces.PaintCanvasBase;

public class MouseHandler extends MouseAdapter {

  private static MouseHandler INSTANCE;

  public static MouseHandler initializeMouseHandler(ApplicationState appState, PaintCanvasBase paintCanvas) {
    if (MouseHandler.INSTANCE != null) {
      throw new IllegalStateException("MouseHandler may not be initialized more than once.");
    }
    MouseHandler.INSTANCE = new MouseHandler(appState, paintCanvas);
    return MouseHandler.INSTANCE;
  }

  public static MouseHandler getInstance() {
    if (MouseHandler.INSTANCE == null) {
      throw new IllegalStateException("Cannot get instance before initializing.");
    }
    return MouseHandler.INSTANCE;
  }

  ApplicationState appState;
  PaintCanvasBase paintCanvas;
  ShapeCollection shapeCollection;
  ShapeCollection selected;
  Point start, end;
  BoundaryBox boundaryBox;

  private MouseHandler(ApplicationState appState, PaintCanvasBase paintCanvas) {
    shapeCollection = ListContainer.getShapeList();
    selected = new ShapeCollection();
    this.appState = appState;
    this.paintCanvas = paintCanvas;
  }

  public List<IShape> getSelected() {
    if (boundaryBox == null) {
      return new ArrayList<>();
    }
    return boundaryBox.getSelected();
  }

  public void mousePressed(MouseEvent event) {
    start = event.getPoint();
    System.out.println(start);
  }

  public void mouseReleased(MouseEvent event) {
    end = event.getPoint();
    MouseMode MM = appState.getActiveMouseMode();
    // TEMP CODE:
    double[] start1 = { start.getX(), start.getY() };
    double[] end1 = { end.getX(), end.getY() };
    double width, height, x, y, x2, y2;

    // Switch x's if user drags from r->l

    width = Math.abs(end1[0] - start1[0]);
    x = start1[0];
    x2 = end1[0];
    height = Math.abs(end1[1] - start1[1]);
    y = start1[1];
    y2 = end1[1];

    // END TEMP CODE
    ShapeInfo shapeInfo = new ShapeInfo(appState, paintCanvas, start, end, x, y, x2, y2, width, height);
    CreateShapeCommand csc;

    switch (MM) {
      case DRAW:

        csc = new CreateShapeCommand(shapeInfo);

        shapeCollection.registerObserver(csc);
        shapeCollection.addShape(csc.getShape());
        csc.execute();
        paintCanvas.repaint();
        break;

      case SELECT:
        boundaryBox = new BoundaryBox(start, end);

        //OUTLINE Selected Shapes 

        if (!boundaryBox.getSelected().isEmpty()) {
          ClipBoard.INSTANCE.setSelected(boundaryBox.getSelected());
          paintCanvas.repaint();
        }


        break;

      case MOVE:
        double diffX = width;
        double diffY = height;
        System.out.println(ListContainer.getShapeList().getShapes());
        if (!ClipBoard.INSTANCE.getSelected().isEmpty()) {
          MoveCommand move = new MoveCommand(ClipBoard.INSTANCE.getSelected(), diffX, diffY, end1, start1, paintCanvas);
          move.execute();
          paintCanvas.repaint();
        }
        break;
        

    }
  }
}