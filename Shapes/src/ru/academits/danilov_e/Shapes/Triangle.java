package ru.academits.danilov_e.Shapes;

public class Triangle implements Shape, Comparable<Shape> {
    private double x1;
    private double x2;
    private double x3;

    private double y1;
    private double y2;
    private double y3;

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;

        this.y1 = y1;
        this.y2 = y2;
        this.y3 = y3;
    }

    public double getWidth() {
        return Math.max(this.x1, Math.max(this.x2, this.x3)) - Math.min(this.x1, Math.min(this.x2, this.x3));
    }

    public double getHeight() {
        return Math.max(this.y1, Math.max(this.y2, this.y3)) - Math.min(this.y1, Math.min(this.y2, this.y3));
    }

    public double getArea() {
        return (this.x1 * (this.y2 - this.y3) + this.x2 * (this.y3 - this.y1) + this.x3 * (this.y1 - this.y2)) / 2;
    }

    public double getPerimeter() {
        double sideA = Math.sqrt(Math.pow((this.x2 - this.x1), 2) + Math.pow((this.y2 - this.y1), 2));
        double sideB = Math.sqrt(Math.pow((this.x3 - this.x2), 2) + Math.pow((this.y3 - this.y2), 2));
        double sideC = Math.sqrt(Math.pow((this.x1 - this.x3), 2) + Math.pow((this.y1 - this.y3), 2));

        return sideA + sideB + sideC;
    }

    public String getName(){
        return "Треугольник";
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
