package ru.academits.danilov_e.shapes_comparator;

import ru.academits.danilov_e.shapes.Shape;

import java.util.Comparator;

public class AreaComparator implements Comparator<Shape> {
    public int compare(Shape shape1, Shape shape2) {
        return Double.compare(shape1.getArea(), shape2.getArea());
    }
}
