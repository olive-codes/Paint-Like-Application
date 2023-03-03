package model.shape;

import controller.BoundaryBox;
import controller.CreateShapeCommand;
import controller.DrawEllipseStrategy;
import controller.DrawRectangleStrategy;
import controller.DrawTriangleStrategy;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.List;

import model.ClipBoard;
import model.ShapeCollection;
import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;
import model.interfaces.IDrawingStrategy;
import model.persistence.ApplicationState;
import view.interfaces.PaintCanvasBase;

// TODO: Create a ShapeInfoBuilder class (it should mimic ShapeInfo)
//       - Take a look at NutritionBuilder as a guide
// TODO: After basic ShapeInfoBuilder id done, add a constructor which has
//       a parameter ShapeInfo and copies the values into the builder (this allows for easy cloning)
// TODO: Make all of the fields in ShapeInfo final (this will break a lot of things)
//       - To fix this, you will replace all of those areas with the ShapeInfoBuilder to copy and change the values
//       - Protip: change fields to final one at a time and fix errors incrementally
public class ShapeInfo implements IShape {

  //TODO: Suggestion for Groups
  public boolean isGroup;
  public List<ShapeInfo> group;

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

  public ShapeInfo(ApplicationState state, PaintCanvasBase pc, Point start,
      Point end, double x, double y, double x2, double y2, double w, double h) {
    // TODO: We probably shouldn't use "getActivitePrimaryColor" etc...
    // ShapeInfo should only be constructed via the ShapeInfoBuilder
    // and all of the fields should be final and parameters.
    this.primaryColor = state.getActivePrimaryColor();
    this.secondaryColor = state.getActiveSecondaryColor();
    this.shading = state.getActiveShapeShadingType();
    this.shape = state.getActiveShapeType();
    this.start = start;
    this.end = end;
    this.state = state;
    this.width = w;
    this.height = h;
    this.x = x;
    this.y = y;
    this.x2 = x2;
    this.y2 = y2;
    this.pc = pc;
    this.isSelected = false;
    this.inGroup = false;
    this.outlineShape = null;
  }

  @Override
  public void draw(Graphics2D g2d) {
    IDrawingStrategy ids = null;
    // TODO Auto-generated method stub
    switch (this.shape) {
     
      case RECTANGLE:
        ids = new DrawRectangleStrategy();
        break;
      case ELLIPSE:
        ids = new DrawEllipseStrategy();
        break;
      case TRIANGLE:
        ids = new DrawTriangleStrategy();
        break;
   
    }

    ids.draw(g2d, this);

    // This draws the outline.
    // TODO: Consider moving into "shapeInfo" and running when "isSelected"
    if (ClipBoard.INSTANCE.getSelected().contains(this)) {

      ShapeInfo si = ShapeInfoBuilder
        .copy(this)
        .isSelected(true)
        .shadingType(ShapeShadingType.OUTLINE)
        .secondaryColor(ShapeColor.BLACK)
        .build();
      si.draw(g2d);
    }
    

  }

  @Override
  public BoundaryBox getBoundaryBox() {
    Rectangle2D box = this.shapeG2D.getBounds2D();
    BoundaryBox bounds = new BoundaryBox(box.getMinX(), box.getMinY(), box.getWidth(), box.getHeight());
    return bounds;
  }

  @Override
  public boolean intersects(Rectangle2D shape) {
    return this.shapeG2D.intersects(shape);
  }

  @Override
  public ShapeBuilder getBuilder() {
    return ShapeInfoBuilder.copy(this);
  }

  @Override
  public void offSetX(double x) {
    this.x += x;
    this.x2 += x;
    
  }

  @Override
  public void offSetY(double y) {
    this.y += y;
    this.y2 += y;
    
  }
}