package model.Commands;

import model.ListContainer;
import model.interfaces.ICommand;
import model.interfaces.IUndoable;
import model.shape.IShape;

import java.util.LinkedList;
import java.util.List;
import view.interfaces.PaintCanvasBase;

public class DeleteCommand implements IUndoable, ICommand {

    private final List<IShape> shapesToDelete;
    private final PaintCanvasBase paintCanvas;

    public DeleteCommand (List<IShape> selected, PaintCanvasBase paintCanvas) {
        shapesToDelete = new LinkedList<>(selected);
        this.paintCanvas = paintCanvas;
    }

    @Override
    public void execute() {
        delete();
        CommandHistory.add(this);
        
    }

    @Override
    public void undo() {

        for (IShape shape : shapesToDelete) {
            
            ListContainer.getShapeList().addShape(shape);
        }
        paintCanvas.repaint();
    }

    @Override
    public void redo() {
        delete();
    }

    private final void delete() {

        for (IShape shape : shapesToDelete) {
            
            ListContainer.getShapeList().getShapes().remove(shape);
  
        }
        paintCanvas.repaint();
    }
    
}
