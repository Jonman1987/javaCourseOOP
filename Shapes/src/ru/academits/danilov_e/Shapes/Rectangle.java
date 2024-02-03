package ru.academits.danilov_e.Shapes;

public class Rectangle implements Shape, Comparable<Shape> {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getArea() {
        return width * height;
    }

    public double getPerimeter() {
        return 2 * (width + height);
    }

    public String getName(){
        return "Прямоугольник";
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

    public boolean equals(Object figure){
        if(figure == this){
            return true;
        }

        if(figure == null || figure.getClass() != getClass()){
            return false;
        }

        Shape temp = (Shape) figure;
        return this.getWidth() == temp.getWidth() && this.getHeight()
                == temp.getHeight() && this.getArea() == temp.getArea() && this.getPerimeter()
                == temp.getPerimeter();
    }
}
