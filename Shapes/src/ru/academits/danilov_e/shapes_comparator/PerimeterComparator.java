package ru.academits.danilov_e.shapes_comparator;

import ru.academits.danilov_e.shapes.Shape;

import java.util.Comparator;

public class PerimeterComparator implements Comparator<Shape> {
    public int compare(Shape shape1, Shape shape2) {
        return Double.compare(shape1.getPerimeter(), shape2.getPerimeter());
    }
}