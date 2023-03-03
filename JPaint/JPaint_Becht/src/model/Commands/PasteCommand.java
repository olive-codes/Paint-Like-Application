package model.Commands;

import model.ListContainer;
import model.interfaces.ICommand;
import model.interfaces.IUndoable;
import model.shape.IShape;
import view.interfaces.PaintCanvasBase;

import java.util.LinkedList;
import java.util.List;

public class PasteCommand implements IUndoable, ICommand {

    private final List<IShape> shapestoPaste;
    private final PaintCanvasBase paintCanvas;

    public PasteCommand(List<IShape> clipBoardList, PaintCanvasBase paintCanvas) {
        shapestoPaste = new LinkedList<>(clipBoardList);
        this.paintCanvas = paintCanvas;
    }

    @Override
    public void execute() {
        doPaste();
        CommandHistory.add(this);
    }

    @Override
    public void undo() {

        for (IShape shape : shapestoPaste) {

            ListContainer.getShapeList().getShapes().remove(shape);

        }
        paintCanvas.repaint();
    }

    @Override
    public void redo() {
        doPaste();
    }

    private final void doPaste() {
        //go through Clipboard
        for (IShape shape : shapestoPaste) {

            //change x + y
            // shape.shapeInfo.x = 0;
            // shape.shapeInfo.y = 0;

            //add shape to main list
            ListContainer.getShapeList().addShape(shape);

        }

        paintCanvas.repaint();

        } 
    }