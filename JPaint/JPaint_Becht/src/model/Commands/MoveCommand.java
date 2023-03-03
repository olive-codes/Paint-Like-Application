package model.Commands;
// Java convention for packages should start with organization name
// edu.unm.jcollard.model.commands

import java.util.LinkedList;
import java.util.List;

import model.ListContainer;
import model.interfaces.ICommand;
import model.interfaces.IUndoable;
import model.shape.IShape;
import view.interfaces.PaintCanvasBase;

public class MoveCommand implements IUndoable, ICommand {

    private final List<IShape> shapes;

    private final double diffX, diffY;
    private final double[] end1;
    private final double[] start1;

    private final PaintCanvasBase paintCanvas;


    public MoveCommand(List<IShape> selected, double diffX, double diffY, double[] end1, double[] start1, PaintCanvasBase paintCanvas) {
        shapes = new LinkedList<>(selected);
        this.diffX = diffX;
        this.diffY = diffY;
        this.end1 = end1;
        this.start1 = start1;
        this.paintCanvas = paintCanvas;
    }

    @Override
    public void execute() {
        doMove();
        CommandHistory.add(this);
    }

    @Override
    public void undo() {
        doMove(true);
    }

    @Override
    public void redo() {
        doMove();
    }

    private final void doMove() {
        doMove(false);
    }
    
    private final void doMove(boolean isUndo) {
        int multiplier = isUndo ? -1 : 1;
        for (IShape shape : shapes) {
            if (ListContainer.getShapeList().getShapes().contains(shape)) {
              //add to List Container with new measurements
  
              int indexOfSelectedShape = ListContainer.getShapeList().getShapes().indexOf(shape);
              IShape getSelectedShape = ListContainer.getShapeList().getShapes().get(indexOfSelectedShape);
   
              if (end1[0] > start1[0]) {
                getSelectedShape.offSetX(diffX*multiplier);
                // getSelectedShape.shapeInfo.x = getSelectedShape.shapeInfo.x + diffX*multiplier;
                // getSelectedShape.shapeInfo.x2 = getSelectedShape.shapeInfo.x2 + diffX*multiplier;
            }
              else {
                getSelectedShape.offSetX(-diffX*multiplier);
                // getSelectedShape.shapeInfo.x = getSelectedShape.shapeInfo.x - diffX*multiplier;
                // getSelectedShape.shapeInfo.x2 = getSelectedShape.shapeInfo.x2 - diffX*multiplier;
            }
              if (end1[1] > start1[1]) {
                getSelectedShape.offSetY(diffY*multiplier);
                // getSelectedShape.shapeInfo.y = getSelectedShape.shapeInfo.y + diffY*multiplier;
                // getSelectedShape.shapeInfo.y2 = getSelectedShape.shapeInfo.y2 + diffY*multiplier;
            }
              else {
                getSelectedShape.offSetY(-diffY*multiplier);
                // getSelectedShape.shapeInfo.y = getSelectedShape.shapeInfo.y - diffY*multiplier;
                // getSelectedShape.shapeInfo.y2 = getSelectedShape.shapeInfo.y2 - diffY*multiplier;
                }
            }
        } 
        paintCanvas.repaint();

    }

}
