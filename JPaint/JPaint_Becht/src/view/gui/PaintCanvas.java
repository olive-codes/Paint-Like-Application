package view.gui;

import view.interfaces.PaintCanvasBase;

import controller.NullObject;
import model.GraphicsSingleton;
import model.ListContainer;
import model.ShapeCollection;

import java.awt.*;

public class PaintCanvas extends PaintCanvasBase {
  // TODO: Refactor to make this passed into all of the things rather than global
  //       OR make it an actual Singleton
    public static PaintCanvas INSTANCE;

    public Graphics2D getGraphics2D() {
        return (Graphics2D) getGraphics();
      }
    
    
      @Override
      /**
       * This is an event handler.  If this function gets called, its time to
       * draw the entire picture.
       * It you want to force a paint event, call aPaintCanvas.repaint()
       */
      public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        super.paint(g2d);
        ShapeCollection mainShapeColletion = ListContainer.getShapeList();
        GraphicsSingleton.getInstance().setG2D(g2d);
        if (mainShapeColletion == null) {
          NullObject n = new NullObject();
          n.execute();
          return;
        }
        mainShapeColletion.draw(g2d);
        
        // if (mainShapeColletion.getShapes().size() > 0) {
        //   mainShapeColletion.getShapes().get(0).update();
        // }
    
        // if (ListContainer.getSelectedShapes().getShapes().size() > 0) {
        //   ListContainer.getSelectedShapes().getShapes().get(0).update();
        // }
    
        System.out.println("Time to repaint");
      }
}
