package model.Commands;
import model.interfaces.ICommand;
import model.interfaces.IUndoable;

import java.util.LinkedList;
import java.util.List;

import javax.sound.sampled.Clip;

import model.ClipBoard;
import model.ListContainer;

import model.shape.Group;
import model.shape.IShape;

public class UngroupCommand implements IUndoable, ICommand {

    private final Group shapesToUngroup;

    public UngroupCommand (Group group) {
        shapesToUngroup = group;
    
    }

    @Override
    public void execute() {
        ungroup();
        CommandHistory.add(this);
        
    }

    @Override
    public void undo() {
        for (IShape shape : shapesToUngroup.getShapes()) {
            ListContainer.getShapeList().removeShape(shape);
        }
        ListContainer.getShapeList().addShape(shapesToUngroup);

        List<IShape> selected = new LinkedList<>();
        selected.add(shapesToUngroup);
        ClipBoard.INSTANCE.setSelected(selected);
        
    }

    @Override
    public void redo() {
        ungroup();
        
    }

    public final void ungroup () {

        for (IShape shape : shapesToUngroup.getShapes()) {
            ListContainer.getShapeList().addShape(shape);
        }
        ListContainer.getShapeList().removeShape(shapesToUngroup);

        ClipBoard.INSTANCE.setSelected(shapesToUngroup.getShapes());


        // //go through the shapes selected by the user
        // for (CreateShapeCommand shape : shapesToUngroup) {

        //  //get index of shape in actual list
        //  int index = ListContainer.getShapeList().getShapes().indexOf(shape);
        //  //using index find the shape in actual list -> shape info -> set to not in group
        //  ListContainer.getShapeList().getShapes().get(index).shapeInfo.inGroup = false;

        // }

        // paintCanvas.repaint();
    }
    
}
