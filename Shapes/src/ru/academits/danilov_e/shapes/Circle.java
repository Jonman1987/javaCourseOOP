package ru.academits.danilov_e.shapes;

public class Circle implements Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public double getWidth() {
        return 2 * radius;
    }

    public double getHeight() {
        return 2 * radius;
    }

    public double getArea() {
        return Math.PI * radius * radius;
    }

    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    public String getName() {
        return "Круг";
    }

    @Override
    public String toString() {
        return "Фигура: " + getName() + ", Площадь: " + getArea() + ", Периметр: " + getPerimeter();
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (object == null || object.getClass() != getClass()) {
            return false;
        }

        Circle temp = (Circle) object;

        return getWidth() == temp.getWidth() && getHeight() == temp.getHeight()
                && getArea() == temp.getArea() && getPerimeter() == temp.getPerimeter();
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;

        hash = prime * hash + Double.hashCode(radius);

        return hash;
    }
}
