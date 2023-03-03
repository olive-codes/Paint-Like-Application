package model.shape;

import java.util.LinkedList;
import java.util.List;

public class GroupBuilder implements ShapeBuilder {

    private List<ShapeBuilder> subElements;

    public GroupBuilder(List<IShape> toCopy) {
        subElements = new LinkedList<>();
        for (IShape shape : toCopy) {
            subElements.add(shape.getBuilder());
        }
    }

    @Override
    public ShapeBuilder offsetX(double x) {
        for (ShapeBuilder sb : subElements) {
            sb.offsetX(x);
        }

        return this;
    }

    @Override
    public ShapeBuilder offsetY(double y) {
        for (ShapeBuilder sb : subElements) {
            sb.offsetY(y);
        }

        return this;
    }

    @Override
    public IShape build() {
        List<IShape> elements = new LinkedList<>();
        for (ShapeBuilder b : subElements) {
            elements.add(b.build());
        }
        Group g = new Group(elements);
        return g;
    }
    
}
