package model.shape;

public interface ShapeBuilder {

    ShapeBuilder offsetX(double i);
    ShapeBuilder offsetY(double i);
    IShape build();
    
}