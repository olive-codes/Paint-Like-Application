package model.Commands;

import model.ClipBoard;
import model.ListContainer;
import model.interfaces.ICommand;
import model.interfaces.IUndoable;
import model.shape.Group;
import model.shape.IShape;

import java.util.LinkedList;
import java.util.List;

public class GroupCommand implements IUndoable, ICommand {

    private final List<IShape> shapesToGroup;

    private final Group group;

    public GroupCommand (List<IShape> selected) {
        shapesToGroup = new LinkedList<>(selected);
        this.group = new Group(selected);
    }

    @Override
    public void undo() {
        ListContainer.getShapeList().removeShape(group);
        for (IShape shape : shapesToGroup) {
            ListContainer.getShapeList().addShape(shape);
        }
        ClipBoard.INSTANCE.setSelected(shapesToGroup);
        
    }

    @Override
    public void redo() {
        group();
        
    }

    @Override
    public void execute() {
        group();
        CommandHistory.add(this);
        
    }
    

    public final void group () {
        for (IShape shape : shapesToGroup) {
            ListContainer.getShapeList().removeShape(shape);
        }
        ListContainer.getShapeList().addShape(group);
        List<IShape> selected = new LinkedList<>();
        selected.add(this.group);
        ClipBoard.INSTANCE.setSelected(selected);
        //go through the shapes selected by the user

        // for (CreateShapeCommand shape : shapesToGroup) {
            
        //     //get index of shape in actual list
        //     int index = ListContainer.getShapeList().getShapes().indexOf(shape);
        //     //using index find the shape in actual list -> shape info -> set to in group
        //     ListContainer.getShapeList().getShapes().get(index).shapeInfo.inGroup = true;
        
        // }

        // paintCanvas.repaint();
    }

    public IShape getGroup() {
        return group;
    }
}
