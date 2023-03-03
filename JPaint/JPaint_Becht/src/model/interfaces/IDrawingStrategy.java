package model.interfaces;


import java.awt.Graphics2D;

import model.shape.ShapeInfo;

import java.awt.Shape;


public interface IDrawingStrategy {
    public void draw(Graphics2D g, ShapeInfo si);
    public Shape getShape(ShapeInfo si);
}