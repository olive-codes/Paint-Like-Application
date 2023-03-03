package drawing;

import java.awt.Graphics2D;
import java.awt.Stroke;

import model.shape.ShapeInfo;

import java.awt.BasicStroke;

public class OutlineDrawingStrategy extends DrawingStrategy {

    public static final DrawingStrategy INSTANCE = new OutlineDrawingStrategy();

        private OutlineDrawingStrategy() {}

        @Override
        public void draw(ShapeInfo si, Graphics2D g2d) {
            if (si.isSelected) {
                Stroke dash = new BasicStroke(3, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND,
                    0, new float[]{15}, 5);
                g2d.setStroke(dash);
              } else {
                g2d.setStroke(new BasicStroke(3));
              }
              g2d.setColor(si.secondaryColor.getColor());
              g2d.draw(si.shapeG2D);
              //g2d.drawPolygon(tri_x, tri_y, 3);
              
        }


}
