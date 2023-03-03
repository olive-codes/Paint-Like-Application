package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import controller.MouseHandler;
import model.Commands.DeleteCommand;
import model.Commands.GroupCommand;
import model.Commands.PasteCommand;
import model.Commands.UngroupCommand;
import model.shape.Group;
import model.shape.IShape;
import model.shape.ShapeBuilder;
import view.gui.PaintCanvas;

public class ClipBoard {

    public static final ClipBoard INSTANCE = new ClipBoard();

    private final List<IShape> selected = new ArrayList<>();
    private final List<ShapeBuilder> clipboardList = new ArrayList<>();

    private ClipBoard() {
    }

    // Maybe Clipboard is a Singleton?

    // clipboard file
    // clear clipboard list?
    // add selected list shapes to clipboard list (using boundary box?)

    // paste file
    // take in clipboard list
    // take in start/ end point (Mouse Handler -> drag + release)
    // manipulate x + y values of shapes in clipboard list
    // add those shapes to main shape list
    // repaint canvas

    public void createCopyList(List<IShape> selected) {
        clipboardList.clear();
        for (IShape shape : selected) {
            // ShapeInfo copy = null; // Make a copy
            // Joe recommends creating a ShapeInfoBuilder class
            // At the end of the day you will have:

            ShapeBuilder copy = shape.getBuilder();
            clipboardList.add(copy);
        }

    }

    public void copy() {
        createCopyList(MouseHandler.getInstance().getSelected());
    }

    public void paste() {

        // can i just call from paste command here?
        List<IShape> shapes = new ArrayList<>();
        for (ShapeBuilder builder : clipboardList) {
            IShape copy = builder.offsetX(25).offsetY(25).build();
            shapes.add(copy);
        }
        PasteCommand paste = new PasteCommand(shapes, PaintCanvas.INSTANCE);
        paste.execute();

        System.out.println("Shapes:");
        /*
         * for(ShapeInfo info : clipboardList) {
         * System.out.println(info.shape.name());
         * System.out.println("  - " + info.x + ", " + info.y);
         * System.out.println("-=-=-=-=-=-=-\n\n");
         * }
         */
    }

    public void delete() {
        DeleteCommand delete = new DeleteCommand(Collections.unmodifiableList(this.selected), PaintCanvas.INSTANCE);
        delete.execute();

    }

    public void setSelected(List<IShape> selected) {
        this.selected.clear();
        this.selected.addAll(selected);
    }

    public List<IShape> getSelected() {
        return Collections.unmodifiableList(this.selected);
    }

    public void group() {
        if (this.selected.size() <= 1) return;
        GroupCommand group = new GroupCommand(selected);
        group.execute();

    }

    public void ungroup() {
        if (this.selected.size() != 1) return;
        if (!(this.selected.get(0) instanceof Group)) return;
        Group group = (Group)this.selected.get(0);
        UngroupCommand ungroup = new UngroupCommand(group);
        ungroup.execute();
    }

}
