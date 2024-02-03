package ru.academits.danilov_e.Shapes;

public class Square implements Shape, Comparable<Shape> {
    private double sideSize;

    public Square(double sideSize) {
        this.sideSize = sideSize;
    }

    public double getWidth() {
        return sideSize;
    }

    public double getHeight() {
        return sideSize;
    }

    public double getArea() {
        return sideSize * sideSize;
    }

    public double getPerimeter() {
        return 4 * sideSize;
    }

    public String getName(){
        return "Квадрат";
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

    @Override
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
