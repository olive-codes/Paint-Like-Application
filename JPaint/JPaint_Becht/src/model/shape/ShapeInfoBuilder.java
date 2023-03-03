package model.shape;

import controller.CreateShapeCommand;
import java.awt.Point;
import java.awt.Shape;

import model.ShapeCollection;
import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;
import model.persistence.ApplicationState;
import view.interfaces.PaintCanvasBase;

public class ShapeInfoBuilder implements ShapeBuilder {

    public Shape shapeG2D;
    public ShapeColor primaryColor;
    public ShapeColor secondaryColor;
    public ShapeType shape;
    public ShapeShadingType shading;
    public Point start;
    public Point end;
    public ShapeCollection sl;
    public ApplicationState state;
    public PaintCanvasBase pc;
    public double width;
    public double height;
    public double x;
    public double y;
    public double x2;
    public double y2;
    public boolean isSelected;
    public boolean inGroup;
    public CreateShapeCommand outlineShape;

    public static ShapeInfoBuilder copy(ShapeInfo toCopy) {
        ShapeInfoBuilder builder = new ShapeInfoBuilder();
        builder.shapeG2D = toCopy.shapeG2D;
        builder.primaryColor = toCopy.primaryColor;
        builder.secondaryColor = toCopy.secondaryColor;
        builder.shape = toCopy.shape;
        builder.shading = toCopy.shading;
        builder.start = toCopy.start;
        builder.end = toCopy.end;
        builder.sl = toCopy.sl;
        builder.state = toCopy.state;
        builder.pc = toCopy.pc;
        builder.width = toCopy.width;
        builder.height = toCopy.height;
        builder.x = toCopy.x;
        builder.y = toCopy.y;
        builder.x2 = toCopy.x2;
        builder.y2 = toCopy.y2;
        builder.isSelected = toCopy.isSelected;
        builder.inGroup = toCopy.inGroup;
        builder.outlineShape = toCopy.outlineShape;

        return builder;
    }

    @Override
    public ShapeInfoBuilder offsetX(double x) {
        this.x += x;
        this.x2 += x;
        return this;
    }

    @Override
    public ShapeInfo build() {
        ShapeInfo copy = new ShapeInfo(state, pc, start, end, x, y, x2, y2, width, height);

        copy.shapeG2D = this.shapeG2D;
        copy.primaryColor = this.primaryColor;
        copy.secondaryColor = this.secondaryColor;
        copy.shape = this.shape;
        copy.shading = this.shading;
        copy.start = this.start;
        copy.end = this.end;
        copy.sl = this.sl;
        copy.state = this.state;
        copy.pc = this.pc;
        copy.width = this.width;
        copy.height = this.height;
        copy.x = this.x;
        copy.y = this.y;
        copy.x2 = this.x2;
        copy.y2 = this.y2;
        copy.isSelected = this.isSelected;
        copy.inGroup = this.inGroup;
        copy.outlineShape = this.outlineShape;

        return copy;
    }

    @Override
    public ShapeInfoBuilder offsetY(double y) {
        this.y += y;
        this.y2 += y;
        return this;
    }

    public ShapeInfoBuilder isSelected(boolean isSelected) {
        this.isSelected = isSelected;
        return this;
    }

    public ShapeInfoBuilder secondaryColor(ShapeColor secondaryColor) {
        this.secondaryColor = secondaryColor;
        return this;
    }

    public ShapeInfoBuilder shadingType(ShapeShadingType shading) {
        this.shading = shading;
        return this;
    }
}
