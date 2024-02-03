package ru.academits.danilov_e.Shapes;

public class Circle implements Shape, Comparable<Shape> {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double getWidth() {
        return 2 * radius;
    }

    public double getHeight() {
        return 2 * radius;
    }

    public double getArea() {
        return Math.PI * Math.pow(radius, 2);
    }

    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    public String getName(){
        return "Круг";
    }

    public int compareTo(Shape figure) {
        if (this.getArea() < figure.getArea()) {
            return -1;
        } else if (this.getArea() == figure.getArea()) {
            return 0;
        }

        return 1;
    }

    @Override
    public String toString(){
        return "Фигура: " + this.getName() + "\nДлинна: " + this.getWidth() + "\nШирина: " + this.getHeight()
                + "\nПлощадь: " + this.getArea() + "\nПериметр: " + this.getPerimeter();
    }

    public boolean equals(Shape figure){
        if(figure == this){
            return true;
        }

        if(figure == null || figure.getClass() != getClass()){
            return false;
        }

        return this.getWidth() == figure.getWidth() && this.getHeight()
                == figure.getHeight() && this.getArea() == figure.getArea() && this.getPerimeter()
                == figure.getPerimeter();
    }
}
